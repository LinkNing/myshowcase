package net.nifoo.tij.parallel.forkjoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

class Fib extends RecursiveTask<Integer> {
	static final int threshold = 13;
	volatile int number; // arg/result

	Fib(int n) {
		number = n;
	}

	int getAnswer() {
		if (!isDone())
			throw new IllegalStateException();
		return number;
	}

	int seqFib(int n) {
		if (n <= 1)
			return n;
		else
			return seqFib(n - 1) + seqFib(n - 2);
	}

	@Override
	protected Integer compute() {
		int n = number;
		if (n <= threshold) // granularity ctl
			number = seqFib(n);
		else {
			Fib f1 = new Fib(n - 1);
			Fib f2 = new Fib(n - 2);
			invokeAll(f1, f2);
			number = f1.getRawResult() + f2.getRawResult();
		}
		return number;
	}

	public static void main(String[] args) {
		int groupSize = 4; // for example
		ForkJoinPool group = new ForkJoinPool(groupSize);
		Fib f = new Fib(50); // for example
		group.invoke(f);
		int result = f.getAnswer();
		f.getRawResult();
		System.out.println("Answer: " + result);
	}

}