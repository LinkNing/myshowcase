package net.nifoo.tij.resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.Locale;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

public class PropFoo {

	private static final String PROP_FILE = "resource.xml";

	public static void writeProp(String key, String value) {
		Properties prop = new Properties();

		prop.setProperty(key, value);

		URL rootPath = PropFoo.class.getClassLoader().getResource(".");

		OutputStream out = null;
		try {
			File file = new File(rootPath.getFile(), PROP_FILE);
			out = new FileOutputStream(file);
			prop.storeToXML(out, "测试", "UTF-8");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			IOUtils.closeQuietly(out);
		} catch (IOException e) {
			e.printStackTrace();
			IOUtils.closeQuietly(out);
		}

		System.out.printf("写入成功  =》%s: %s \n", key, value);

	}

	public static void readProp(String key, Locale locale) {
		Properties prop = new Properties();

		InputStream in = null;
		try {
			in = PropFoo.class.getClassLoader().getResourceAsStream(PROP_FILE);
			prop.loadFromXML(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			IOUtils.closeQuietly(in);
		} catch (IOException e) {
			e.printStackTrace();
			IOUtils.closeQuietly(in);
		}

		String value = prop.getProperty(key);
		
		System.out.printf("读取成功  =》%s: %s \n", key, value);
	}

	public static void main(String[] args) {
		String key = "name";
		String value = "张三";

		writeProp(key, value);
		readProp(key, null);
	}

}
