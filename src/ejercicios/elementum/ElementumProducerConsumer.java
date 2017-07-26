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
		synchronized (queue) {
			Integer num;
			if (queue.isEmpty()) {
				System.out.println("Queue is empty, waiting...");
				queue.wait(timeOut);
			}
			if (queue.isEmpty()) {
				return null;
			}
			num = queue.poll();
			System.out.println("Took item, list size is: " + queue.size());
			queue.notifyAll();
			return num;
		}
	}

	public boolean offer(int number) throws InterruptedException {
		synchronized (queue) {
			// No es necesario un while, para chequear los spurious wake-ups ya
			// que se quiere aprovechar el timeout, por lo tanto pregunto dos
			// veces
			if (isFull()) {
				System.out.println("Queue is full, waiting...");
				queue.wait(timeOut);
			}
			if (isFull()) {
				return false;
			}
			queue.add(number);
			System.out.println("Produced item, list size is: " + queue.size());
			queue.notifyAll();
			return true;
		}
	}

	public static void main(String[] args) {
		final ElementumProducerConsumer consumerProducer = new ElementumProducerConsumer(0, 50);
		Thread producer = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					while (true) {
						consumerProducer.offer(1);
					}
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
					while (true) {
						consumerProducer.poll();
					}
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
