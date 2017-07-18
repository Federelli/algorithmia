package ejercicios;

import java.util.LinkedList;
import java.util.Queue;

public class ElementumProducerConsumer {

	private int timeOut;
	private int limit;
	private static Queue<Integer> queue = new LinkedList<>();
	private Object lock = new Object();

	public static void main(String[] args) {
		final ElementumProducerConsumer consumerProducer = new ElementumProducerConsumer(100, 20);
		Thread producer = new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					consumerProducer.offer(1);
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
					consumerProducer.poll();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		producer.start();
		consumer.start();
	}

	public ElementumProducerConsumer(int timeOut, int limit) {
		this.timeOut = timeOut;
		this.limit = limit;
	}

	public boolean isFull() {
		return queue.size() == limit;
	}

	public Integer poll() throws InterruptedException {
		while (true) {
			synchronized (lock) {
				while (queue.isEmpty()) {
					lock.wait(timeOut);
				}
				System.out.print("List size is: " + queue.size());
				int value = queue.poll();
				System.out.println("; value is: " + value);
				lock.notify();
			}
		}
	}

	public boolean offer(int number) throws InterruptedException {
		while (true) {
			synchronized (lock) {
				while (isFull()) {
					lock.wait(timeOut);
				}
				queue.add(number);
				lock.notify();
			}
		}
	}
}
