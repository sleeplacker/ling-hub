package com.ling.learn1407.concurrentcollection;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 并发散列映射的原子操作-不安全的示例
 * 
 * 并发安全的容器的作用是保证在多线程环境下，不会破坏容器数据结构，并不能保证对容器的值进行非原子操作时能保持正确
 *
 * Chapter14/com.ling.learn1407.concurrentcollection.AtomicOperationTest.java
 *
 * author lingang
 *
 * createTime 2019-12-18 21:06:27
 *
 */
public class AtomicOperationTest {
	private static ConcurrentHashMap<String, Long> chm = new ConcurrentHashMap<>();
	private static AtomicInteger ai = new AtomicInteger(0);

	public static void main(String[] args) {
		chm.put("AA", 0l);
		for (int i = 0; i < 10000; ++i) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					Long value = chm.get("AA");
					++value;
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
		System.out.println(chm);// 结果不是10000，说明并发散列映射虽然是线程安全的，但是对值的操作不一定安全
	}
}
