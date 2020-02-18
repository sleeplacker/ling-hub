package com.ling.learn0902.collectionimpl;

import java.util.PriorityQueue;

/**
 * 优先级队列
 * 
 * 1. 优先级队列的迭代器遍历是按照元素添加顺序
 * 
 * 2. 优先级队列的删除(出队)顺序是：每次删除的元素都是剩下元素中最小的元素，即正序排序中最前面的那个元素
 *
 * Chapter09/com.ling.learn0902.collectionimpl.PriorityQueueTest.java
 *
 * author lingang
 *
 * createTime 2019-11-21 22:10:30
 *
 */
public class PriorityQueueTest {
	public static void main(String[] args) {
		PriorityQueue<String> ps = new PriorityQueue<>();
		ps.add("Aa");
		ps.add("Cc");
		ps.add("Bb");
		ps.add("Dd");

		/* 迭代器遍历顺序 */
		System.out.println("迭代器遍历顺序");
		for (String s : ps) {
			System.out.println(s);
		}

		/* 删除元素(出队)顺序 */
		System.out.println("\n出队顺序");
		while (!ps.isEmpty()) {
			System.out.println(ps.remove());
		}
	}
}
