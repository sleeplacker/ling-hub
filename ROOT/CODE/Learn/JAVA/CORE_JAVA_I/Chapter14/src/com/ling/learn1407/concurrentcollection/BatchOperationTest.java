package com.ling.learn1407.concurrentcollection;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class BatchOperationTest {
	public static void main(String[] args) {
		ConcurrentHashMap<String, Long> chm = new ConcurrentHashMap<String, Long>();
		chm.put("AA", 4l);
		chm.put("BB", 1l);
		chm.put("CCC", 7l);
		chm.put("DD", 8l);
		chm.put("EE", 9l);

		/* 查找操作，第一个参数为同时启动多少条线程来并行执行操作 */
		System.out.println("查找操作");
		System.out.println(chm.search(1, (k, v) -> v > 5l ? v : null));// 查找键和值
		System.out.println(chm.searchKeys(Long.MAX_VALUE, k -> k.length() > 2 ? k : null));// 查找键
		System.out.println(chm.searchValues(Long.MAX_VALUE, v -> v < 5 ? v : null));// 查找值
		System.out.println(chm.searchEntries(Long.MAX_VALUE, en -> en.getValue() % 4 == 0 ? en : null));// 查找键/值对

		/* 应用函数 */
		System.out.println("\n应用函数");
		chm.forEach(2, (k, v) -> System.out.println(k + "->" + v));// 形式1，直接应用函数
		System.out.println();
		chm.forEach(2, (k, v) -> k + "=" + v, System.out::println);// 形式2，先转换，再应用函数
		System.out.println();
		chm.forEachKey(1, System.out::println);
		System.out.println();
		chm.forEachKey(1, k -> k + "#", System.out::println);
		// 后面的forEachValue和forEachEntry同理

		/* 归约操作 */
		System.out.println("\n归约操作");
		System.out.println(chm.reduce(1, (k, v) -> k.length() + v, Long::sum));// 因为二元操作，所以针对键和值的方法要先将键和值转换为1个字段
		System.out.println(chm.reduceKeys(1, (k1, k2) -> k1 + k2));
		System.out.println(chm.reduceValues(1, (v1, v2) -> v1 + v2));
		System.out.println(chm.<Long>reduceEntries(2, en -> en.getValue(), (v1, v2) -> v1 * v2));
		ConcurrentHashMap<String, Long> temp = new ConcurrentHashMap<String, Long>();//临时map，为了让下面能取个Entry对象
		temp.put("#", 0l);
		System.out.println(chm.reduceEntries(1, (en1, en2) -> {
			Map.Entry<String, Long> newEntry = temp.entrySet().iterator().next();
			newEntry.setValue((long) Math.pow(en1.getValue(), en2.getValue()));
			return newEntry;
		}));
	}

}
