package ejercicios.elementum;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MyBetterTestClass {

	//Haciendo volatile la variable, me aseguro que no sea cacheable y todos los threads siempre tengan el último valor
	private volatile static MyBetterTestClass myInstance;
	 //Uso ConcurrentHashMap
	private ConcurrentMap<String, VeryExpensiveToCreateClass> cache = new ConcurrentHashMap<>();
	//Cada tanto limpiar el HashMap
	VerySlowConnection connection = new VerySlowConnection(); //Pool de conexiones

	 //Agrego objeto para usar de Lock
	private static Object singletonLock = new Object();
	private static Object updateCacheLock = new Object();
	
	//Remuevo el syncrhonized a nivel método y hago el chequeo asegurandome que sólo los primeros threads crean la instancia. (1)
	public static MyBetterTestClass getInstance() {
		MyBetterTestClass testClass = myInstance;
		if (myInstance == null) {
			synchronized(singletonLock) {
				myInstance = testClass;
				if(myInstance == null) {
					myInstance = testClass = new MyBetterTestClass();
				}
			}
		}
		//La mayoría de las veces la instancia ya va a estar creada y no se llama al synchronized
		return myInstance;
	}
	//utilizar Loader.

	private String buildIdentifier(String token, String username, String machine) {
		//Usemos StringBuilder en vez de StringBuffer, no es necesario el thread safeness.
		StringBuilder builder = new StringBuilder();
		builder.append(token)
			.append("|")
			.append(username)
			.append("|")
			.append(machine);
		return builder.toString();
	}

	private VeryExpensiveToCreateClass retrieveRemoteInstance(String id) {
		return connection.get(id);
	}

	//Quito el synchronized a nivel método
	public VeryExpensiveToCreateClass get(String token, String username, String machine) {
		String id = buildIdentifier(token, username, machine);
		VeryExpensiveToCreateClass result = null;
		if(cache.containsKey(id)) {
			result = cache.get(id);
		} else {
			result = retrieveRemoteInstance(id);
			synchronized(updateCacheLock) {
				if (cache.containsKey(id)) {
					cache.get(id);
				} else {
					if (result != null) {
						cache.put(id, result);
					} else {
						cache.put(id, new VeryExpensiveToCreateClass(id));
					}
				}
			}
		}
		return result;
	}

	public static void main(String[] args) {
		MyBetterTestClass testClass = MyBetterTestClass.getInstance();
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
