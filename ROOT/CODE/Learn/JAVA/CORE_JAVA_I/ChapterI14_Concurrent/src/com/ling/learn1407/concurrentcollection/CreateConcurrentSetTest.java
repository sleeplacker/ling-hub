package com.ling.learn1407.concurrentcollection;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 创建并发集(Set)视图
 *
 * 没有并发集相关的类，而只有并发映射，要想得到并发集，可以通过并发映射得到并发集的视图
 *
 * Chapter14/com.ling.learn1407.concurrentcollection.CreateConcurrentSetTest.java
 *
 * author lingang
 *
 * createTime 2019-12-21 23:36:21
 *
 */
public class CreateConcurrentSetTest {
	public static void main(String[] args) {
		/* 使用ConcurrentHashMap的静态方法创建并发集 */
		Set<Long> ccSet = ConcurrentHashMap.<Long>newKeySet();
		ccSet.add(10l);// 可以新增元素
		ccSet.add(30l);
		ccSet.add(20l);
		ccSet.remove(10l);// 也可以删除元素
		System.out.println(ccSet);

		/* 从ConcurrentHashMap对象获取并发集 */
		ConcurrentHashMap<String, String> conMap = new ConcurrentHashMap<>();
		conMap.put("AA", "aaaaa");
		conMap.put("BB", "bbbbb");
		Set<String> setFromMap = conMap.keySet();
		// setFromMap.add("CC");//
		// java.lang.UnsupportedOperationException，因为添加key到Set中会影响原Map中的数据，而直接添加key无法添加value，所以不支持添加操作
		setFromMap.remove("AA");// 删除操作可以做，会直接删除掉Map中的键/值对
		System.out.println(setFromMap);

		/* 从ConcurrentHashMap对象获取带默认值的并发集 */
		Set<String> setWithDefaultValue = conMap.keySet("-----");
		setWithDefaultValue.add("DD");// 可以添加
		setWithDefaultValue.add("EE");
		setWithDefaultValue.remove("DD");// 可以删除
		System.out.println(setWithDefaultValue);
	}
}
