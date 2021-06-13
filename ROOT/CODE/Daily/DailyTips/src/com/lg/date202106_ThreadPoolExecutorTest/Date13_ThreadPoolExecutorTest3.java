package com.lg.date202106_ThreadPoolExecutorTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 ThreadPoolExecutor 测试3：当队列相当大时(足以容纳所有到达的工作) 
 * 
 * 此时，这些工作会在 coreSize 个线程中排队运行，而 maxSize 不会发挥作用
 *
 * DailyTips/com.lg.date202106_ThreadPoolExecutorTest.Date13_ThreadPoolExecutorTest3.java
 *
 * author ling
 *
 * createTime 2021-06-13 08:08:14 
 *
 */
public class Date13_ThreadPoolExecutorTest3 {
	public static void main(String[] args) {
		ThreadPoolExecutor tpExecutor = new ThreadPoolExecutor(1, 2, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(15),
				new RunAndLog());
		for (int i = 1; i <= 10; ++i) {
			Work w = new Work(i);
			tpExecutor.execute(w);
			System.out.println(tpExecutor.toString().substring(tpExecutor.toString().indexOf('[')));
		}

	}
}