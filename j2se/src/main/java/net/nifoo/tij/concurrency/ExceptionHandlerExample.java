package net.nifoo.tij.concurrency;

public class ExceptionHandlerExample {

	public static void main(String[] args) {

		Runnable task = new Runnable() {

			@Override
			public void run() {
				System.out.println("I'm here");
				throw new IllegalStateException("test Exception");
			}
		};

		Thread thread = new Thread(task);
		thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.printf("%s's Uncaught Exception: \n", t.getName());
				e.printStackTrace(System.out);
			}
		});

		thread.start();
	}

}
