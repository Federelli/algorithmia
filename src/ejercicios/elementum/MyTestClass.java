package ejercicios.elementum;

import java.util.HashMap;
import java.util.Map;

/**
 * Background: This class is a singleton that is accessed concurrently from
 * several threads. It tries to retrieve an object from a remote connection, if
 * not available, it creates a local instance. It maintains a local cache of the
 * retrieved/create objects.
 *
 * VeryExpensiveToCreateClass is immutable.
 *
 * Problem: This class has at least 4 performance and 1 implementation problems.
 * Please identify them and write a comment about possible solutions. Extra
 * points for an actual implementation of the proposed fix.
 * 
 * Bonus: How would you make this class easier to test?
 */
public class MyTestClass {

	private static MyTestClass myInstance;
	private Map<String, VeryExpensiveToCreateClass> cache = new HashMap<>(); // Usar ConcurrentHashMap (1)
	VerySlowConnection connection = new VerySlowConnection();
	//Inyectar dependencia, para poder testear.

	// El synchronized este obliga a todos los threads a estar sincronizados y
	// cada thread tiene que esperar si hay otro haciendo el chequeo de null.
	// (2)
	public static synchronized MyTestClass getInstance() {
		if (myInstance == null) {
			myInstance = new MyTestClass();
		}
		return myInstance;
	}

	private String buildIdentifier(String token, String username, String machine) {
		//Se utiliza StringBuffer cuando esta variable va a ser única por Thread, no es necesario. (3)
		StringBuffer buffer = new StringBuffer();
		buffer.append(token)
			.append("|")
			.append(username)
			.append("|")
			.append(machine);
		return buffer.toString();
	}

	private VeryExpensiveToCreateClass retrieveRemoteInstance(String id) {
		return connection.get(id);
	}

	// El synchronized de aca sólo deja que se haga una conexión a la vez (4)
	public synchronized VeryExpensiveToCreateClass get(String token, String username, String machine) {
		String id = buildIdentifier(token, username, machine);
		VeryExpensiveToCreateClass result = cache.get(id);
		if (result == null) {
			System.out.println("Retrieving remote instance...");
			result = retrieveRemoteInstance(id);
		}
		if (result == null) {
			System.out.println("Remote instance not found...");
			System.out.println("Creating instance...");
			result = new VeryExpensiveToCreateClass(id);
			// Crea otra instancia de la clase en vez de devolver la que tiene, media pila capo! (5)
			cache.put(id, result);
		}

		return result;

	}

	public static void main(String[] args) {
		MyTestClass testClass = MyTestClass.getInstance();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("getting 1|1|1");
				testClass.get("1", "1", "1");
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("getting 1|1|1");
				testClass.get("1", "1", "1");
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				System.out.println("getting 2|1|1");
				testClass.get("2", "1", "1");
			}
		}).start();
	}

}
