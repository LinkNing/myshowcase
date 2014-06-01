package net.nifoo.tij.i18n;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import org.apache.commons.lang3.LocaleUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LocaleExample {
	static final Logger logger = LoggerFactory.getLogger(LocaleExample.class);

	public static void main(String[] args) {
		List<Locale> locals = LocaleUtils.localeLookupList(Locale.forLanguageTag("zh-CN"));
		
		for (Locale locale : locals) {
			System.out.println(locale);
		}
		
		System.out.println(Locale.ROOT);

		test1();
	}

	public static void test1() {
		Locale local1 = Locale.forLanguageTag("zh-CN");
		Locale local2 = Locale.forLanguageTag("zh-cmn-Hans-CN");
		Locale local3 = Locale.CHINA;

		System.out.println(local1.equals(local2));
		System.out.println(local1.equals(local3));
		System.out.println(local2.equals(local3));
		//		System.out.println(local3.getScript());
		//		System.out.println(local3.getDisplayScript());

		System.out.println("================================");

		System.out.println(local1.toString());
		System.out.println(local2.toString());
		System.out.println(local2.toLanguageTag());

		System.out.println("================================");

		String outputString = new String();
		Locale[] thaiLocale = { new Locale("zh"), new Locale("zh", "CN"), Locale.forLanguageTag("zh-cmn-Hans-CN") };

		for (Locale locale : thaiLocale) {
			NumberFormat nf = NumberFormat.getNumberInstance(locale);
			outputString = outputString + locale.toString() + ": ";
			outputString = outputString + nf.format(573.34) + "\n";
		}

		System.out.println(outputString);
	}

	public static void test2() {
		Locale locale = null;
		locale = Locale.getDefault(Locale.Category.DISPLAY);
		logger.debug(locale.toString());
		locale = Locale.getDefault(Locale.Category.FORMAT);
		logger.debug(locale.toString());

		logger.debug(Locale.getDefault().toLanguageTag());
		logger.debug(Locale.ROOT.toString());
	}

}
