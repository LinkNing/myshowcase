package net.nifoo.tij.i18n;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class DecimalFormateExample {
	public static void main(String[] args) {
		Number[] values = new Number[] { 123456.789, 123456.789, 123.78, 12345.67, 12345.67 };
		String[] patterns = new String[] { "###,###.###", "###.##", "000000.000", "$###,###.###", "\u00A5###,###.###" };
		Locale[] locs = new Locale[] { Locale.US, Locale.GERMANY, Locale.FRANCE };

		for (int i = 0; i < values.length; i++) {
			test1(values[i], patterns[i]);
		}

		System.out.println("\n===================================\n");
		
		for (int i = 0; i < locs.length; i++) {
			test2(123456.789, "###,###.###", locs[i]);
		}

	}

	static void test1(Number value, String pattern) {
		DecimalFormat myFormatter = new DecimalFormat(pattern);
		String output = myFormatter.format(value);
		System.out.println(value + " " + pattern + " " + output);
		//System.out.printf("[%f] %S %s \n", value, pattern, output);
	}

	static void test2(Number value, String pattern, Locale loc) {
		NumberFormat nf = NumberFormat.getNumberInstance(loc);
		DecimalFormat df = (DecimalFormat) nf;
		df.applyPattern(pattern);
		String output = df.format(value);
		System.out.println(pattern + " " + output + " " + loc.toString());
	}

}
