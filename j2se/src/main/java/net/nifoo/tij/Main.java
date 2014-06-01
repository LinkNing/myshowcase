package net.nifoo.tij;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

public class Main {

	public static void main(String[] args) {
		try {
			URI uri = new URI("http", "example.com", "/hello world/", "");
			URL url = uri.toURL();
			System.out.println(url.toString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
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