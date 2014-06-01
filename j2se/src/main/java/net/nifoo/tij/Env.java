package net.nifoo.tij;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class Env {

	public static void main(String[] args) {
		showSysEnv();
		
		Calendar cal = GregorianCalendar.getInstance();
		System.out.println(cal.get(Calendar.DAY_OF_WEEK) == Calendar.TUESDAY);
	}

	public static void test() {
		List<String> list = new ArrayList<String>();
		list.add("dfad");
		list.add("bbb");
		list.add("");

		String info = StringUtils.collectionToDelimitedString(list, ",", "'", "'");

		System.out.println(info);
	}
	
	public static void showSysEnv() {
		Map<String,String> envs = System.getenv();
		for (Map.Entry<String, String> env : envs.entrySet()) {
			System.out.printf("[%10s]: %s\n", env.getKey(), env.getValue());
		}
	}

}