package net.nifoo.java8.streams;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

	public static void main(String[] args) {
		Map<Integer, String> map = buildMap();

		traverseMap(map);
		computeMap(map);
		removeFromMap(map);
	}

	public static Map<Integer, String> buildMap() {
		Map<Integer, String> map = new HashMap<>();

		for (int i = 0; i < 10; i++) {
			map.putIfAbsent(i, "val" + i);
			map.putIfAbsent(i, "repeat" + i); // 重复插入将无效
		}

		return map;
	}

	public static void traverseMap(Map<Integer, String> map) {
		map.forEach((id, val) -> System.out.printf("%d: %s\n", id, val));
	}

	public static void computeMap(Map<Integer, String> map) {
		map.computeIfPresent(3, (num, val) -> val + num);
		map.get(3); // val33

		map.computeIfPresent(9, (num, val) -> null);
		System.out.printf("does the 9th element exists? %s \n", map.containsKey(9)); // false

		map.computeIfAbsent(23, num -> "val" + num);
		// map.containsKey(23); // true
		System.out.printf("does the 23th element exists? %s \n", map.containsKey(23)); // false

		map.computeIfAbsent(3, num -> "bam");
		// map.get(3); // val33
		System.out.printf("does the 3th element changed? %s \n", !map.get(3).equals("val33")); // false

		traverseMap(map);
	}

	public static void removeFromMap(Map<Integer, String> map) {
		String value = null;

		// it doesn't work
		map.remove(3, "val3");
		value = map.get(3); // val33
		System.out.println(value);

		// this works
		map.remove(3, "val33");
		value = map.get(3); // null
		System.out.println(value);
	}

}