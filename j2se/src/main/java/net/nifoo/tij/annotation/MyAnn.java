package net.nifoo.tij.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
/*
 * 定义注解 
 * 注解中含有两个元素 id 和 description
 * description 元素 有默认值 "no description"
 */
public @interface MyAnn {
	public String value();

	public String description() default "no description";
}
