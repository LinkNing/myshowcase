package net.nifoo.tij.concurrency;

import java.util.concurrent.CountDownLatch;

public class CountDownLatchDemo extends Thread {
	public static void main(String[] args) {

		CountDownLatch latch = new CountDownLatch(2);
		Joinner joiner = new Joinner(latch);
		joiner.start();

		Worker w1 = new Worker(latch);
		Worker w2 = new Worker(latch);
		w1.start();
		w2.start();

	}
}

class Worker extends Thread {
	private CountDownLatch latch;

	public Worker(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("hello world!");
		latch.countDown();
	}

}

class Joinner extends Thread {
	private CountDownLatch latch;

	public Joinner(CountDownLatch latch) {
		super();
		this.latch = latch;
	}

	@Override
	public void run() {
		System.out.println("wait joining......");
		try {
			latch.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block  
			e.printStackTrace();
		}
		System.out.println("joined...");
	}

}