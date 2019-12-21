package com.ling.learn1407.concurrentcollection;

import java.util.ArrayList;
import java.util.List;

/**
 * 线程不安全的容器-列表
 * 
 * 会出现很多java.util.ConcurrentModificationException，原因和UnSafeHahMap.java中的一样
 *
 * Chapter14/com.ling.learn1407.concurrentcollection.UnSafeArrayList.java
 *
 * author lingang
 *
 * createTime 2019-12-22 00:24:51
 *
 */
public class UnSafeArrayList {
	private static List<String> unsafeList = new ArrayList<>();

	public static void main(String[] args) {

		for (int i = 0; i < 1000; ++i) {
			new Thread(new Runnable() {
				public void run() {
					unsafeList.add("AA");
					System.out.println(unsafeList);
				}
			}).start();
		}
	}
}
