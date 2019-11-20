package com.ling.learn0310.array;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Arrays.asList方法用法使用注意事项
 *
 * Chapter3/com.ling.learn0310.array.ArraysAsListTest.java
 *
 * author lingang
 *
 * createTime 2019-10-10 00:47:17 
 *
 */
public class ArraysAsListTest {
	public static void main(String[] args) {
		int[] initValues = new int[2];
		Integer[] objectValues = new Integer[2];
		/* 1. Arrays.asList方法要求传入的数组元素为对象类型，不能是原生类型 */
		System.out.println("1. Arrays.asList传入参数必须为对象数组");
		System.out.println(Arrays.asList(initValues)); // [[I@2a139a55]，打印的并不是数组中的元素，而是含一个对象元素的数组
		System.out.println(Arrays.toString(initValues)); // [0, 0]，Arrays.toString方法可以将原生数据数组格式化为好看的字符串
		System.out.println(Arrays.asList(objectValues)); // [null,
															// null]，打印了数组中的元素

		/*
		 * 2. Arrays.asList方法返回的列表对象不能改变大小，
		 * 因为Arrays.asList方法返回的ArrayList对象不是java.util包中的类，而是Arrays类中的内部类，
		 * 该类没有重写add方法和remove方法，所以使用了java.util.AbstractList中的add方法和remove方法，
		 * 可以看到这个类中的这两个方法都会直接抛出UnsupportedOperationException
		 */
		System.out.println("\n2. Arrays.asList返回列表是固定长度的");
		List<Integer> arrList = Arrays.asList(objectValues);
		// arrList.add(1); arrList.remove(1); //将抛出java.lang.UnsupportedOperationException，因为Arrays.asList返回的列表是不能改变大小的
		ArrayList<Integer> realArrayList = new ArrayList<>(arrList); // 创建一个java.util.ArrayList对象
		realArrayList.add(1); // 现在可以改变列表长度
	}
}
