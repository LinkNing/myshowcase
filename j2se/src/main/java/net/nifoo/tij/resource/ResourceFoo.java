package net.nifoo.tij.resource;

import java.util.Locale;
import java.util.ResourceBundle;

public class ResourceFoo {

	public static void getMessage(String key, Locale locale) {
		ResourceBundle bundle = ResourceBundle.getBundle("resource", locale);

		System.out.println(bundle.getString(key));
	}

	public static void main(String[] args) {
		String key = "name";

		getMessage(key, Locale.ENGLISH);
		getMessage(key, Locale.CHINESE);
		
		
	}

}
