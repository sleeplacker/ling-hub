package com.ling.learn1407.concurrentcollection;

import java.util.HashMap;
import java.util.Map;

/**
 * 线程不安全的容器
 * 
 * 多运行几次会出现很多java.util.ConcurrentModificationException，如果不出，加大循环次数
 * 
 * 原因是：HashMap中的桶里面是链表结构，所以遍历桶中链表的时候会使用到迭代器，前面已经知道，如果迭代器创建之后，链表的结构又被修改了，迭代器就会失效，再使用原来的迭代器就会抛出java.util.ConcurrentModificationException
 *
 * Chapter14/com.ling.learn1407.concurrentcollection.UnSafeHahMap.java
 *
 * author lingang
 *
 * createTime 2019-12-18 00:03:06
 *
 */
public class UnSafeHahMap {
	private static Map<Integer, Integer> map = new HashMap<>(5);

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
