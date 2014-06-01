package net.nifoo.tij.concurrency;

import org.apache.commons.lang3.builder.HashCodeBuilder;

public class Runner {

	public static void main(String[] args) {
		testInterrupt();
	}

	public static void testInterruptForSleep() {

		Runnable task = new Runnable() {

			@Override
			public void run() {
				long start = System.currentTimeMillis();

				boolean interrupted = Thread.currentThread().isInterrupted();
				while (true) {
					try {
						Thread.sleep(2000);
					} catch (InterruptedException e) {
						interrupted = true;
						e.printStackTrace();
						break;
					}
					System.out.println(System.currentTimeMillis() - start);
				}
				if (interrupted)
					Thread.currentThread().interrupt();
			}
		};

		Thread thread = new Thread(task);
		thread.start();

		try {
			Thread.sleep(5000);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	public static void testInterrupt() {

		Runnable task = new Runnable() {

			@Override
			public void run() {
				long start = System.currentTimeMillis();

				int i = 0;
				while (!Thread.currentThread().isInterrupted()) {
					i++;
					System.out.println(System.currentTimeMillis() - start);
				}
				System.out.println(i);
				throw new IllegalStateException("test Exception");
			}
		};

		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
			
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.printf("%s - %s", t.getName(), e.getMessage());
			}
		});
		
		thread.start();

		try {
			Thread.sleep(50);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
