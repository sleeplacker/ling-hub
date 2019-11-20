package com.ling.learn0310.array;

import java.util.Arrays;

/**
 * 数组操作
 *
 * Chapter3/com.ling.learn0310.array.ArrayTest.java
 *
 * author lingang
 *
 * createTime 2019-10-10 00:47:06 
 *
 */
public class ArrayTest {
	public static void main(String[] args) {
		/*
		 * 1. 数组初始值，当使用new datatype[n]的方式创建数组时，每种数组类型的元素都有默认初始值 整数类型-0 浮点类型-0.0
		 * boolean类型-false 对象类型-null
		 */
		System.out.println("1. 数组初始值");
		int[] initValues = new int[2];
		double[] doubleValues = new double[2];
		boolean[] booleanValues = new boolean[2];
		Integer[] objectValues = new Integer[2];
		for (int iv : initValues) {
			System.out.println(iv);
		}
		for (double dv : doubleValues) {
			System.out.println(dv);
		}
		for (boolean bv : booleanValues) {
			System.out.println(bv);
		}
		for (Integer ov : objectValues) {
			System.out.println(ov);
		}

		/* 2. 数组在java中也是一种对象，将一个数组变量赋值给另一个数组变量，会让两个数组变量指向同一个数组对象 */
		System.out.println("\n2. 数组是对象");
		int[] intArr = { 1, 2, 3 }; // 数组的另一种初始化方式，使用{}括起来，逗号分隔
		System.out.println(intArr); // [I@659e0bfd 打印的是对象地址，说明数组其实也是对象
		int[] intArrCopy = intArr; // 数组变量赋值
		intArr[1] = 0;
		System.out.println(intArrCopy[1]); // 改变intArr指向的数组对象，intArrCopy指向的数组对象也被改变，说明两者指向同一对象

		/* 3. Arrays.copyOf会创建一个新的数组对象，而不是创建一个指向已有对象的引用 */
		System.out.println("\n3. 数组拷贝");
		int[] srcArr = { 1, 2 };
		int[] desArr = Arrays.copyOf(srcArr, srcArr.length); // 此操作会创建一个新的数组对象，而不是让desArr指向srcArr指向的对象
		System.out.println(Arrays.toString(desArr));
		srcArr[1] = 0; // 修改srcArr指向的数组对象的元素值
		System.out.println(Arrays.toString(desArr)); // desArr指向的数组对象的元素值未被改变，说明srcArr和desArr指向不同的对象

		/* 4. 拷贝到一个更长的数组时，后面补第1点介绍的，每种类型对应的默认初始值；拷贝到一个更长的数组时，后面部分被截断 */
		System.out.println("\n4. 数组拷贝并改变数组大小");
		int[] biggerArr = Arrays.copyOf(srcArr, srcArr.length + 1); // 拷贝到一个更长的数组
		int[] smallerArr = Arrays.copyOf(srcArr, srcArr.length - 1); // 拷贝到一个更短的数组
		System.out.println(Arrays.toString(biggerArr)); // 拷贝到一个更长的数组时，后面补第1点介绍的，每种类型对应的默认初始值
		System.out.println(Arrays.toString(smallerArr)); // 拷贝到一个更长的数组时，后面部分被截断

		/* 5. 数组排序，Arrays.sort方法使用了快速排序算法*/
		System.out.println("\n5. 数组排序");
		int[] randomArr = {1,3,5,7,9,2,4,6,8,10};
		Arrays.sort(randomArr);
		System.out.println(Arrays.toString(randomArr));
	}
}
