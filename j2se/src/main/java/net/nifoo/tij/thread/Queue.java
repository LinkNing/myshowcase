package net.nifoo.tij.thread;

import java.util.LinkedList;
import java.util.List;

public class Queue<T> {

	private int capacity;

	private List<T> cache;
	Object lock = new Object();

	private Integer lockRead = new Integer(0);
	private Integer lockWrite = new Integer(0);

	/**
	 * Constructor for Queue.
	 * 
	 * @param capacity
	 *            int
	 */
	public Queue(int capacity) {
		this.capacity = capacity;
		cache = new LinkedList<T>();
	}

	/**
	 * Method getCapacity.
	 * 
	 * @return int
	 */
	public int getCapacity() {
		return capacity;
	}

	/**
	 * Method pop.
	 * 
	 * @return T
	 * @throws InterruptedException
	 */
	public T pop() throws InterruptedException {
		T item = null;
		synchronized (lockRead) {
			while (cache.size() <= 0) {
				lockRead.wait();
			}
			item = cache.get(0);
			cache.remove(0);
		}
		synchronized (lockWrite) {
			lockWrite.notifyAll();
		}
		return item;
	}

	/**
	 * Method push.
	 * 
	 * @param item
	 *            T
	 * @throws InterruptedException
	 */
	public void push(T item) throws InterruptedException {
		synchronized (lockWrite) {
			while (cache.size() >= capacity) {
				List<T> cacheCopy = new LinkedList(cache);
				System.out.print("队列已满[");
				for (T elem : cacheCopy) {
					System.out.printf(elem + ",");
				}
				System.out.println("]");
				lockWrite.wait();
			}
			cache.add(item);
		}
		synchronized (lockRead) {
			lockRead.notifyAll();
		}
	}

}
