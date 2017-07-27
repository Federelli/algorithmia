package ejercicios.elementum.vending;

import java.math.BigDecimal;
import java.util.Optional;

import javafx.util.Pair;

public interface VendingMachine {

	public boolean addItem(VendingItem item, Integer itemNumber);

	public void selectItem(Integer itemNumber);

	public void insertMoney(BigDecimal money);

	public Pair<Optional<VendingItem>, BigDecimal> submit();
	
}
