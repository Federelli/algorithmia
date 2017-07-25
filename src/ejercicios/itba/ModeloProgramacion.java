package ejercicios.itba;

import java.util.stream.LongStream;

public class ModeloProgramacion {

	public static void sequentialRun(int times) {
		CuentaBancaria cuenta = new CuentaBancaria();
		for (int i = 0; i < times; i++) {
			cuenta.depositar(10);
		}
		System.out.println("Sequential Run: " + cuenta.leerMonto());
	}

	public static void concurrentRun(int times) throws InterruptedException {
		CuentaBancaria cuenta = new CuentaBancaria();
		Thread[] pool = new Thread[times];
		for (int i = 0; i < times; i++) {
			pool[i] = new Thread() {
				@Override
				public void run() {
					cuenta.depositar(10);
				}
			};
			pool[i].start();
		}
		for (int i = 0; i < times; i++) {
			pool[i].join();
		}
		System.out.println("Sequential Run: " + cuenta.leerMonto());
	}

	public static void concurrentRunGood(int times) throws InterruptedException {
		CuentaBancaria cuenta = new CuentaBancaria();
		Thread[] pool = new Thread[times];
		for (int i = 0; i < times; i++) {
			pool[i] = new Thread() {
				@Override
				public void run() {
					synchronized (cuenta) {
						cuenta.depositar(10);
					}
				}
			};
			pool[i].start();
		}
		for (int i = 0; i < times; i++) {
			pool[i].join();
		}
		System.out.println("Sequential Run: " + cuenta.leerMonto());
	}

	public static void concurrentRunNew(int times) throws InterruptedException {
		CuentaBancaria cuenta = new CuentaBancaria();
		LongStream.rangeClosed(1, times).parallel().forEach(e -> cuenta.depositar(10));
		System.out.println("Sequential Run: " + cuenta.leerMonto());
	}

	public static void main(String[] args) throws InterruptedException {
		int times = 100000;
		// sequentialRun(times);
		concurrentRunNew(times);
	}

}
