package net.nifoo.tij.thread;

import java.util.Random;

public class Producer implements Runnable {

	private Queue<Item> queue;

	/**
	 * Constructor for Producer.
	 * 
	 * @param queue
	 *            Queue<Item>
	 */
	public Producer(String name, Queue<Item> queue) {
		this.queue = queue;
		Thread thread = new Thread(this, name);
		thread.start();
	}

	private boolean quit;

	/**
	 * Method run.
	 * 
	 * @see java.lang.Runnable#run()
	 */
	public void run() {
		while (!quit) {
			Item item = makeItem();
			try {
				queue.push(item);
				System.out.printf("[%s] - 生产: %s \n", Thread.currentThread().getName(), item.getInfo());
				Thread.sleep(200);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	private Random random = new Random();

	/**
	 * Method makeItem.
	 * 
	 * @return Item
	 */
	private Item makeItem() {
		String info = String.valueOf(random.nextInt(100));
		Item item = new Item();
		item.setInfo(info);
		return item;
	}

	/**
	 * Method isQuit.
	 * 
	 * @return boolean
	 */
	public boolean isQuit() {
		return quit;
	}

	/**
	 * Method setQuit.
	 * 
	 * @param quit
	 *            boolean
	 */
	public void setQuit(boolean quit) {
		this.quit = quit;
	}

}
