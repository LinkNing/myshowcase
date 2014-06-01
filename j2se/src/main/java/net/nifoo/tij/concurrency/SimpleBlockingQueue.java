package net.nifoo.tij.concurrency;

import java.util.LinkedList;
import java.util.concurrent.Callable;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.lang.math.RandomUtils;
import org.apache.commons.lang3.RandomStringUtils;

public class SimpleBlockingQueue<E> {
	private Lock lock = new ReentrantLock();
	private Condition notEmpty = lock.newCondition();
	private Condition notFull = lock.newCondition();
	private LinkedList<E> innerQ;
	private int maxSize;

	public SimpleBlockingQueue(int size) {
		this.maxSize = size;
		innerQ = new LinkedList<>();
	}

	public E take() throws InterruptedException {
		lock.lock();
		try {
			if (innerQ.size() == 0)
				notEmpty.await();
			if (innerQ.size() == maxSize)
				notFull.signalAll();

			E e = innerQ.poll();
			System.out.printf("[%s] takes: %s \n", Thread.currentThread().getName(), e);
			return e;

		} finally {
			lock.unlock();
		}
	}

	public void put(E element) throws InterruptedException {
		lock.lock();
		try {
			if (innerQ.size() == 0)
				notEmpty.signal();
			if (innerQ.size() == maxSize)
				notFull.await();

			innerQ.offer(element);

			System.out.printf("[%s] puts: %s \n", Thread.currentThread().getName(), element);
		} finally {
			lock.unlock();
		}
	}

	public static void main(String[] args) {
		SimpleBlockingQueue<Production> queue = new SimpleBlockingQueue<>(5);

		ExecutorService executor = Executors.newFixedThreadPool(2);
		executor.submit(new Productor(queue, executor));
		executor.submit(new Consumer(queue, executor));
		executor.submit(new Consumer(queue, executor));

		//executor.shutdown();
	}
}

class Production {
	private String name;

	public Production(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String toString() {
		return name;
	}
}

class Productor implements Callable<Production> {
	private ExecutorService executor;
	private SimpleBlockingQueue<Production> queue;

	public Productor(SimpleBlockingQueue<Production> queue, ExecutorService executor) {
		this.queue = queue;
		this.executor = executor;
	}

	@Override
	public Production call() throws Exception {
		Production prod = new Production(RandomStringUtils.randomAlphabetic(2));
		queue.put(prod);

		if (!Thread.interrupted()) {
			executor.submit(this);
		}

		return prod;
	}

}

class Consumer implements Runnable {
	private ExecutorService executor;
	private SimpleBlockingQueue<Production> queue;

	public Consumer(SimpleBlockingQueue<Production> queue, ExecutorService executor) {
		this.queue = queue;
		this.executor = executor;
	}

	@Override
	public void run() {
		try {
			Production prod = queue.take();
			prod.getName();

			Thread.sleep(RandomUtils.nextInt(5) * 1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
			return;
		}

		if (!Thread.interrupted()) {
			executor.execute(this);
		}
	}
}
