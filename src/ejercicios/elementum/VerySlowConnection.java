package ejercicios.elementum;

import java.util.HashMap;
import java.util.Map;

public class VerySlowConnection {

	Map<String, VeryExpensiveToCreateClass> connections = new HashMap<>();
	
	public VerySlowConnection() {
		//Put some connections just to test
		connections.put("1|1|1", new VeryExpensiveToCreateClass("1|1|1"));
		connections.put("1|1|2", new VeryExpensiveToCreateClass("1|1|2"));
	}
	
	VeryExpensiveToCreateClass get (String id) {
		return new VeryExpensiveToCreateClass(id);
	}
	
}
