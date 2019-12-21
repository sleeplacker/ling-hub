package com.ling.learn1407.concurrentcollection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 线程安全的容器
 * 
 * 运行多少次都不会出现java.util.ConcurrentModificationException，因为ConcurrentHashMap是线程安全的容器
 *
 * Chapter14/com.ling.learn1407.concurrentcollection.SafeConcurrentHashMapTest.java
 *
 * author lingang
 *
 * createTime 2019-12-18 00:56:45
 *
 */
public class SafeConcurrentHashMapTest {
	private static Map<Integer, Integer> map = new ConcurrentHashMap<>(5);

	public static void main(String[] args) {

		for (int i = 0; i < 1000; ++i) {
			final int j = i;
			new Thread(new Runnable() {
				public void run() {
					map.put(j % 100, j);
					System.out.println(map);
				}
			}).start();
		}
	}
}
