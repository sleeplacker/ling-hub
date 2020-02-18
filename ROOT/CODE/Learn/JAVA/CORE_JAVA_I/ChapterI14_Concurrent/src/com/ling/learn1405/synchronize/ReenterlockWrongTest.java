package com.ling.learn1405.synchronize;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁的错误用法
 *
 * Chapter14/com.ling.learn1405.synchronize.ReenterlockWrongTest.java
 *
 * author lingang
 *
 * createTime 2019-12-11 00:02:19
 *
 */
public class ReenterlockWrongTest {
	private static int count = 0;
	private static boolean[] completeResult = new boolean[50];
	private static boolean[] done = new boolean[50];

	public static void main(String[] args) {
		for (int i = 0; i < 50; ++i) { // 让50条线程同时启动
			final int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					ReentrantLock lock = new ReentrantLock();
					/*
					 * 这里的锁是不会起作用的，因为run方法只有当前线程能进入，而其他线程时不能进入的，所以不会有其他线程来竞争这个锁，
					 * 每个线程会自己新建一个锁，这样使用锁和不使用没什么区别
					 */
					lock.lock();
					for (int i = 0; i < 1000; ++i) {// 对count变量累加1000次
						++count;
					}
					lock.unlock();
					completeResult[index] = true;// 每条线程累加完1000次后记录完成标志
				}
			}).start();
		}
		Arrays.fill(done, true);
		while (!Arrays.equals(completeResult, done)) {
			// 如果50条线程的累加任务没执行完，则程序空转
		}
		// 至此50条线程累加任务完成，count变量的值应该为50000
		System.out.println(count);// 40668，锁没有起作用
	}
}
