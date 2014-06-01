package net.nifoo.tij.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CallerExample {
	public static void main(String[] args) {

		ExecutorService executor = Executors.newSingleThreadExecutor();

		Caller caller = new Caller("China");
		Future<String> future = executor.submit(caller);

		try {
			String destination = future.get();
			System.out.printf("I'm in %s", destination);
		} catch (InterruptedException | ExecutionException e) {
			e.printStackTrace();
		}
		
		executor.shutdown();

	}

	static class Caller implements Callable<String> {

		public String destination;

		public Caller(String destination) {
			this.destination = destination;
		}

		@Override
		public String call() throws Exception {
			System.out.println("I'm here");
			return destination;
		}

	};
}
