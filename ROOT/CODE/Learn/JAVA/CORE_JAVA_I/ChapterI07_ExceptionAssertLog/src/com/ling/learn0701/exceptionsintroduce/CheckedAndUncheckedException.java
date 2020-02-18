package com.ling.learn0701.exceptionsintroduce;

/**
 * 受查异常和非受查异常
 * 
 * 1. 对于受查异常，编译器要求必须捕获或者抛出
 * 
 * 2. 对于非受查异常，编译器不要求捕获或者抛出
 * 
 * 3. 什么是受查异常和非受查异常，参看readme.txt
 * 
 *
 * Chapter7/com.ling.learn0701.exceptionsintroduce.CheckedAndUncheckedException.java
 *
 * author lingang
 *
 * createTime 2019-10-31 01:38:53
 *
 */
public class CheckedAndUncheckedException {

	// public static void fun1() {
	public static void fun1() throws CheckedException {
		throw new CheckedException();// 对于受查异常，编译器要求必须捕获或者抛出
	}

	public static void fun2() {
		throw new UncheckedException(); // 对于非受查异常，编译器不要求捕获或者抛出
	}

}

@SuppressWarnings("serial")
class CheckedException extends Exception {// 受查异常

}

@SuppressWarnings("serial")
class UncheckedException extends RuntimeException {// 非受查异常

}
