package com.ling.learn0903.map;

import java.util.HashMap;
import java.util.Map;

/**
 * 映射
 *
 * Chapter09/com.ling.learn0903.map.MapTest.java
 *
 * author lingang
 *
 * createTime 2019-11-22 11:05:23
 *
 */
public class MapTest {
	public static void main(String[] args) {
		Map<String, Object> m = new HashMap<>();
		m.put("A", "aa");
		m.put("B", "bb");
		m.put("C", "cc");
		System.out.println(m.get("D"));
		/* getOrDefault方法为不存在的key值返回默认值 */
		System.out.println(m.getOrDefault("D", "空"));
		System.out.println(m.getOrDefault("C", "空"));

		/* put方法返回key对应的原值 */
		System.out.println(m.put("C", "cccc"));// 返回原值：cc
		System.out.println(m.put("D", "dd"));// 返回原值：null

		/* remove方法返回key对应的值 */
		System.out.println(m.remove("D"));// 返回删除的key值对应的value：dd
		System.out.println(m.remove("E"));// 无该key，返回null

		/* jdk8提供了很方便遍历map的方法 */
		m.forEach((k, v) -> System.out.println("key=" + k + ", value=" + v));

		/* 根据key/value对，替换value */
		m.replaceAll((k, v) -> {
			if ("B".equals(k))
				return null;
			else
				return k + v;
		});
		System.out.println(m);
	}
}
