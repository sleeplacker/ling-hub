package com.lg.date202106_ThreadPoolExecutorTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 ThreadPoolExecutor 测试2：设置拒绝策略为在主线程运行
 * 
 * 可以看到当 maxSize 和队列都用完以后，还有工作到达，会在主线程中执行
 * 工作，这时主线程阻塞，因此不能处理新的工作请求。
 *
 * DailyTips/com.lg.date202106_ThreadPoolExecutorTest.Date13_ThreadPoolExecutorTest2.java
 *
 * author ling
 *
 * createTime 2021-06-13 07:36:35
 *
 */
public class Date13_ThreadPoolExecutorTest2 {
	public static void main(String[] args) {
		ThreadPoolExecutor tpExecutor = new ThreadPoolExecutor(1, 2, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5),
				new RunAndLog());
		for (int i = 1; i <= 10; ++i) {
			Work w = new Work(i);
			tpExecutor.execute(w);
			System.out.println(tpExecutor.toString().substring(tpExecutor.toString().indexOf('[')));
		}

	}
}