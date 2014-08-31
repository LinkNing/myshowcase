package net.nifoo.tij.concurrency;

public class HandleInterrupt {

	public static void main(String[] args) {
		testInterruptForSleep();
	}

	/**
	 * 线程阻塞时被中断。
	 */
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
						System.out.printf("中断状态：%s \n", Thread.currentThread().isInterrupted());
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

	/**
	 * 自己检测线程是否中断，并抛出异常。
	 */
	public static void testInterrupt() {

		Runnable task = new Runnable() {

			@Override
			public void run() {
				long start = System.currentTimeMillis();

				int i = 0;
				while (!Thread.currentThread().isInterrupted()) {
					i++;
					System.out.printf("elapse: %d \n", System.currentTimeMillis() - start);
				}
				System.out.printf("the count: %d \n", i);
				
				// 线程被中断，抛出异常
				throw new IllegalStateException(new InterruptedException("该线程已经被中断"));
			}
		};

		Thread thread = new Thread(task, "Interrupt demo");
		thread.setUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.printf("%s - %s", t.getName(), e.getMessage());
			}
		});

		thread.start();

		try {
			Thread.sleep(5);
			thread.interrupt();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

}
