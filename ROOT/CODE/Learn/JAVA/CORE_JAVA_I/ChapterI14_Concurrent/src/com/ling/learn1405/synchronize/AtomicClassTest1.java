package com.ling.learn1405.synchronize;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类测试1
 * 
 * 这里没有使用锁，也没有使用synchronized，也能得到正确结果，因为原子类中的原子方法能保证方法执行过程中不会被中断
 * 
 *
 * Chapter14/com.ling.learn1405.synchronize.AtomicClassTest1.java
 *
 * author lingang
 *
 * createTime 2019-12-16 10:57:01incrementAndGet
 *
 */
public class AtomicClassTest1 {
	private static AtomicInteger count = new AtomicInteger(0);// AtomicInteger为原子类
	private static boolean[] completeResult = new boolean[50];
	private static boolean[] done = new boolean[50];

	public static void main(String[] args) {
		for (int i = 0; i < 50; ++i) { // 让50条线程同时启动
			final int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < 1000; ++i) {// 对count变量累加1000次
						count.incrementAndGet();// 原子方法，中间不会被中断，所以不会有线程安全问题
					}
					completeResult[index] = true;// 每条线程累加完1000次后记录完成标志
				}
			}).start();
		}
		Arrays.fill(done, true);
		while (!Arrays.equals(completeResult, done)) {
			// 如果50条线程的累加任务没执行完，则程序空转
		}
		System.out.println(count);// 50000
	}

}
