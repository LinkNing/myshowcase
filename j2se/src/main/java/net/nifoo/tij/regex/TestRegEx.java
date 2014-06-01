package net.nifoo.tij.regex;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;

public class TestRegEx {

	public static void main(String[] args) {
		String input = "abccabcccabc";
		String regex = "abc+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			System.out.printf("match \"%s\" at %d - %d \n", matcher.group(), matcher.start(), matcher.end());
		}

		String[] infos = pattern.split(input); // Pattern.split()
		System.out.println(StringUtils.join(infos, ','));

		infos = input.split(regex); // String.split()
		System.out.println(StringUtils.join(infos, ','));

		System.out.println(matcher.replaceFirst("X"));
		input.replaceFirst(regex, "X");
		input.replaceAll(regex, "X");

		StringBuffer sb = new StringBuffer();
		pattern = Pattern.compile("[aeiou]");
		matcher = pattern.matcher(input);
		matcher.reset("fdsaeqgvikldfasu");
		// reset 将matcher用于一个新的字符序列
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group().toUpperCase());
		}
		matcher.appendTail(sb);
		System.out.println(sb);
		
		byte b = 0b100;
		System.out.println(b);
		
		Map<String, String> myMap = new HashMap<>();  
	}

}
