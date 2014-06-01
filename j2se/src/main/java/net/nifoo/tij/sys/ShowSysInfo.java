package net.nifoo.tij.sys;

import java.util.Map;
import java.util.Properties;

public class ShowSysInfo {

	public static void main(String[] args) {
		showSysEnv();
		//		showSysProperties();
	}

	public static void showSysEnv() {
		Map<String, String> envs = System.getenv();
		for (Map.Entry<String, String> env : envs.entrySet()) {
			System.out.printf("[%20s]: %s\n", env.getKey(), env.getValue());
		}
	}

	public static void showSysProperties() {
		Properties properties = System.getProperties();
		for (Map.Entry env : properties.entrySet()) {
			System.out.printf("[%20s]: %s\n", env.getKey(), env.getValue());
		}
	}

}