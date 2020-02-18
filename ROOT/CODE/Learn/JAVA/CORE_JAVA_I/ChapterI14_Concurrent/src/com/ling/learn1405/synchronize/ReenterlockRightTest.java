package com.ling.learn1405.synchronize;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁的正确用法
 *
 * Chapter14/com.ling.learn1405.synchronize.ReenterlockRightTest.java
 *
 * author lingang
 *
 * createTime 2019-12-11 00:07:38
 *
 */
public class ReenterlockRightTest {
	private static int count = 0;
	private static boolean[] completeResult = new boolean[50];
	private static boolean[] done = new boolean[50];
	private ReentrantLock lock = new ReentrantLock();//创建锁对象

	public static void main(String[] args) {
		final ReenterlockRightTest obj = new ReenterlockRightTest();
		for (int i = 0; i < 50; ++i) { // 让50条线程同时启动
			final int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					obj.inc1000();//调用带锁的方法来进行累加
					completeResult[index] = true;// 每条线程累加完1000次后记录完成标志
				}
			}).start();
		}
		Arrays.fill(done, true);
		while (!Arrays.equals(completeResult, done)) {
			// 如果50条线程的累加任务没执行完，则程序空转
		}
		// 至此50条线程累加任务完成，count变量的值应该为50000
		System.out.println(count);// 50000，说明锁起作用了
	}

	public void inc1000() {
		lock.lock();// 获得锁，其他线程再走到这一行是阻塞
		try {
			for (int i = 0; i < 1000; ++i) {
				++count;
			}
		} finally {
			lock.unlock();//释放锁，其他线程又可以竞争或者锁 
		}

	}
}
