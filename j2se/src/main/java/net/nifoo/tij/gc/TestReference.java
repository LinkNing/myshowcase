package net.nifoo.tij.gc;

import java.lang.ref.PhantomReference;
import java.lang.ref.Reference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class TestReference {

	public static void main(String[] args) {
		new TestReference().foo();
	}

	public void foo() {
		final ReferenceQueue refQ = new ReferenceQueue();
		StringBuilder info1 = new StringBuilder("Soft Reference Target");
		StringBuilder info2 = new StringBuilder("Weak Reference Target");
		StringBuilder info3 = new StringBuilder("Reference Target 3");
		Reference<StringBuilder> refSoft1 = new SoftReference<StringBuilder>(info1, refQ);
		//Reference<StringBuilder> refSoft2 = new SoftReference<StringBuilder>(info2, refQ);
		Reference<StringBuilder> refWeek = new WeakReference<StringBuilder>(info2, refQ);
		Reference<StringBuilder> refPhan = new PhantomReference<StringBuilder>(info3, refQ);
		info1 = null;
		info2 = null;
		info3 = null;

		System.out.println(refWeek.get());
		System.out.println(refPhan.get()); // 影子引用的get方法永远返回null

		Runnable runner = new Runnable() {
			private List bufList = new ArrayList();

			public void run() {
				int i = 0;
				while (true) {
					try {
						byte[] buff = new byte[1024 * 1024];
						Reference ref = new PhantomReference(buff, refQ);
						bufList.add(ref);
					} catch (OutOfMemoryError e) {
						System.out.println(e);
						//System.exit(1);
						return;
					}
					if (++i % 10 == 0) {
						System.gc();
					}
				}
			}
		};

		new Thread(runner).start();

		System.out.println(">>>>>>>>>>>>>>>开始监控引用>>>>>>>>>>>>>>>");
		while (true) {
			try {
				Reference<StringBuilder> ref = refQ.remove(10000);
				if (ref != null) {
					// 影子引用必须显示清除
					//if (ref instanceof PhantomReference)
					//	ref.clear();
					System.out.println(">> " + ref);
				} else {
					System.out.println(">> .");
				}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}
}
