package net.nifoo.tij.regex;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestRegEx {

	public static void main(String[] args) {
		String input = "abccdabcccabc";
		String regex = "abc+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			System.out.printf("match \"%s\" at %d - %d \n", matcher.group(), matcher.start(), matcher.end());
		}

		String[] infos = Pattern.compile("a").split(input); // Pattern.split()
		System.out.printf("pattern.split: %s \n", Arrays.toString(infos));

		infos = input.split("a"); // String.split()
		System.out.printf("string.split: %s \n", Arrays.toString(infos));

		System.out.printf("matcher.replaceFirst: %s \n", matcher.replaceFirst("X"));
		System.out.printf("string.replaceFirst: %s \n", input.replaceFirst(regex, "X"));
		System.out.printf("string.replaceAll: %s \n", input.replaceAll(regex, "X"));
		input.replaceAll(regex, "X");

		StringBuffer sb = new StringBuffer();
		pattern = Pattern.compile("[aeiou]");
		matcher = pattern.matcher(input);
		matcher.reset("fdsaeqgvikldfasu"); // reset 将matcher用于一个新的字符序列
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group().toUpperCase());
		}
		matcher.appendTail(sb);
		System.out.println(sb);

		Pattern p = Pattern.compile("cat");
		Matcher m = p.matcher("one cat two cats in the yard");
		sb = new StringBuffer();
		while (m.find()) {
			m.appendReplacement(sb, "dog");
		}
		m.appendTail(sb);
		System.out.println(sb.toString());

	}

}
