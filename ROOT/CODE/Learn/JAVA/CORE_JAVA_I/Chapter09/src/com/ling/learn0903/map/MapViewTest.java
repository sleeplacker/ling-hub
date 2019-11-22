package com.ling.learn0903.map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 映射视图
 *
 * Chapter09/com.ling.learn0903.map.MapViewTest.java
 *
 * author lingang
 *
 * createTime 2019-11-22 12:39:10
 *
 */
public class MapViewTest {
	public static void main(String[] args) throws InterruptedException {
		Map<String, Object> m = new HashMap<>();
		m.put("A", "aa");
		m.put("B", "bb");
		m.put("C", "cc");
		m.put("D", "dd");
		m.put("E", "bb");

		/* 1. 键集 */
		System.out.println("键集");
		Set<String> keys = m.keySet();
		keys.forEach(k -> System.out.println(k));

		/* 2. 值集 */
		System.out.println("值集");
		Collection<Object> values = m.values();
		values.forEach(v -> System.out.println(v));

		/* 3. 键/值对集 */
		System.out.println("键/值对集");
		Set<Map.Entry<String, Object>> kvs = m.entrySet();
		kvs.forEach(kv -> System.out.println("key=" + kv.getKey() + ", value=" + kv.getValue()));

		/* 4. 上面的3种视图返回的结果都不能新增元素 */
		// keys.add("E");//java.lang.UnsupportedOperationException
		// values.add("ee");// java.lang.UnsupportedOperationException
		// kvs.add(null);//java.lang.UnsupportedOperationException

		/* 5. 可以上面3种视图返回的结果删除元素 */
		keys.remove("A");// OK，删除了键对应的键/值对
		System.out.println(m);
		/* 删除了值所对应的键/值对，如果该值对应多个键，则只删除第一个键/值对 */
		values.remove("bb");// OK，但值删除了B=bb，E=bb没被删除，结果为：{C=cc, D=dd, E=bb}。
		System.out.println(m);

		kvs.remove(kvs.iterator().next());//OK，删除第一个键/值对
		System.out.println(m);
	}
}
