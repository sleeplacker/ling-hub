package com.ling.learn1405.synchronize;

import java.util.Arrays;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 条件对象
 * 
 * 本例的条件对象可能会出现死锁，要强行退出程序
 *
 * Chapter14/com.ling.learn1405.synchronize.ReenterlockConditionTest.java
 *
 * author lingang
 *
 * createTime 2019-12-11 00:48:38
 *
 */
public class ReenterlockConditionTest {
	private static int count = 0;
	private static boolean[] completeResult = new boolean[5];
	private static boolean[] done = new boolean[5];
	private ReentrantLock lock = new ReentrantLock();// 创建锁对象
	private Condition condition = lock.newCondition();// 创建条件对象

	public static void main(String[] args) {
		final ReenterlockConditionTest obj = new ReenterlockConditionTest();
		for (int i = 0; i < 5; ++i) { // 让5条线程同时启动
			final int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						obj.inc10(index);
					} catch (InterruptedException e) {
						e.printStackTrace();
					} // 调用带锁的方法来进行累加
					completeResult[index] = true;// 每条线程累加完1000次后记录完成标志
				}
			}).start();
		}
		Arrays.fill(done, true);
		while (!Arrays.equals(completeResult, done)) {
			// 如果50条线程的累加任务没执行完，则程序空转
		}
		// 至此50条线程累加任务完成，count变量的值应该为500
		System.out.println(count);// 500，说明锁起作用了
	}

	public void inc10(int index) throws InterruptedException {
		lock.lock();// 获得锁，其他线程再走到这一行是阻塞
		try {
			for (int i = 0; i < 10; ++i) {
				while ((count - index) % 2 != 0) { // 当不满足条件时，进入等待(这里的条件没什么实际意义：当count和线程序号同为奇数或同为偶数才能进行累加)
					condition.await();
					System.out.println(index + "-" + i);
				}
				++count;
				condition.signalAll();// 当线程操作成功后，可能会给其他线程带来能运行的机会，通知所有其他线程解除等待状态，而去竞争锁来执行
			}
		} finally {
			lock.unlock();// 释放锁，其他线程又可以竞争或者锁
		}

	}
}
