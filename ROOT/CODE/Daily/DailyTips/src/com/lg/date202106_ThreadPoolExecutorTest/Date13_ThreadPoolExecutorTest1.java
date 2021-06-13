package com.lg.date202106_ThreadPoolExecutorTest;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池 ThreadPoolExecutor 测试1：观察线程池各个部分的使用顺序
 * 
 * 从打印结果可以看出，当工作(work)不断到达线程池时，按照下面的顺序创建线程
 * 
 * 1. 如果工作数量没超过 coreSize 时，创建新线程
 * 2. 如果 coreSize 满了，还有工作到达，则将工作加入到队列
 * 3. 如果 coreSize 和队列都满了，还有工作到达，则创建新线程(数量<=maxSize-coreSize)
 * 4. 如果 队列和 maxSize 都满了，则进入拒绝策略
 * 
 * 关于工作到达顺序和运行顺序：到达顺序和运行顺序可能不同，因为当 coreSize 和队列满了以后，
 * 还有新工作到达会创建新线程，所以这是到达的工作会先于已在队列中的工作运行。
 *
 * DailyTips/com.lg.date202106_ThreadPoolExecutorTest.Date13_ThreadPoolExecutorTest1.java
 *
 * author ling
 *
 * createTime 2021-06-13 07:25:47
 *
 */
public class Date13_ThreadPoolExecutorTest1 {
	public static void main(String[] args) {
		ThreadPoolExecutor tpExecutor = new ThreadPoolExecutor(1, 2, 10, TimeUnit.MINUTES, new ArrayBlockingQueue<>(5),
				new DiscardAndLog());
		for (int i = 1; i <= 10; ++i) {
			Work w = new Work(i);
			tpExecutor.execute(w);
			System.out.println(tpExecutor.toString().substring(tpExecutor.toString().indexOf('[')));
		}

	}
}