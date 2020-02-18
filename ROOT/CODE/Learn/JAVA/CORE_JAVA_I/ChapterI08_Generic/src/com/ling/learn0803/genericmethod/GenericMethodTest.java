package com.ling.learn0803.genericmethod;

/**
 * 泛型方法
 * 
 * 1. 调用泛型方法格式：<T>方法名(参数)
 * 
 * 2. 调用泛型方法时，可以省略类型参数，此时由编译器推导类型
 * 
 * 3. 省略类型参数时，有可能会出现问题，就是当编译器推导出的参数列表中的参数类型不一致时 此时的编译错误为：
 * Type mismatch: cannot convert from Number&Comparable<?> to double，
 * 这是因为编译器推导参数类型的时候推导出多个超类或接口，所以无法确定参数类型，
 * 解决办法是将参数列表中的参数类型转为一致
 *
 * Chapter8/com.ling.learn0803.genericmethod.GenericMethodTest.java
 *
 * author lingang
 *
 * createTime 2019-11-12 01:04:07
 *
 */
public class GenericMethodTest {
	public static void main(String[] args) {
		/* 调用泛型方法 */
		System.out.println(GenericMethodTest.<String>getMiddle("aaa", "bbb", "ccc"));
		/* 调用泛型方法时，可以省略类型参数，此时由编译器推导类型 */
		System.out.println(GenericMethodTest.getMiddle("aaa", "bbb", "ccc"));
		/*
		 * 省略类型参数时，有可能会出现问题，就是当编译器推导出的参数列表中的参数类型不一致时
		 * 
		 * 编译错误：Type mismatch: cannot convert from Number&Comparable<?> to
		 * double
		 * 
		 */
		// double middle = GenericMethodTest.getMiddle(1.5, 2, 3);
		/* 解决办法，将参数列表中的参数类型转为一致 */
		double middle = GenericMethodTest.getMiddle(1.5, (double) 2, (double) 3);
		System.out.println(middle);
	}

	public static <T> T getMiddle(T... ts) {
		return ts[ts.length / 2];
	}
}
