package net.nifoo.tij.thread;

import java.util.Random;

public class Consumer implements Runnable {
	private Queue<Item> queue;

	public Consumer(String name, Queue<Item> queue) {
		this.queue = queue;
		Thread thread = new Thread(this, name);
		thread.start();
	}

	private boolean quit = false;

	public void run() {
		while (!quit) {
			try {
				Item item = queue.pop();
				System.out.printf("[%s] - 消费: %s \n", Thread.currentThread().getName(), item.getInfo());
				int interval = 400 + new Random().nextInt(600);
				System.out.println(interval);
				Thread.sleep(interval);
				Thread.yield();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	public boolean isQuit() {
		return quit;
	}

	public void setQuit(boolean quit) {
		this.quit = quit;
	}

}
