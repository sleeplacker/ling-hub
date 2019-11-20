package com.ling.learn0702.catchexception;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 一次性catch多个异常
 * 
 * java7后可以一次捕获多个一次，用|分割，这样做的好处是更高效，因为字节码只包含一个对公共catch子句的代码块；
 * 当一次性捕获多个异常时，异常变量会是final的
 *
 * Chapter7/com.ling.learn0702.catchexception.CatchExceptionTest.java
 *
 * author lingang
 *
 * createTime 2019-10-31 14:47:41
 *
 */
public class CatchExceptionTest {
	public static void main(String[] args) {
		try {
			fun1(2);
		} catch (IOException | TimeoutException e) { // java7后可以一次捕获多个一次，用|分割，这样做的好处是更高效，因为字节码只包含一个对公共catch子句的代码块
			/*
			 * 下面一行会出现编译错误： The parameter e of a multi-catch block cannot be
			 * assigned
			 * 
			 * 当一次性捕获多个异常时，异常变量会是final的
			 */
			// e = null;
			e.printStackTrace();
		}
	}

	// 该方法会抛出多个异常
	public static void fun1(int iVal) throws IOException, TimeoutException {
		if (iVal == 1) {
			throw new IOException();
		} else {
			throw new TimeoutException();
		}

	}
}
