package com.ling.learn1407.concurrentcollection;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 线程安全的容器
 * 
 * 运行多少次都不会出现异常，因为CopyOnWriteArrayList为线程安全的列表
 *
 * Chapter14/com.ling.learn1407.concurrentcollection.SafeCopyOnWriteArrayListTest.java
 *
 * author lingang
 *
 * createTime 2019-12-18 00:56:45
 *
 */
public class SafeCopyOnWriteArrayListTest {
	private static List<String> safeList = new CopyOnWriteArrayList<>();// CopyOnWriteArrayList为线程安全的列表

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
