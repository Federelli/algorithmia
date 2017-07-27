package ejercicios.elementum.vending;

import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.Test;

import javafx.util.Pair;

public class VendingMachineTest {

	public VendingMachineImpl createVendingMachineWithStock() {
		VendingMachineImpl vendingMachine = new VendingMachineImpl(20, 10, new BigDecimal(100));
		VendingItem item1 = new VendingItem("Item1", new BigDecimal(1));
		VendingItem item2 = new VendingItem("Item2", new BigDecimal(2));
		VendingItem item3 = new VendingItem("Item3", new BigDecimal(3));
		VendingItem item4 = new VendingItem("Item4", new BigDecimal(4));
		for (int i = 0; i < 10; i++) {
			vendingMachine.addItem(item1, 1);
		}
		for (int i = 0; i < 10; i++) {
			vendingMachine.addItem(item2, 2);
		}
		for (int i = 0; i < 10; i++) {
			vendingMachine.addItem(item3, 3);
		}
		for (int i = 0; i < 10; i++) {
			vendingMachine.addItem(item4, 4);
		}
		return vendingMachine;
	}

	@Test
	public void testSucessfulPurchase() {
		VendingMachineImpl vendingMachine = createVendingMachineWithStock();
		vendingMachine.selectItem(1);
		vendingMachine.insertMoney(new BigDecimal(1));
		Pair<Optional<VendingItem>, BigDecimal> itemAndChange = vendingMachine.submit();
		assertTrue(vendingMachine.itemsAtLane(1) == 9);
		//assertTrue(itemAndChange.getKey().equals(obj));
	}

	@Test
	public void unsuccessfulPurchase() {
		VendingMachineImpl vendingMachine = createVendingMachineWithStock();
		vendingMachine.selectItem(0);
		vendingMachine.insertMoney(new BigDecimal(1));
		vendingMachine.submit();
		assertTrue(vendingMachine.insertedMoney().equals(new BigDecimal(1)));
	}
	
	@Test
	public void emptyLane() {
		VendingMachineImpl vendingMachine = createVendingMachineWithStock();
		for (int i = 0; i < 10; i++) {
			vendingMachine.selectItem(1);
			vendingMachine.insertMoney(new BigDecimal(1));
			vendingMachine.submit();
		}
		assertTrue(vendingMachine.itemsAtLane(1) == 0);
	}
	
	@Test
	public void emptyLanePlusOne() {
		VendingMachineImpl vendingMachine = createVendingMachineWithStock();
		for (int i = 0; i < 11; i++) {
			vendingMachine.selectItem(1);
			vendingMachine.insertMoney(new BigDecimal(1));
			vendingMachine.submit();
		}
		assertTrue(vendingMachine.itemsAtLane(1) == 0);
	}
	
	@Test
	public void cancel() {
		VendingMachineImpl vendingMachine = createVendingMachineWithStock();
		vendingMachine.selectItem(1);
		vendingMachine.insertMoney(new BigDecimal(1));
		assertTrue(vendingMachine.cancel().equals(new BigDecimal(1)));
		assertTrue(vendingMachine.itemsAtLane(1) == 10);
		assertTrue(vendingMachine.insertedMoney().equals(new BigDecimal(0)));
	}

}
