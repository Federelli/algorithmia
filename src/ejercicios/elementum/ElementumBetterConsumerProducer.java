package ejercicios.elementum;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class ElementumBetterConsumerProducer {

	private int timeOut;
	private int limit;
	private static BlockingQueue<Integer> queue;

	public ElementumBetterConsumerProducer(int timeOut, int limit) {
		this.timeOut = timeOut;
		this.limit = limit;
		queue = new ArrayBlockingQueue<Integer>(limit);
	}

	public boolean isFull() {
		return queue.size() == limit;
	}

	public Integer poll() throws InterruptedException {
		while (true) {
			if (queue.isEmpty()) {
				System.out.println("Queue is empty, will wait");
			}
			queue.poll(timeOut, TimeUnit.MILLISECONDS);
			System.out.println("Took 1 element, list size is now: " + queue.size());
		}
	}

	public boolean offer(int number) throws InterruptedException {
		while (true) {
			while (isFull()) {
				System.out.println("Queue is full, will wait");
			}
			queue.offer(number, timeOut, TimeUnit.MILLISECONDS);
			System.out.println("Produced element, list size is now: " + queue.size());
		}
	}

	public static void main(String[] args) {
		final ElementumBetterConsumerProducer consumerProducer = new ElementumBetterConsumerProducer(0, 50);
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
}
