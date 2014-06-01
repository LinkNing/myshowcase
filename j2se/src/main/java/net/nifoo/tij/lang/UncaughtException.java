package net.nifoo.tij.lang;

public class UncaughtException {

	public static void main(String[] args) {
		Thread.UncaughtExceptionHandler eh = new Thread.UncaughtExceptionHandler() {

			public void uncaughtException(Thread t, Throwable e) {
				System.err.println(t.getName() + " 异常中止: ");
				e.printStackTrace();

			}
		};

		Foo foo = new Foo();

		Thread t1 = new Thread(foo);
		t1.setUncaughtExceptionHandler(eh);
		t1.start();

		Thread t2 = new Thread(foo);
		t2.start();
	}

}

class Foo implements Runnable {

	public void run() {
		throw new RuntimeException("异常中止线程!");
	}

}
