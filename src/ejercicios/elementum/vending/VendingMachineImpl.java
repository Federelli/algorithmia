package ejercicios.elementum.vending;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Optional;
import java.util.Queue;

import javafx.util.Pair;

public class VendingMachineImpl implements VendingMachine {

	private Map<Integer, Queue<VendingItem>> items = new HashMap<>(); // MultiMap
	private int maxSlots;
	private int maxItemsPerSlot;
	private BigDecimal money;
	private BigDecimal insertedMoney;
	private Optional<Integer> selectedItem;
	private String display;

	public VendingMachineImpl(int maxSlots, int maxItemsPerSlot, BigDecimal money) {
		this.maxItemsPerSlot = maxItemsPerSlot;
		this.maxSlots = maxSlots;
		this.money = money;
		this.insertedMoney = new BigDecimal(0);
	}

	@Override
	public boolean addItem(VendingItem item, Integer itemNumber) {
		if (items.containsKey(itemNumber)) {
			Queue<VendingItem> lane = items.get(itemNumber);
			return lane.offer(item);
		} else {
			Queue<VendingItem> lane = new LinkedList<VendingItem>();
			if (lane.offer(item)) {
				items.put(itemNumber, lane);
				return true;
			} else {
				System.out.println("Lane" + itemNumber + " is full, unable to add item ");
			}
		}
		return false;
	}
	
	private Pair<Optional<VendingItem>, BigDecimal> dispenseItem(Integer itemNumber) {
		if (items.containsKey(itemNumber)) {
			Queue<VendingItem> lane = items.get(itemNumber);
			Optional<VendingItem> item = Optional.ofNullable(lane.poll());
			if (item.isPresent() && insertedMoney.compareTo(item.get().price()) >= 0) {
				successfulPurchase();
				BigDecimal change = new BigDecimal(0);
				if (insertedMoney.compareTo(item.get().price()) > 0) {
					change = giveChange(item.get().price());
				}
				resetInsertedMoney();
				return new Pair<>(item, change);
			}
		}
		return new Pair<>(Optional.empty(), new BigDecimal(0));
	}

	private void successfulPurchase() {
		this.money.add(insertedMoney);
	}

	private void resetInsertedMoney() {
		insertedMoney = new BigDecimal(0);
	}

	@Override
	public void selectItem(Integer itemNumber) {
		this.selectedItem = Optional.of(itemNumber);
		// TODO: update display
	}

	@Override
	public Pair<Optional<VendingItem>, BigDecimal> submit() {
		if (selectedItem.isPresent()) {
			return dispenseItem(selectedItem.get());
		} else {
			return new Pair<>(Optional.empty(), new BigDecimal(0));
		}
		// TODO: update display
	}

	private BigDecimal giveChange(BigDecimal itemPrice) {
		return insertedMoney.subtract(itemPrice);
	}

	@Override
	public void insertMoney(BigDecimal money) {
		insertedMoney = insertedMoney.add(money);
	}

	public int itemsAtLane(Integer lane) {
		if (items.containsKey(lane)) {
			return items.get(lane).size();
		}
		return 0;
	}

	public BigDecimal insertedMoney() {
		return insertedMoney;
	}
}
