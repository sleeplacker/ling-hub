package com.ling.learn0804.boundingtype;

import java.util.ArrayList;

/**
 * 被限定的类型变量
 * 
 * 1. 限定类型变量语法：<T extends
 * T1&T2&T3...Tn>其中的T1至Tn可以是类或接口，限定T必须实现T1-Tn中的接口且是T1-Tn中类的子类；如果限定类型中包含类，那么类必须是第一个限定类型且只能声明一个限定类型类
 *
 * Chapter8/com.ling.learn0804.boundingtype.BoundingTypeTest.java
 *
 * author lingang
 *
 * createTime 2019-11-12 23:24:27
 *
 */
public class BoundingTypeTest {
	public static void main(String[] args) {
		System.out.println(getMinMax(new Integer[] { 4, 5, 6, 1, 2, 3 }));
		System.out.println(getMinMax(new String[] { "c", "a", "d" }));
	}

	public static <T extends Comparable> Pair<T> getMinMax(T[] ts) {
		if (ts == null || ts.length == 0)
			return null;
		T min = ts[0];
		T max = ts[0];
		for (T t : ts) {
			if (min.compareTo(t) > 0) {
				min = t;
			} else if (max.compareTo(t) < 0) {
				max = t;
			}
		}
		return new Pair<>(min, max);
	}

	public static <T extends ArrayList & Comparable & Cloneable> void test() { // OK
	}
	/* 编译报错，ArrayList是类而不是接口，应该放到限定类型的最前面 */
	// public static <T extends Comparable & Cloneable & ArrayList> void test()
	// { }
}

class Pair<T> {
	private T min;
	private T max;

	/**
	 * @param min
	 * @param max
	 */
	public Pair(T min, T max) {
		this.min = min;
		this.max = max;
	}

	@Override
	public String toString() {
		return "Pair [min=" + min + ", max=" + max + "]";
	}

}