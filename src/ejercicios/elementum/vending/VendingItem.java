package ejercicios.elementum.vending;

import java.math.BigDecimal;

public class VendingItem {

	private int id;
	private String name;
	private BigDecimal price;
	
	public VendingItem(String name, BigDecimal price) {
		this.name = name;
		this.price = price;
	}
	
	public void updateName(String name) {
		this.name = name;
	}
	
	public void updatePrice(BigDecimal price) {
		this.price = price;
	}
	
	public BigDecimal price() {
		return price;
	}
	
	@Override
	public String toString() {
		return name;
	}
	
}
