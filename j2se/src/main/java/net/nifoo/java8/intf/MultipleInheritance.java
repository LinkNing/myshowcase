package net.nifoo.java8.intf;

interface ParentInterface1 {
	default String fun1() {
		return "ParentInterface1.fun1";
	}

	default String fun2() {
		return "ParentInterface1.fun2";
	}
}

interface ParentInterface2 {
	default String fun1() {
		return "ParentInterface2.fun1";
	}
}

interface SonInterface extends ParentInterface1, ParentInterface2 {
	/*
	 * 没有这个方法将通不过编译，SonInterface不知道该从哪个父接口继承fun1。
	 */
	default String fun1() {
		// return ParentInterface1.super.fun1(); // 手动指定继承哪一个父接口的方法
		return "SonInterface.fun1";
	}

	default String fun2() {
		return "SonInterface.fun2";
	}
}

class ImplementOfInterface implements SonInterface {

	public String fun2() {
		return "ImplementOfInterface.fun2";
	}
}

public class MultipleInheritance {
	public static void main(String[] args) {
		ParentInterface1 foo = null;

		foo = new ImplementOfInterface() { };
		System.out.println(foo.fun2());

		foo = new SonInterface() { };
		System.out.println(foo.fun2());

		foo = new ParentInterface1() {};
		System.out.println(foo.fun2());

	}
}