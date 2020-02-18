package com.ling.learn1405.synchronize;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 锁测试与超时
 *
 * Chapter14/com.ling.learn1405.synchronize.TryLockTest.java
 *
 * author lingang
 *
 * createTime 2019-12-16 22:19:33
 *
 */
public class TryLockTest {
	private static ReentrantLock lock = new ReentrantLock();
	private static Condition condition = lock.newCondition();

	public static void main(String[] args) {
		/* 这里的测试没有什么意义，只是罗列一下语法 */
		new Thread(new Runnable() {

			@Override
			public void run() {
				if (lock.tryLock()) {// 尝试能不能获得锁
					try {
						System.out.println("do something in thread 1");
						try {
							Thread.sleep(5000);
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
						// 如果获得了锁，做一些操作
					} finally {
						lock.unlock();
					}
				} else {
					// 如果没获得锁，就做另一些操作
					System.out.println("do other thing in thread 1");
				}
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					if (lock.tryLock(7, TimeUnit.SECONDS)) { // 等待7秒，如果等到锁就走if分支，否则走else否则
						System.out.println("do something in thread 2");
					} else {
						System.out.println("do other thing in thread 2");
					}
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		try {
			lock.lockInterruptibly();// 这个方法相当于lock.tryLock(timeout,
										// TimeUnit)方法第一个时间参数为无限长时

		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			condition.await(2, TimeUnit.SECONDS);// 如果等待时间到了返回false，如果被其他线程唤醒返回true，如果等待时被中断抛出InterruptedException
			condition.awaitUninterruptibly();// 如果等待时被中断不会抛出InterruptedException
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
