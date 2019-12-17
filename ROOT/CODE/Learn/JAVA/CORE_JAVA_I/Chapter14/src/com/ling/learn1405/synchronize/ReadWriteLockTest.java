package com.ling.learn1405.synchronize;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读写锁
 *
 * Chapter14/com.ling.learn1405.synchronize.ReadWriteLockTest.java
 *
 * author lingang
 *
 * createTime 2019-12-17 13:06:19
 *
 */
public class ReadWriteLockTest {
	private String data;
	private ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();
	private Lock rlock = rwLock.readLock();// 创建读锁
	private Lock wlock = rwLock.writeLock();// 创建写锁

	public static void main(String[] args) {

	}

	public String getData() {
		/* 读操作时使用读锁，多线程可以同时获得读锁，然而这和不使用锁有什么区别呢：区别是读锁虽然和读锁不互斥，但是和写锁互斥 */
		rlock.lock();
		try {
			return data;
		} finally {
			rlock.unlock();
		}
	}

	public void setData(String data) {
		/*写操作时使用写锁，写锁跟读锁和写锁都互斥*/
		wlock.lock();
		try {
			this.data = data;
		} finally {
			wlock.unlock();
		}
	}

}
