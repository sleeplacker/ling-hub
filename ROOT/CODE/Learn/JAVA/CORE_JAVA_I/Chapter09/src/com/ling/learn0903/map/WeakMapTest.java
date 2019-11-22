package com.ling.learn0903.map;

import java.util.WeakHashMap;

/**
 * 弱引用映射
 *
 * Chapter09/com.ling.learn0903.map.WeakMapTest.java
 *
 * author lingang
 *
 * createTime 2019-11-22 17:07:14
 *
 */
public class WeakMapTest {
	public static WeakHashMap<String, Object> wm = new WeakHashMap<>();

	public static void main(String[] args) throws InterruptedException {
		WeakHashMap<String, Object> wm = new WeakHashMap<>();
		wm.put(new String("A"), "aa");// 这里的键应该是对象，否则垃圾收集器可能回收不到
		wm.put(new String("B"), "bb");
		wm.put(new String("C"), "cc");
		wm.put("D", "dd");// 使用字面量"D"

		System.out.println(wm);// {C=cc, B=bb, A=aa, D=dd}
		System.gc();
		System.out.println(wm);// {D=dd}，使用字面量"D"作为键，那么垃圾收集器可能回收不到，所以这个元素也不会被回收
	}

}
