package com.ling.learn1407.concurrentcollection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

/**
 * 并发散列映射的原子操作-正确示例2
 * 
 * 使用并发散列容器的原子方法来完成累加操作
 *
 * Chapter14/com.ling.learn1407.concurrentcollection.AtomicOperationTest2.java
 *
 * author lingang
 *
 * createTime 2019-12-18 21:20:53 
 *
 */
public class AtomicOperationTest2 {
	private static ConcurrentHashMap<String, AtomicLong> chm = new ConcurrentHashMap<>();
	private static AtomicInteger ai = new AtomicInteger(0);

	public static void main(String[] args) {
		chm.put("AA", new AtomicLong(0));
		for (int i = 0; i < 10000; ++i) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					AtomicLong value = chm.get("AA");
					value.incrementAndGet();
					chm.put("AA", value);
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
