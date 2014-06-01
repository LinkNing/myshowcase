package net.nifoo.tij.i18n;


public class Unicode6 {
	public static void main(String[] args) {
		System.out.println( "\u0628" + "\u064e" + "\u064a" + "\u0652" + "\u067a" + "\u064f");
		System.out.println("\u0075\u00a8\u00fc");
		System.out.println("\u901F\u5EA6\u4E2D\u56FD");
		
		int ch = 0x901F;
		System.out.println((char)ch);
		System.out.println(Character.toChars(ch));
		System.out.println(Character.codePointAt("速度中国", 0));
		
		System.out.printf("Character %c is valid.%n", ch);

		
		//System.out.println("\u1F37A");
	}

}