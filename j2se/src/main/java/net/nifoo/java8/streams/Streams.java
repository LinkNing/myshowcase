package net.nifoo.java8.streams;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;

public class Streams {

	public static void main(String[] args) {
		List<String> stringCollection = new ArrayList<>();
		stringCollection.add("ddd2");
		stringCollection.add("aaa2");
		stringCollection.add("bbb1");
		stringCollection.add("aaa1");
		stringCollection.add("bbb3");
		stringCollection.add("ccc");
		stringCollection.add("bbb2");
		stringCollection.add("ddd1");

		System.out.print("以Stream方式遍历List中所有元素：");
		stringCollection.forEach(e -> visit(e));
		System.out.println();

		baseTest(stringCollection);

		mapTest(stringCollection);

		reducedTest(stringCollection);

		parallelTest();
	}

	/**
	 * java.util.Stream表示了某一种元素的序列，在这些元素上可以进行各种操作。 <br/>
	 * Stream操作可以是中间操作，也可以是完结操作。完结操作会返回一个某种类型的值，而中间操作会返回流对象本身，
	 * 并且你可以通过多次调用同一个流操作方法来将操作结果串起来。<br/>
	 * Stream是在一个源的基础上创建出来的，例如java.util.Collection中的list或者set（map不能作为Stream的源）。<br/>
	 * Stream操作往往可以通过顺序或者并行两种方式来执行。
	 */
	public static void baseTest(List<String> stringCollection) {
		System.out.print("取以a开头的元素并排序：");
		stringCollection //
				.stream() //
				.filter(e -> e.startsWith("a")) // 中间操作
				.sorted((a, b) -> a.compareTo(b)) // 中间操作
				.forEach(e -> visit(e)); // 完结操作
		System.out.println();

		long startsWithB = stringCollection.stream()
				.filter((s) -> s.startsWith("b")).count();
		System.out.print("取以b开头的元素并计数：" + startsWithB);
		System.out.println();

		// 以上操作不影响stringCollection本身的元素及其顺序
		System.out.print("在List取Stream并进行操作后，不影响List中本身的元素及其顺序：");
		stringCollection.forEach(e -> visit(e));
		System.out.println();
	}

	/**
	 * map是一个对于流对象的中间操作，通过给定的方法，它能够把流对象中的每一个元素对应到另外一个对象上。
	 * 下面的例子就演示了如何把每个string都转换成大写的string.
	 */
	public static void mapTest(List<String> stringCollection) {
		System.out.print("把List中的对象替换成其他对象：");
		stringCollection.stream() //
				// .map(String::toUpperCase) //
				.map(e -> String.format("%s:%s", e, e.toUpperCase())) //
				.sorted((a, b) -> b.compareTo(a)) //
				.forEach(e -> visit(e));
		System.out.println();
	}

	/**
	 * reduce是一个终结操作，它能够通过某一个方法，对元素进行削减操作。该操作的结果会放在一个Optional变量里返回。
	 */
	public static void reducedTest(List<String> stringCollection) {
		System.out.print("把List中的元素reduce成一个：");

		Optional<String> reduced = stringCollection //
				.stream() //
				.sorted() //
				.reduce((s1, s2) -> s1 + "#" + s2);

		reduced.ifPresent(System.out::println);
	}

	public static void parallelTest() {
		int max = 2_000_000;
		List<String> values = new ArrayList<>(max);
		Stream.generate(() -> UUID.randomUUID()).limit(max) //
				.forEach(e -> values.add(e.toString()));
		System.out.printf("There are %d elements:\n", max);

		long t0, t1, count, millis;

		t0 = System.nanoTime();
		count = values.stream().sorted().count();
		t1 = System.nanoTime();

		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.printf("\tsequential sort took: %d ms \n", millis);

		t0 = System.nanoTime();
		count = values.parallelStream().sorted().count();
		t1 = System.nanoTime();

		millis = TimeUnit.NANOSECONDS.toMillis(t1 - t0);
		System.out.printf("\tparallel sort took: %d ms \n", millis);

	}

	public static void visit(String e) {
		System.out.printf("[%s]\t", e);
	}
}
