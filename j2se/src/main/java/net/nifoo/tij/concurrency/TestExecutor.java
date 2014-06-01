package net.nifoo.tij.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class TestExecutor {

	public static void main(String[] args) {
		//		testExecutor();
		testScheduledExecutorService();
	}

	public static void testExecutor() {
		Executor executor = Executors.newSingleThreadExecutor();

		executor.execute(new Runnable() {

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getName());
			}
		});

		System.out.println(Thread.currentThread().getName());
	}

	public static void testExecutorService() {
		int processors = Runtime.getRuntime().availableProcessors();
		System.out.println(processors);

		ExecutorService executor = Executors.newFixedThreadPool(processors);
		Future<Integer> future = executor.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				return 7527;
			}

		});

		try {
			Integer i = future.get();
			System.out.println(i);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void testScheduledExecutorService() {
		ScheduledExecutorService ses = Executors.newScheduledThreadPool(1);
		Runnable pinger = new Runnable() {
			public void run() {
				System.out.println("PING!");
			}
		};
		ses.scheduleAtFixedRate(pinger, 5, 5, TimeUnit.SECONDS);
	}

}
