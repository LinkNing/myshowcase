package net.nifoo.java8.streams;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import net.nifoo.common.Gender;
import net.nifoo.common.User;

public class MapReduceDemo {

	public static void main(String[] args) {
		List<User> users = getUsers();

		traverseUsers(users);

		reduceAvg(users);
		reduceSum(users);
		collectAvg(users);

		// 获取年龄大于20的用户列表
		users.parallelStream().filter(p -> p.getAge() > 20).collect(Collectors.toList())
				.forEach(p -> System.out.println(p.toString()));

		// 按性别统计用户数
		Map<Gender, Integer> map = users.parallelStream().collect(
				Collectors.groupingBy(User::getGender, Collectors.summingInt(p -> 1)));
		System.out.println(map);

		// 按性别获取用户名称
		Map<Gender, List<String>> map2 = users.stream().collect(
				Collectors.groupingBy(User::getGender, Collectors.mapping(User::getName, Collectors.toList())));
		System.out.println(map2);

		// 按性别求年龄的总和
		Map<Gender, Integer> map3 = users.stream().collect(
				Collectors.groupingBy(User::getGender, Collectors.reducing(0, User::getAge, Integer::sum)));

		System.out.println(map3);

		// 按性别求年龄的平均值
		Map<Gender, Double> map4 = users.stream().collect(
				Collectors.groupingBy(User::getGender, Collectors.averagingInt(User::getAge)));
		System.out.println(map4);

	}

	private static List<User> getUsers() {
		return Arrays.asList( //
				new User(1, "张三", LocalDate.parse("2002-06-30"), Gender.MALE), //
				new User(2, "李四", LocalDate.parse("1995-08-24"), Gender.FEMALE), //
				new User(3, "王五", LocalDate.parse("1988-03-12"), Gender.MALE), //
				new User(4, "赵六", LocalDate.parse("1985-10-05"), Gender.FEMALE) //
				);
	}

	private static void traverseUsers(Collection<User> users) {
		users.stream().forEach(
				p -> {
					System.out.printf("{id: %d, name: %s, birthday: %s, age: %s, gender: %s} \n", p.getId(),
							p.getName(), p.getBirthday(), p.getAge(), p.getGender());
				});
	}

	// 注意，reduce操作每处理一个元素总是创建一个新值， Stream.reduce适用于返回单个结果值的情况.
	// 获取所有用户的平均年龄
	private static void reduceAvg(Collection<User> users) {
		// mapToInt的pipeline后面可以是average,max,min,count,sum
		double avg = users.parallelStream().mapToInt(User::getAge).average().getAsDouble();

		System.out.println("reduceAvg User Age: " + avg);
	}

	// 获取所有用户的年龄总和
	private static void reduceSum(Collection<User> users) {
		double sum = users.parallelStream().mapToInt(User::getAge).reduce(0, (x, y) -> x + y); // 可以简写为.sum()

		System.out.println("reduceSum User Age: " + sum);
	}

	// 获取所有男性用户的平均年龄
	private static void collectAvg(Collection<User> users) {
		// 与stream.reduce方法不同，Stream.collect修改现存的值，而不是每处理一个元素，创建一个新值
		Averager averageCollect = users.parallelStream() //
				.filter(p -> p.getGender() == Gender.MALE) //
				.map(User::getAge) //
				.collect(Averager::new, Averager::accept, Averager::combine);

		System.out.println("Average age of male members: " + averageCollect.average());
	}

}