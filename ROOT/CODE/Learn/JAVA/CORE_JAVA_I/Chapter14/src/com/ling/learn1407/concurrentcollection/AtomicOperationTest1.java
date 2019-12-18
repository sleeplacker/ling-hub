package com.ling.learn1407.concurrentcollection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发散列映射的原子操作-正确示例1
 * 
 * 使用原子类来作为值，这样累加操作就是原子的，不会有线程安全问题
 * 
 *
 * Chapter14/com.ling.learn1407.concurrentcollection.AtomicOperationTest1.java
 *
 * author lingang
 *
 * createTime 2019-12-18 21:19:23
 *
 */
public class AtomicOperationTest1 {
	private static ConcurrentHashMap<String, Long> chm = new ConcurrentHashMap<>();
	private static AtomicInteger ai = new AtomicInteger(0);

	public static void main(String[] args) {
		chm.put("AA", 0l);
		for (int i = 0; i < 10000; ++i) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					/*
					 * merge为原子方法，参数含义依次是：key，操作数，原value和操作数的运算
					 * 
					 * 除了merge，ConcurrentHashMap还有
					 * compute,computeIfAbsent和computeIfPresent等原子方法，
					 * 如果merge和compute方法中的函数式接口返回null，那么这个键/值对会被删除
					 */
					chm.merge("AA", 1l, Long::sum);
					ai.incrementAndGet();// 表示这条线程执行完了
				}
			}).start();
		}
		while (ai.intValue() < 10000) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(chm);// 结果是10000
	}
}
