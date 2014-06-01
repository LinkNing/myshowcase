package net.nifoo.tij.ref;

import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

public class ClExt {

	public static void main(String[] args) {
		ClExt ce = new ClExt();
		try {
			ce.test();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
	}

	@SuppressWarnings({ "unused", "rawtypes" })
	public void test() throws MalformedURLException {
		ClassLoader exCl = ClExt.class.getClassLoader();
		URL url = new URL("file:/E:/workspace/java/tij/ext/");
		ClassLoader cl = new URLClassLoader(new URL[] { url });
		URL url2 = new URL("file:/E:/workspace/java/tij/ext/");
		ClassLoader cl2 = new URLClassLoader(new URL[] { url2 });

		try {
			Class clClazz = cl.loadClass("net.nifoo.tij.ref.ClExt$MyThread");
			Thread th = (Thread) clClazz.newInstance();
			th.setContextClassLoader(cl);
			th.start();
			
			Class clazz1 = cl.loadClass("Msg");
			// Class clazz2 = cl2.loadClass("Msg");
			// System.out.println(clazz1.getClassLoader());
			// System.out.println(clazz2.getClassLoader());
			// System.out.println(clazz1.equals(clazz2));
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	public static class MyThread extends Thread{
		@SuppressWarnings("rawtypes")
		public void run() {
			System.out.println(this.getClass().getClassLoader());
			System.out.println(Thread.currentThread().getContextClassLoader());
			// ClassLoader cl = this.getClass().getClassLoader();
			ClassLoader cl1 = Thread.currentThread().getContextClassLoader();
			try {
				Class clazz1 = cl1.loadClass("Msg");
				System.out.println(clazz1);
				Class clazz3 = Class.forName("Msg",false, cl1);
				System.out.println(clazz3.getClassLoader());
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

		}
	}

}
