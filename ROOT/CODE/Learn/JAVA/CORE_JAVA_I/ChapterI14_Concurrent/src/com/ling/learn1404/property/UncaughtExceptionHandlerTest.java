package com.ling.learn1404.property;

import java.lang.Thread.UncaughtExceptionHandler;

/**
 * 未捕获异常处理器
 * 
 * 当未设置默认异常处理器和指定线程处理器时，如果线程run方法中出现未捕获异常会按照下面的步骤处理异常：
 * 
 * 1) 如果线程组有父线程组，那么父线程组的uncaughtException方法会被调用
 * 
 * 2) 否则，如果Thread.getDefaultUncaughtExceptionHandler()方法有返回处理器，则调用该处理器
 * 
 * 3) 否则，如果Throwable是ThreadDeath的实例，则什么都不做
 * 
 * 4) 否则，将线程名字和Throwable的栈轨迹输出到System.err上 
 *
 * Chapter14/com.ling.learn1404.property.UncaughtExceptionHandlerTest.java
 *
 * author lingang
 *
 * createTime 2019-12-09 22:33:30
 *
 */
public class UncaughtExceptionHandlerTest {
	public static void main(String[] args) {

		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				throw new RuntimeException("my runtime exception");
			}
		});
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				throw new RuntimeException("my runtime exception");
			}
		});
		/* 设置指定线程的未捕获异常处理器 */
		t1.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("每条线程独立的处理器：" + e);
			}
		});
		/* 设置所有线程共享的默认处理器 */
		Thread.setDefaultUncaughtExceptionHandler(new UncaughtExceptionHandler() {

			@Override
			public void uncaughtException(Thread t, Throwable e) {
				System.out.println("所有线程共享的默认处理器：" + e);
			}
		});
		t1.start();
		t2.start();
		/* 从运行结果可以看出：当设置了所有线程共享的默认处理器和指定线程处理器时，优先使用指定线程处理器 */
		// 运行结果如下：
		// 每条线程独立的处理器：java.lang.RuntimeException: my runtime exception
		// 所有线程共享的默认处理器：java.lang.RuntimeException: my runtime exception
	}
}
