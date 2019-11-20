package com.ling.learn0708.wildcardtypes;

import java.util.ArrayList;

/**
 * 通配符捕获
 *
 * Chapter7/com.ling.learn0708.wildcardtypes.WildcardTypeCatch.java
 *
 * author lingang
 *
 * createTime 2019-11-20 10:22:57
 *
 */
public class WildcardTypeCatch {
	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<>();
		list.add(0, 1);
		list.add(1, 2);
		swap(list);
		System.out.println(list);
	}

	// 交换list的前两个元素
	public static void swap(ArrayList<?> list) {
		/* 交换需要一个中间变量，但是不能用?来声明变量 */
		// ? first = list.get(0);//编译错误
		/* 所以只能借助辅助方法来进行交换，这时?会被辅助方法中的泛型参数T捕获 */
		swapHelper(list);
	}

	private static <T> void swapHelper(ArrayList<T> list) {
		T first = list.get(0);
		list.set(0, list.get(1));
		list.set(1, first);
	}
}
