package net.nifoo.tij.i18n;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatExample {
	public static void main(String[] args) {
		Locale[] locs = new Locale[] { //
		Locale.forLanguageTag("zh-CN"), Locale.US, Locale.GERMANY };

		for (Locale loc : locs) {
			test1(loc);
		}
		
		for (Locale loc : locs) {
			test2(loc);
		}
		
		for (Locale loc : locs) {
			test3(loc);
		}
		
		for (Locale loc : locs) {
			test4(loc);
		}
	}

	static void test1(Locale currentLocale) {
		Date today;
		String dateOut;
		DateFormat dateFormatter;

		dateFormatter = DateFormat.getDateInstance(DateFormat.DEFAULT, currentLocale);
		today = new Date();
		dateOut = dateFormatter.format(today);

		System.out.printf("[%12s] %s \n", dateOut, currentLocale.toString());
	}

	static void test2(Locale currentLocale) {
		Date today;
		String dateOut;
		DateFormat dateFormatter;

		dateFormatter = DateFormat.getTimeInstance(DateFormat.DEFAULT, currentLocale);
		today = new Date();
		dateOut = dateFormatter.format(today);

		System.out.printf("[%12s] %s \n", dateOut, currentLocale.toString());
	}

	static void test3(Locale currentLocale) {
		Date today;
		String dateOut;
		DateFormat dateFormatter;

		dateFormatter = DateFormat.getDateTimeInstance(DateFormat.DEFAULT, DateFormat.DEFAULT, currentLocale);
		today = new Date();
		dateOut = dateFormatter.format(today);

		System.out.printf("[%12s] %s \n", dateOut, currentLocale.toString());
	}
	
	static void test4(Locale currentLocale) {
		Date today;
		String dateOut;
		SimpleDateFormat formatter;

		formatter = new SimpleDateFormat("EEE d MMM yy", currentLocale);
		today = new Date();
		dateOut = formatter.format(today);


		System.out.printf("[%12s] %s \n", dateOut, currentLocale.toString());
	}

}
