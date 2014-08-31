package net.nifoo.tij.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

public class GenericTypeReflectDemo {

	public static void main(String[] args) {
		try {
			runDemo();
		} catch (NoSuchFieldException | SecurityException e) {
			e.printStackTrace();
		}
	}
	
	public static void runDemo() throws NoSuchFieldException, SecurityException {
		Pair pair = new Pair();
		Field field = pair.getClass().getDeclaredField("myList"); //myList的类型是List 
		System.out.println(field.getType().getName()); // 输出  java.util.List
		System.out.println(field.getGenericType()); // 输出 java.util.List<java.lang.String>

		Type type = field.getGenericType();
		if (type instanceof ParameterizedType) {
			ParameterizedType paramType = (ParameterizedType) type;
			Type[] actualTypes = paramType.getActualTypeArguments();
			for (Type aType : actualTypes) {
				if (aType instanceof Class) {
					Class clz = (Class) aType;
					System.out.println(clz.getName()); //输出java.lang.String         
				}
			}
		}
	}
	
	static class Pair {
		private List<String> myList;

		public List<String> getMyList() {
			return myList;
		}

		public void setMyList(List<String> myList) {
			this.myList = myList;
		}
	}
}
