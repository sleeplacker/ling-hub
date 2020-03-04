package com.ling.learn0806.processor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.METHOD, ElementType.TYPE })//表明定义的注解可以注解方法，类和接口等
@Retention(RetentionPolicy.SOURCE)//表明使用源码方式来解析该注解，即该注解在编译时会生成源文件
public @interface ToString {
	boolean includeName() default true;
}