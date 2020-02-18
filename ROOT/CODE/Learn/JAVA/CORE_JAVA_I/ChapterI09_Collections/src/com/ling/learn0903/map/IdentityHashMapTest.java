package com.ling.learn0903.map;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

/**
 * 标识散列映射
 *
 * Chapter09/com.ling.learn0903.map.IdentityHashMapTest.java
 *
 * author lingang
 *
 * createTime 2019-11-22 23:58:20
 *
 */
public class IdentityHashMapTest {
	public static void main(String[] args) {
		Map<String, Object> hashmap = new HashMap<>();
		Map<String, Object> idenmap = new IdentityHashMap<>();
		hashmap.put("A", "aa");
		idenmap.put("A", "aa");
		hashmap.put("D", "dd");
		idenmap.put("D", "dd");
		hashmap.put("C", "cc");
		idenmap.put("C", "cc");
		hashmap.put("C", "cccc");// 插入键值是同一个对象的元素
		idenmap.put("C", "cccc");// 插入键值是同一个对象的元素
		hashmap.put("B", "bb");
		idenmap.put("B", "bb");
		hashmap.put(new String("B"), "bbbb");// 插入键值相同但不属于同一个对象的元素
		idenmap.put(new String("B"), "bbbb");// 插入键值相同但不属于同一个对象的元素
		/* HashMap覆盖了键B和键C对应的元素，因为HashMap比较键值是否相等使用的是equals方法 */
		System.out.println(hashmap);// {A=aa, B=bbbb, C=cc D=dd}
		/*
		 * IdentityHashMap只覆盖了键C对应的元素，因为IdentityHashMap比较键值是否相等使用的是==，
		 * 必须是同一个对象才认为两个键值相等，所以new
		 * String("B")和"B"是不同的键值，但是"C"和"C"是同一个对象，所以是同一个键值
		 */
		System.out.println(idenmap);// {C=cc, B=bbbb, D=dd, B=bb A=aa}

	}
}
