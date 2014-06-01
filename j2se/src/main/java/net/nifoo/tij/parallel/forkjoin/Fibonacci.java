package net.nifoo.tij.parallel.forkjoin;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @date   2014-3-26
*/
public class Fibonacci extends RecursiveTask<Long> {
	private static final long serialVersionUID = 7875142223684511653L;
	private final long n;

	Fibonacci(long n) {
		this.n = n;
	}

	protected Long compute() {
		if (n <= 1) {
			return n;
		}

		Fibonacci f1 = new Fibonacci(n - 1);
		Fibonacci f2 = new Fibonacci(n - 2);
		f1.fork();
		f2.fork();

		Long fi1 = f1.join();
		Long fi2 = f2.join();
		return fi1 + fi2;
	}

	public static void main(String[] args) {
		Fibonacci task = new Fibonacci(50);
		ForkJoinPool pool = new ForkJoinPool(4);

		pool.invoke(task);
		System.out.println(task.getRawResult());
	}

}
