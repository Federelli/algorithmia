package ejercicios.elementum.vending;

import java.math.BigDecimal;

public class VendingMachineFactory {

	 VendingMachine getVendingMachine(String type) {
		switch (type) {
		case "Queue":
			return new VendingMachineImpl(25, 10, new BigDecimal(100));
		default:
			return new VendingMachineImpl(25, 10, new BigDecimal(100));
		}
	}
}
