package net.nifoo.tij.parallel.forkjoin;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

class Task extends RecursiveAction {
	final long[] array;
	final int lo;
	final int hi;
	private int THRESHOLD = 4; //For demo only

	public Task(long[] array) {
		this.array = array;
		this.lo = 0;
		this.hi = array.length - 1;
	}

	public Task(long[] array, int lo, int hi) {
		this.array = array;
		this.lo = lo;
		this.hi = hi;
	}

	protected void compute() {
		if (hi - lo < THRESHOLD) {
			sequentiallySort(array, lo, hi);
		} else {
			int pivot = partition(array, lo, hi);
			//System.out.printf("\npivot = %d, low = %d, high = %d", pivot, lo, hi);
			//System.out.printf("\narray %s\n", Arrays.toString(array));
			invokeAll(new Task(array, lo, pivot - 1), new Task(array, pivot + 1, hi));
		}
	}

	private int partition(long[] array, int lo, int hi) {
		long x = array[hi];
		int i = lo - 1;
		for (int j = lo; j < hi; j++) {
			if (array[j] <= x) {
				i++;
				swap(array, i, j);
			}
		}
		swap(array, i + 1, hi);
		return i + 1;
	}

	private void swap(long[] array, int i, int j) {
		if (i != j) {
			long temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
	}

	private void sequentiallySort(long[] array, int lo, int hi) {
		Arrays.sort(array, lo, hi + 1);
	}
}

public class TestForkJoinSimple {
	private static final int NARRAY = 10000000; //For demo only
	long[] array = new long[NARRAY];
	Random rand = new Random();

	@Before
	public void setUp() {
		for (int i = 0; i < array.length; i++) {
			array[i] = rand.nextLong() % 100000000; //For demo only
		}
		//System.out.println("Initial Array: " + Arrays.toString(array));
	}

	@Test
	public void testSort() throws Exception {
		Task sort = new Task(array);
		ForkJoinPool fjpool = new ForkJoinPool();
		fjpool.submit(sort);
		fjpool.shutdown();

		fjpool.awaitTermination(30, TimeUnit.SECONDS);

		assertTrue(checkSorted(array));
	}

	@After
	public void showSortResult() {
		//System.out.println("Sorted Array: " + Arrays.toString(array));
		System.out.println(array.length);
	}

	boolean checkSorted(long[] a) {
		for (int i = 0; i < a.length - 1; i++) {
			if (a[i] > (a[i + 1])) {
				return false;
			}
		}
		return true;
	}
}