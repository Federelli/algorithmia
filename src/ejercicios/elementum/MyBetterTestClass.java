package ejercicios.elementum;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class MyBetterTestClass {

	//Haciendo volatile la variable, me aseguro que no sea cacheable y todos los threads siempre tengan el �ltimo valor
	private volatile static MyBetterTestClass myInstance;
	 //Uso ConcurrentHashMap
	private ConcurrentMap<String, VeryExpensiveToCreateClass> cache = new ConcurrentHashMap<>();
	VerySlowConnection connection = new VerySlowConnection();

	 //Agrego objeto para usar de Lock
	private static Object singletonLock = new Object();
	private static Object updateCacheLock = new Object();
	
	//Remuevo el syncrhonized a nivel m�todo y hago el chequeo asegurandome que s�lo los primeros threads crean la instancia. (1)
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
		//La mayor�a de las veces la instancia ya va a estar creada y no se llama al synchronized
		return myInstance;
	}

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

	//Quito el synchronized a nivel m�todo
	public VeryExpensiveToCreateClass get(String token, String username, String machine) {
		String id = buildIdentifier(token, username, machine);
		
		//Al tener ConcurrentHashMap no es necesario que sea sincronizado ni el m�todo ni el put
		cache.putIfAbsent(id, retrieveRemoteInstance(id));
		
		//Si no pudiera utilizar un ConcurrentHashMap, entonces el synch va luego de chequear si existe la variable
//		if (!cache.containsKey(id)) {
//			//Pongo un synchronized a nivel put luego de hacer la verificaci�n si tengo o no el objeto en el cache
//			synchronized(updateCacheLock) {
//				cache.putIfAbsent(id, retrieveRemoteInstance(id));
//			}
//		} 
		return cache.get(id);
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
