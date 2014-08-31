package net.nifoo.tij.parallel.forkjoin;

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
		f2.fork(); // 对第二个子任务进行fork()，即放入线程对应队列的结尾
		f1.invoke(); // 执行第一个子任务
		
		return f1.compute() + f2.join();
	}

	public static void main(String[] args) {
		Fibonacci task = new Fibonacci(50);
		ForkJoinPool pool = new ForkJoinPool(4);

		pool.invoke(task);
		System.out.println(task.getRawResult());
	}

}
