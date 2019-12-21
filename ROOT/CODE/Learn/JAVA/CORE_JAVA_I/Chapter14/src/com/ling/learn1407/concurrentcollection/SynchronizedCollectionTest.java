package com.ling.learn1407.concurrentcollection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 同步包装器
 * 
 * 可以使用Collections.synchronized方法来将非线程安全的容器包装为线程安全的
 * 包括：Collections.synchronizedCollection，Collections.synchronizedMap，Collections.synchronizedList，Collections.synchronizedSet等方法
 *
 * Chapter14/com.ling.learn1407.concurrentcollection.SynchronizedCollectionTest.java
 *
 * author lingang
 *
 * createTime 2019-12-22 00:31:47
 *
 */
public class SynchronizedCollectionTest {
	private static List<String> safeList = Collections.synchronizedList(new ArrayList<>());// 包装后已为线程安全的List

	public static void main(String[] args) {
		for (int i = 0; i < 1000; ++i) {
			new Thread(new Runnable() {
				public void run() {
					safeList.add("AA");
					System.out.println(safeList);
				}
			}).start();
		}
	}
}
