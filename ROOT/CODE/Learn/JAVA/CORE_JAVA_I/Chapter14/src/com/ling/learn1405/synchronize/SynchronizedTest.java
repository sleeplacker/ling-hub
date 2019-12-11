package com.ling.learn1405.synchronize;

import java.util.Arrays;
import java.util.concurrent.locks.ReentrantLock;

/**
 * synchronized关键字
 *
 * Chapter14/com.ling.learn1405.synchronize.SynchronizedTest.java
 *
 * author lingang
 *
 * createTime 2019-12-11 13:29:06
 *
 */
public class SynchronizedTest {
	private static int count = 0;
	private static boolean[] completeResult = new boolean[50];
	private static boolean[] done = new boolean[50];
	private ReentrantLock lock = new ReentrantLock();// 创建锁对象

	public static void main(String[] args) throws InterruptedException {
		final SynchronizedTest obj = new SynchronizedTest();
		for (int i = 0; i < 50; ++i) { // 让50条线程同时启动
			final int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					// obj.inc1000_1();// 调用带锁的方法来进行累加
					// obj.inc1000_2();// 调用带锁的方法来进行累加
					// obj.inc1000_3();// 调用带锁的方法来进行累加
					obj.inc1000_4();// 调用带锁的方法来进行累加
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

		/* wait,notifyAll和notify方法 */
		synchronized (obj) {
			obj.wait();// 类似ReentrantLock的await方法，必须在同步块内使用，否则会报IllegalMonitorStateException
			obj.notify();// 类似ReentrantLock的signal方法，必须在同步块内使用，否则会报IllegalMonitorStateException
			obj.notifyAll();// 类似ReentrantLock的signalAll方法，必须在同步块内使用，否则会报IllegalMonitorStateException
		}
	}

	public void inc1000_1() {// 没有同步的方法
		for (int i = 0; i < 1000; ++i) {
			++count;
		}

	}

	public void inc1000_2() {// 使用ReentrantLock对象来同步
		lock.lock();// 获得锁，其他线程再走到这一行是阻塞
		try {
			for (int i = 0; i < 1000; ++i) {
				++count;
			}
		} finally {
			lock.unlock();// 释放锁，其他线程又可以竞争或者锁
		}

	}

	public synchronized void inc1000_3() {// 使用synchronized关键字修饰的方法来同步
		for (int i = 0; i < 1000; ++i) {
			++count;
		}

	}

	public void inc1000_4() {// 使用synchronized代码块来同步
		synchronized (this) {
			for (int i = 0; i < 1000; ++i) {
				++count;
			}
		}
	}
}
