package ejercicios.elementum;

import java.util.LinkedList;
import java.util.Queue;

public class ElementumProducerConsumer {

	private int timeOut;
	private int limit;
	private static Queue<Integer> queue = new LinkedList<>();

	public ElementumProducerConsumer(int timeOut, int limit) {
		this.timeOut = timeOut;
		this.limit = limit;
	}

	public boolean isFull() {
		return queue.size() == limit;
	}

	public Integer poll() throws InterruptedException {
		while (true) {
			synchronized (queue) {
				while (queue.isEmpty()) {
					System.out.println("Queue is empty, waiting...");
					queue.wait(timeOut);
				}
				queue.poll();
				System.out.println("Took item, list size is: " + queue.size());
				queue.notify();
			}
		}
	}

	public boolean offer(int number) throws InterruptedException {
		while (true) {
			synchronized (queue) {
				while (isFull()) {
					System.out.println("Queue is full, waiting...");
					queue.wait(timeOut);
				}
				queue.add(number);
				System.out.println("Produced item, list size is: " + queue.size());
				queue.notify();
			}
		}
	}
	
	public static void main(String[] args) {
		final ElementumProducerConsumer consumerProducer = new ElementumProducerConsumer(100, 1000);
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
