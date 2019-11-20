package com.ling.learn0706.debugtips;

import java.io.IOException;
import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 处理非捕获的异常
 * 
 * 当线程中有没被捕获的异常时，当前线程Thread的defaultUncaughtExceptionHandler处理器会处理该异常，默认情况是输出到System.err，可以使用Thread.setDefaultUncaughtExceptionHandler设置新的处理器，将异常信息输出到其他地方
 *
 * Chapter7/com.ling.learn0706.debugtips.HandleUncaugthExceptionTest.java
 *
 * author lingang
 *
 * createTime 2019-11-11 20:07:48
 *
 */
public class HandleUncaugthExceptionTest {
	public static void main(String[] args) throws IOException {
		/* 下面的语句将设置对未捕获异常的输出方式 */
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			/*
			 * 这里处理没有catch的异常，可以选择将异常信息打印到各处：控制台、文件、网络等， 也可以选择什么也不做，即不输出异常信息
			 */
			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println(e);
			}
		});
		throw new IOException();
	}
}
