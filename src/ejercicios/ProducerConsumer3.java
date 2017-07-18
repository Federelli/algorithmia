package ejercicios;

import java.util.LinkedList;
import java.util.Random;

public class ProducerConsumer3 {

	// wait y notify con sincronized
	
	private LinkedList<Integer> list = new LinkedList<>();
	private final int LIMIT = 10;
	private Object lock = new Object();

	public static void main(String[] args) {
		final ProducerConsumer3 consumerProducer = new ProducerConsumer3();
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
		Random random = new Random();
		int value = 0;
		while (true) {
			synchronized (lock) {
				while (list.size() == LIMIT) {
					lock.wait();
				}
				list.add(value++);
				lock.notify();
			}
			Thread.sleep(random.nextInt(100));
		}
	}

	public void consume() throws InterruptedException {
		Random random = new Random();
		while (true) {
			synchronized (lock) {
				while (list.isEmpty()) {
					lock.wait();
				}
				System.out.print("List size is: " + list.size());
				int value = list.removeFirst();
				System.out.println("; value is: " + value);
				lock.notify();
			}
			Thread.sleep(random.nextInt(100));
		}
	}

}
