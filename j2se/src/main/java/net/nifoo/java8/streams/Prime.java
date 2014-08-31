package net.nifoo.java8.streams;

import java.util.stream.Stream;

public class Prime {

	private static Stream<Long> range(long start, long length) {
		return Stream.iterate(start, i -> ++i).limit(length);
	}

	private long countPrimes(int max) {
		return range(1, max).parallel().filter(this::isPrime).count();
	}

	private boolean isPrime(long n) {
		return n > 1;
		// && rangeClosed(2, (long) Math.sqrt(n)).noneMatch(
		// divisor -> n % divisor == 0);
	}

	public static void main(String[] args) {
		range(10, 90).forEach(System.out::println);
	}

}