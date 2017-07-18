package ejercicios;

import java.util.Scanner;

public class ProducerConsumer2 {

	//Prueba de notify() y wait()
	
	public static void main(String[] args) {
		final ProducerConsumer2 consumerProducer = new ProducerConsumer2();
		Thread producer = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					consumerProducer.produce();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		Thread consumer = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					consumerProducer.consume();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		producer.start();
		consumer.start();
	}
	
	public void produce() throws InterruptedException {
		synchronized (this) {
			System.out.println("Producer Thread Running...");
			wait();
			System.out.println("Resumed.");
		}
	}

	public void consume() throws InterruptedException {
		Scanner scanner = new Scanner(System.in);
		Thread.sleep(2000);
		synchronized (this) {
			System.out.println("Waiting for return key.");
			scanner.nextLine();
			notify();
		}
	}

}
