package net.nifoo.tij.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ReflectDemo {

	public static void main(String[] args) {

		MyClass myClass = new MyClass(0); //一般做法
		myClass.increase(2);
		System.out.println("Normal -> " + myClass.count);
		
		try {
			Constructor<MyClass> constructor = MyClass.class.getConstructor(int.class); //获取构造方法
			MyClass myClassReflect = constructor.newInstance(10); //创建对象
			Method method = MyClass.class.getMethod("increase", int.class); //获取方法
			method.invoke(myClassReflect, 5); //调用方法
			Field field = MyClass.class.getField("count"); //获取域
			System.out.println("Reflect -> " + field.getInt(myClassReflect)); //获取域的值
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	static class MyClass {
		private String privateField = "Private Field";
		public int count;

		public MyClass(int i) {
			count = i;
		}

		public void increase(int i) {
			count += i;
		}
	}
}
