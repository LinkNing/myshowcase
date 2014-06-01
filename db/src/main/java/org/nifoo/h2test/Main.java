package org.nifoo.h2test;

import java.net.URL;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		// 设置日志文件路径 
		URL url = App.class.getClassLoader().getResource("");
		System.setProperty("LOGDIR", url.getPath());

		ConfigurableApplicationContext appContext = new ClassPathXmlApplicationContext("applicationContext.xml");

		App app = appContext.getBean(org.nifoo.h2test.App.class);
		app.test();


		appContext.close();
	}

}
