package com.ling.learn0702.catchexception;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * finally子句
 * 
 * 1.  建议使用try-catch加try-finally的方式来解耦try-catch-finally，这样能提高代码的清晰度。详细看main方法中的try语句
 * 
 * 2. try语句可以不包含catch块，而只包含finally 
 * 
 * 3. finally块中的return语句会覆盖try块中的return语句
 * 
 * 4. finally中的异常会覆盖catch中异常被抛出，这样是有问题的，因为通常catch中的异常更有用，所以要使用带资源的try语句
 *
 * Chapter7/com.ling.learn0702.catchexception.FinallyClauseTest.java
 *
 * author lingang
 *
 * createTime 2019-11-04 00:57:46
 *
 */
public class FinallyClauseTest {
	public static void main(String[] args) {

		/*
		 * 建议使用下面的方式来解耦try-catch-finally，这样能提高代码的清晰度。这里内层的try-finally专注于关闭资源
		 * ，而外层的try-catch专注处理操作中的异常，包括finally中的异常
		 */
		try {
			InputStream is = new FileInputStream("");
			try {
				is.close();
			} finally {// try语句可以不包含catch块，而只包含finally
			}
		} catch (Exception e) {

		}

		System.out.println(fun1());// 2，说明finally块中的return语句会覆盖try块中的return语句

		fun2();// 抛出了finally中的异常，说明当catch中抛出一个异常，而finally中也抛出另一个异常时，finally中的异常会覆盖catch中异常被抛出，这样是有问题的，因为通常catch中的异常更有用
	}

	@SuppressWarnings("finally")
	public static int fun1() {
		try {
			return 1;
		} catch (Exception e) {

		} finally {
			return 2;
		}
	}

	@SuppressWarnings("finally")
	public static int fun2() {
		try {
			throw new IOException();
		} catch (Exception e) {
			throw e;
		} finally {
			throw new RuntimeException();
		}
	}
}
