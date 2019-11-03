package com.ling.learn0702.catchexception;

import java.io.IOException;

/**
 * 捕获后再抛出，这种做法的用途
 * 
 * 1. 捕获的是一种异常，二抛出的是另一种异常：比如捕获的是受查异常，而方法声明中没有抛出异常，那么可以将受查异常放到运行时异常的原因中，然后抛出
 * 
 * 2. 捕获异常后，需要打印日志信息
 * 
 * 关于java7前的问题：在java7之前的编译环境，想要抛出比方法声明的异常更通用的类型，编译器会报错
 * 但是在java7之后编译器会跟踪异常来自try块来判断，当然，catch块中的异常变量不能被改变，否则编译也会报错
 * 
 *
 * Chapter7/com.ling.learn0702.catchexception.CatchThenThrowTest.java
 *
 * author lingang
 *
 * createTime 2019-11-01 17:40:36
 *
 */
public class CatchThenThrowTest {
	public static void main(String[] args) throws IOException {
		fun1();
		fun2();
	}

	public static void fun1() {
		try {
			throw new IOException();
		} catch (Exception e) {
			RuntimeException re = new RuntimeException();
			re.initCause(e);// 将捕获异常设置为新异常的原因再抛出
			throw re;
		}
	}

	public static void fun2() throws IOException {
		try {
			throw new IOException();
		} catch (Exception e) {
			System.out.println(e); // 捕获异常后，打印日志信息，再原样抛出
			// e= new RuntimeException(); // 要想抛出比方法声明异常更通用的异常，则不能再catch块中修改异常变量
			/*
			 * 如果在java7之前的编译环境，下面一行会有编译错误：Unhandled exception type Exception
			 * 因为方法声明是抛出更具体的异常，而这里抛出的是更通用的异常，编译器不允许这样；
			 * 但是在java7之后编译器会跟踪异常来自try块来判断，当然，catch块中的异常变量不能被改变，否则编译也会报错
			 */
			throw e;
		}
	}

}
