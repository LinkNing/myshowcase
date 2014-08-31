package net.nifoo.tij.annotation;

import java.lang.reflect.Method;
import java.util.Arrays;

public class AnnotationDemo {

	@AnnotationDef(1)
	public void method_1() {
	}

	@AnnotationDef(value = 2, description = "hello method_1")
	public void method_2() {
	}

	@AnnotationDef(value = 3, description = "many options", option = { "option 1", "option 2" })
	public void method_3() {
	}

	/*
	 * 解析注解，将AnnotationDef类 所有被注解方法 的信息打印出来
	 */
	public static void main(String[] args) {
		Method[] methods = AnnotationDemo.class.getDeclaredMethods();
		for (Method method : methods) {
			// 判断方法中是否有指定注解类型的注解
			boolean hasAnnotation = method.isAnnotationPresent(AnnotationDef.class);
			if (hasAnnotation) {
				// 根据注解类型返回方法的指定类型注解
				AnnotationDef annotation = method.getAnnotation(AnnotationDef.class);
				System.out.printf("AnnotationDef( method = %s, value = %s, description = %s, options = %s \n",
						method.getName(), annotation.value(), annotation.description(),
						Arrays.toString(annotation.option()));
			}
		}
	}
}
