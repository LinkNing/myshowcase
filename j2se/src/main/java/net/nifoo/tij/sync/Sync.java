package net.nifoo.tij.sync;

public class Sync {

	void test1() {
		test();
	}
	
	synchronized void test() {
		System.out.println("xx");
	}

}
