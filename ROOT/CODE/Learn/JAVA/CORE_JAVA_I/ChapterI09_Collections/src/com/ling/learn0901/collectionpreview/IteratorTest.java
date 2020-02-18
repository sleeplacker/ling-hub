package com.ling.learn0901.collectionpreview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/**
 * 迭代器使用
 *
 * Chapter09/com.ling.learn0901.collectionpreview.IteratorTest.java
 *
 * author lingang
 *
 * createTime 2019-11-20 21:41:45
 *
 */
public class IteratorTest {
	// CollectionTypeHierarchy
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.addAll(Arrays.asList("aa", "bb", "cc"));
		Iterator<String> iter = list.iterator();
		while (iter.hasNext()) {
			/*
			 * 1. 每次调用Iterator.remover()方法之前必须调用Iterator.next()方法获得元素，否则会有运行时异常
			 */
			// iter.remove();// 运行时异常：java.lang.IllegalStateException
			String e = iter.next();
			if ("bb".equals(e)) {
				iter.remove();
			}
		}
		System.out.println(list);

		/* 2. jdk8新增了这个方法来遍历集合元素 */
		list.forEach(item -> System.out.println(item));
	}
}
