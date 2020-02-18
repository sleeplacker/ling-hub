package com.ling.learn0603.lambda;

import java.util.function.BiFunction;

/**
 * 函数式接口
 * 
 * 1. 只包含一个抽象方法的接口就是函数式接口
 * 
 * 2. 包含多个抽象方法的接口不是函数式接口
 * 
 * 3. 包含一个抽象方法和一个或多个有默认实现的方法也是函数式接口
 *
 * Chapter6/com.ling.learn0603.lambda.FunctionalInterfaceTest.java
 *
 * author lingang
 *
 * createTime 2019-10-24 23:11:44
 *
 */
public class FunctionalInterfaceTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {

		A ia = name -> System.out.println(name);// OK
		// B ib = name->System.out.println(name);// Wrong：The target type of
		// this expression must be a functional interface，
		C ic = name -> System.out.println(name);// OK

		BiFunction<String, String, Integer> comp = (self, other) -> self.length() - other.length(); // 可以将lambda表达式保存在BiFunction类型的变量中
		String[] ss = { "AAA", "BB", "CCC" };
		/*
		 * com.ling.learn0603.lambda.FunctionalInterface$$Lambda$1/989110044
		 * cannot be cast to java.util.Comparator
		 * BiFunction变量不能被强转为其他接口类型，尽管参数和返回类型是一样的
		 */
		// Arrays.sort(ss,(Comparator<String>)(comp));
	}
}

interface A {// 只包含一个抽象方法的接口是函数式接口
	void showName(String name);
}

interface B { // 包含两个抽象方法的接口不是函数式接口
	void showName(String name);

	String getName();
}

interface C { // 包含一个抽象方法和一个有默认实现的方法也是函数式接口
	void showName(String name);

	default String getName() {
		return null;
	}
}