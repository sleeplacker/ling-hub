package com.ling.learn0603.lambda;

import java.util.function.IntConsumer;
import java.util.function.Predicate;

/**
 * 尽量使用jdk中已定义的函数式接口
 *
 * Chapter6/com.ling.learn0603.lambda.UseExistedFunctionInterface.java
 *
 * author lingang
 *
 * createTime 2019-10-25 02:21:01
 *
 */
public class UseExistedFunctionInterface {
	public static void main(String[] args) {
		
		fun(10, System.out::println);// val -> System.out.println(val)
		
		fun2(10,iVal -> iVal>5);
	}

	public static void fun(int cnt, IntConsumer ic) { // 使用线程的IntConsumer函数式接口
		for (int i = 0; i < cnt; ++i) {
			ic.accept(i);
		}
	}
	
	public static void fun2(int cnt, Predicate<Integer> ip) { // 使用线程的IntConsumer函数式接口
		for (int i = 0; i < cnt; ++i) {
			System.out.println(Predicate.isEqual(i).and(Predicate.isEqual(2)).test(i)); // Predicate是函数式接口，但是可以定义and，or等有默认实现的方法
		}
	}
}

@FunctionalInterface // 标注这是一个函数式接口，如果定义了多个抽象方法，编译器会报错
interface MI {
	void f1();
//	void f2();//Invalid '@FunctionalInterface' annotation; MI is not a functional interface
}
