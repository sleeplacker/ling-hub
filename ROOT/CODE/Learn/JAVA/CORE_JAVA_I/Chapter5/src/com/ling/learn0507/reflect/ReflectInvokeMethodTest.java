package com.ling.learn0507.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 使用反射机制调用类方法
 *
 * Chapter5/com.ling.learn0507.reflect.ReflectInvokeMethodTest.java
 *
 * author lingang
 *
 * createTime 2019-10-21 13:29:15
 *
 */
public class ReflectInvokeMethodTest {
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException {
		Method square = ReflectInvokeMethodTest.class.getMethod("square", double.class); // 获取Method对象
		Method sqrt = Math.class.getMethod("sqrt", double.class);

		printTable(1, 10, square);
		printTable(1, 10, sqrt);
	}

	public static double square(double x) {
		return x * x;
	}

	public static void printTable(double from, double to, Method f)
			throws IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		System.out.println(f);
		for (double x = from; x <= to; ++x) {
			System.out.printf("%10.4f  |  %10.4f%n", x, f.invoke(null, x)); // 使用Method对象调用类方法
		}
	}
}
