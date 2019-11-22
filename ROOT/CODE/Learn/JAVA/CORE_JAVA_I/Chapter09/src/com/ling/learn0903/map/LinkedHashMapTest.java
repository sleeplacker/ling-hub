package com.ling.learn0903.map;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 链接散列集与映射
 *
 * Chapter09/com.ling.learn0903.map.LinkedHashMapTest.java
 *
 * author lingang
 *
 * createTime 2019-11-22 23:28:55
 *
 */
public class LinkedHashMapTest {
	public static void main(String[] args) {
		/* 下面的构造器生成的映射有如下功能：当调用Map的get或put方法时，会将当前操作的元素放到链表末尾 */
		Map<String, Object> m = new LinkedHashMap<>(15, 0.75f, true);
		m.put("A", "aa");
		m.put("D", "dd");
		m.put("C", "cc");
		m.put("B", "bb");
		System.out.println(m);// 初始顺序：{A=aa, D=dd, C=cc, B=bb}
		m.get("C");// 使用get方法访问key：C
		System.out.println(m);// C键值对被放到了链表末尾：{A=aa, D=dd, B=bb, C=cc}
		m.put("D", "ddd");// 使用put方法访问key：D
		System.out.println(m);// D键值对被放到了链表末尾：{A=aa, B=bb, C=cc, D=ddd}
	}

}
