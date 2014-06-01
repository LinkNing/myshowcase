package net.nifoo.tij.i18n;

import java.text.Collator;
import java.text.ParseException;
import java.text.RuleBasedCollator;
import java.util.Locale;

import org.apache.commons.lang3.StringUtils;

public class CollarExample {

	public static void main(String[] args) {
		Locale locale = Locale.US;
		String[] words = { "peach", "apricot", "Apple", "Grape", "lemon" };

		System.out.println("========================");
		test1(locale, words);
		System.out.println();

		words = new String[]{ "哆", "啦", "A", "梦" };
		System.out.println("========================");
		test2(locale, words);

	}

	static void test1(Locale locale, String[] words) {
		Collator myCollator = Collator.getInstance(locale);
		sortStrings(myCollator, words);

		printStrings(words);
	}

	static void test2(Locale locale, String[] words) {
		/*
		String rule1 = ("< a,A < b,B < c,C < d,D < e,E < f,F < g,G " //
				+ "< h,H < i,I < j,J < k,K < l,L < m,M < n,N "
				+ "< o,O < p,P < q,Q < r,R < s,S < t,T "
				+ "< u,U < v,V < w,W < x,X < y,Y < z,Z");

		String rule2 = ("< A < B < C < D < E < F < G < H < I < J < K < L < M < N < O < P < Q < R < S < T < U < V < W < X < Y < Z "
				+ "< a < b < c < d < e < f < g < h < i < j < k < l < m < n < o < p < q < r < s < t < u < v < w < x < y < z");
				 */
		String rule1 = "<哆<啦<A<梦";
		String rule2 = "<梦<A<啦<哆";

		try {
			RuleBasedCollator enCollator = new RuleBasedCollator(rule1);
			sortStrings(enCollator, words);
			printStrings(words);
			System.out.println();

			RuleBasedCollator spCollator = new RuleBasedCollator(rule2);
			sortStrings(spCollator, words);
			printStrings(words);
		} catch (ParseException pe) {
			System.out.println("Parse exception for rules");
		}

	}

	static void sortStrings(Collator collator, String[] words) {
		String tmp;
		for (int i = 0; i < words.length; i++) {
			for (int j = i + 1; j < words.length; j++) {
				if (collator.compare(words[i], words[j]) > 0) {
					tmp = words[i];
					words[i] = words[j];
					words[j] = tmp;
				}
			}
		}
	}

	static void printStrings(String[] words) {
		System.out.println(StringUtils.join(words, '\n'));
	}

}
