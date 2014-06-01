package net.nifoo.tij.resource;

import java.util.Collection;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang.LocaleUtils;

public class LocaleFoo {

	public static void main(String[] args) {
		List<Locale> locales = LocaleUtils.availableLocaleList();

		printLocale(locales);
	}

	static void printLocale(Collection<Locale> locales) {
		for (Locale locale : locales) {
			System.out.println(locale);
		}
	}

}
