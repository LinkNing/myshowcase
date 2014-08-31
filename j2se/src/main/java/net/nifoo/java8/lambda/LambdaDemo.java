package net.nifoo.java8.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class LambdaDemo {

	public static void main(String[] args) {
		List<String> names = new ArrayList<>();
		names.add("1.张三");
		names.add("2.李四");
		names.add("3.王五");

		fun1(names);
		Message msg = () -> String.format("fun1: %s \n", names);
		pringMessage(msg);

		fun2(names);
		pringMessage(() -> String.format("fun2: %s \n", names));

		fun3(names);
		Info info1 = (a, b) -> String.format(a, b);
		pringMessage(info1.getInfo("fun3: %s \n", names));

		fun4(names);
		Info info2 = String::format;
		pringMessage(info2.getInfo("fun4: %s \n", names));

		new LambdaDemo().recursiveCall();
		System.out.printf("recursive call factorial: %d \n", factorial.apply(4));

	}

	public static void pringMessage(Message msg) {
		System.out.print(msg.getMessage());
	}

	public static void pringMessage(String info) {
		System.out.print(info);
	}

	public static void fun1(List<String> names) {
		Collections.sort(names, (String a, String b) -> {
			return b.compareTo(a);
		});
	}

	public static void fun2(List<String> names) {
		Collections.sort(names, (String a, String b) -> a.compareTo(b));
	}

	public static void fun3(List<String> names) {
		Collections.sort(names, (a, b) -> {
			return b.compareTo(a);
		});
	}

	public static void fun4(List<String> names) {
		Collections.sort(names, (a, b) -> a.compareTo(b));
	}

	/*
	 * factorial must be declared as an instance or static variable, then you
	 * can recursive call it.
	 */
	static UnaryOperator<Integer> factorial;

	public void recursiveCall() {
		factorial = i -> {
			return i == 0 ? 0 : i + factorial.apply(i - 1);
		};
	}

}

@FunctionalInterface
interface Message {
	String getMessage();
}

@FunctionalInterface
interface Info {
	String getInfo(String format, Object... arg);
}

@FunctionalInterface
interface UnaryOperator<T> {
	T apply(T i);
}
