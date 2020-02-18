package com.ling.learn1407.concurrentcollection;

import java.util.Arrays;

/**
 * 并行数组运算
 *
 * Chapter14/com.ling.learn1407.concurrentcollection.ArrayParallelOperationTest.java
 *
 * author lingang
 *
 * createTime 2019-12-22 00:07:55
 *
 */
public class ArrayParallelOperationTest {
	public static void main(String[] args) {
		int[] sarr = new int[10];
		Arrays.parallelSort(sarr);// 并行排序
		Arrays.parallelSetAll(sarr, x -> x + 2);// 并行填充，对数组位置进行运算，并将运算结果存到该位置
		System.out.println(Arrays.toString(sarr));
		Arrays.parallelPrefix(sarr, (x, y) -> x + y);// 对相邻元素进行运算，且加上前面运算结果，这里相当于[2,2+3,2+3+4,2+3+4+5,...]
		System.out.println(Arrays.toString(sarr));
	}
}
