package com.ling.learn0702.catchexception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.Map;

/**
 * 堆栈轨迹测试
 *
 * Chapter7/com.ling.learn0702.catchexception.StackTraceTest.java
 *
 * author lingang
 *
 * createTime 2019-11-04 16:40:06
 *
 */
public class StackTraceTest {
	public static void main(String[] args) {
		fun();

		System.out.println("\n***** 打印阶乘方法轨迹 *****");
		factorial(5);
	}

	public static void fun() {
		System.out.println("\n***** 获取异常的堆栈描述信息 *****");
		Throwable t = new Throwable("EX");
		StringWriter out = new StringWriter();
		t.printStackTrace(new PrintWriter(out));
		String description = out.toString();// 获取异常堆栈描述信息
		System.out.println(description);

		StackTraceElement[] frames = t.getStackTrace();// 获取异常的堆栈轨迹
		for (StackTraceElement frame : frames) {
			System.out.println(frame);
		}

		System.out.println("\n***** 打印所有线程的堆栈轨迹 *****");
		Map<Thread, StackTraceElement[]> map = Thread.getAllStackTraces();// 获取所有线程的堆栈轨迹
		for (Thread td : map.keySet()) {
			StackTraceElement[] stes = map.get(td);
			for (StackTraceElement ste : stes) {
				System.out.println(ste);
			}
		}
	}

	public static int factorial(int n) {
		System.out.println("*** factorial(" + n + ")");
		Throwable t = new Throwable();
		StackTraceElement[] stes = t.getStackTrace();
		for (StackTraceElement ste : stes) {
			System.out.println(ste);
		}
		if (n < 1) {
			return 1;
		}
		return n * factorial(n - 1);
	}

}
