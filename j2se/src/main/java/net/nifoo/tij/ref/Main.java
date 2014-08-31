package net.nifoo.tij.ref;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.LinkedList;
import java.util.List;


@SuppressWarnings({"rawtypes","unused"})
public class Main {

	private static ReferenceQueue<Entry> refQ = new ReferenceQueue<Entry>();
	
	public static void main(String[] args) {
		Main main = new Main();
		main.fillMem();
		main.monitorRefQ();

		sleep(1000);

		// 这里的引用不能少，不然变成无引用，直接被GC回收掉了， ReferenceQueue里监控不到。
		Reference refSoft = main.ref1();
		Reference refWeak = main.ref2();
		Reference refPhantom = main.ref3();

		System.gc();

		while (true) {
			sleep(1000);
		}
	}

	private Thread monitorRefQ() {
		Thread th = new Thread("monitorRefQ") {
			public void run() {
				while (true) {
					try {
						Reference<? extends Object> removed = refQ.remove();
						if (removed instanceof PhantomReference)
							removed.clear();
						System.out.printf("[%s : %s] => Queue\n", removed, removed.get());
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		th.setDaemon(true);
		th.start();
		return th;
	}

	public Reference ref1() {
		Entry o = new Entry("SoftReference Entry");
		Reference<Entry> ref = new SoftReference<Entry>(o, refQ);
		System.out.printf("[%s] => %s\n", ref, o);
		return ref;
	}

	public Reference ref2() {
		Entry o = new Entry("WeakReference Entry");
		Reference<Entry> ref = new EntryRef(o, refQ);
		System.out.printf("[%s] => %s\n", ref, o);
		return ref;
	}

	public Reference ref3() {
		Entry o = new Entry("PhantomReference Entry");
		Reference<Entry> ref = new PhantomReference<Entry>(o, refQ);
		System.out.printf("[%s] => %s\n", ref, o);
		return ref;
	}

	private static void sleep(long time) {
		try {
			Thread.sleep(time);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static List buffer = new LinkedList();

	public void fillMem() {
		Thread th = new Thread("fillMem") {
			@SuppressWarnings("unchecked")
			public void run() {
				int _1MB = 1024 * 1024;
				while (true) {
					Main.sleep(1000);
					try {
						//						byte[] buf = new byte[2 * _1MB];
						buffer.add(new byte[_1MB]);
					} catch (OutOfMemoryError e) {
						e.printStackTrace();
						buffer.clear();
					}
				}
			}
		};
		th.setDaemon(true);
		th.start();
	}

	public static class EntryRef extends WeakReference<Entry> {

		public EntryRef(Entry referent, ReferenceQueue<? super Entry> q) {
			super(referent, q);
		}

		@Override
		public void finalize() {
			System.out.println("EntryRef finalized");
		}

	}
}