package net.nifoo.tij.reflect;

public class ReflectFoo {
	
	public static <T> T getStr(Object o){
		System.out.println(String.valueOf(o));
		return (T)o;
	}
	
	public static void main(String[] args) {
		int i = getStr(23);
		System.out.println(i);
		String str = getStr("fdsa");
		float f = getStr(12.3f);
	}

}