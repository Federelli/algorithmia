package ejercicios;

import java.util.LinkedList;
import java.util.Queue;

public class ProducerConsumerFail {

	private static Queue<Integer> queue = new LinkedList<>();

	public static void main(String[] args) {
		Thread producer = new Thread(new Runnable() {
			@Override
			public void run() {
				producer();
			}
		});
		Thread consumer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					consumer();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		producer.start();
		consumer.start();
	}

	private static void producer() {
		while (true) {
			if (queue.size() < 100) {
				queue.add(1);
				System.out.println("Producer produced 1 size is: " + queue.size());
			}
		}
	}

	private static void consumer() throws InterruptedException {
		while (true) {
			Thread.sleep(100);
			queue.poll();
			System.out.println("Consumer removed 1 - size is: " + queue.size());
		}
	}
}
