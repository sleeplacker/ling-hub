package com.lg.date202106_ThreadPoolExecutorTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 ThreadPoolExecutor 测试4：keepAliveTime
 * 
 * 可以看到，当线程池中的所有任务都执行完成后，在等待 keepAliveTime 时间后
 * 线程池中的线程会被释放，最终线程池中只剩下 coreSize 个线程
 *
 * DailyTips/com.lg.date202106_ThreadPoolExecutorTest.Date13_ThreadPoolExecutorTest4.java
 *
 * author ling
 *
 * createTime 2021-06-13 08:07:55 
 *
 */
public class Date13_ThreadPoolExecutorTest4 {
	public static void main(String[] args) {
		ThreadPoolExecutor tpExecutor = new ThreadPoolExecutor(1, 20, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1),
				new DiscardAndLog());
		for (int i = 1; i <= 10; ++i) {
			Work w = new Work(i);
			tpExecutor.execute(w);
		}
		for (int i = 0; i < 30; ++i) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(tpExecutor.toString().substring(tpExecutor.toString().indexOf('[')));
		}
	}
}