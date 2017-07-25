package ejercicios.elementum;

import java.util.HashMap;
import java.util.Map;

public class VerySlowConnection {

	Map<String, VeryExpensiveToCreateClass> connections = new HashMap<>();
	
	public VerySlowConnection() {
		connections.put("1|1|1", new VeryExpensiveToCreateClass(id));
	}
	
	VeryExpensiveToCreateClass get (String id) {
		return new VeryExpensiveToCreateClass(id);
	}
}
