package net.nifoo.tij.lang;

public class StringIntern {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s1 = "test1";
		String s2 = new String("test1");
		System.out.println(s1 == s2);
		s2 = s2.intern();
		System.out.println(s1 == s2);
	}

}
