package com.ling.algorithms01.basic;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 希尔排序
 * 
 * 实际上是执行多轮有偏移量插入排序，第一轮偏移量为n/2，
 * 
 * 每一轮偏移量再除以2，最后一轮偏移量为1，即普通插入排序，
 * 
 * 但是此时的数列有序程度已经很高，不需要多少元素移动就能完成排序
 * 
 * 时间复杂度：跟增量的选取有关，最坏情况为θ(n²)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms01.basic.ShellSort.java
 *
 * author lingang
 *
 * createTime 2020-07-27 11:14:07
 *
 */
public class ShellSort {
	public static void main(String[] args) {
		// 生成随机数
		 Double[] data = Stream.<Double>generate(Math::random).limit(20)
		 .map(d -> Double.parseDouble(String.format("%.2f", d *
		 100))).toArray(Double[]::new);
//		Double[] data = { 1d, 8d, 9d, 0d, 2d, 8d, 7d, 5d };
		System.out.println(Arrays.toString(data));
		// 正序
		shellSort(data, true);
		System.out.println(Arrays.toString(data));
		// 倒序
		shellSort(data, false);
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 希尔排序
	 * 
	 * @param data
	 * @param ascFlag
	 */
	public static void shellSort(Double[] data, boolean ascFlag) {
		if (data.length <= 1)
			return;
		int offSet = data.length / 2;// 偏移量，从数组长度的半开始
		while (offSet >= 1) {// 最后一轮的偏移量为1，此时的操作和插入排序完全一样
			// 下面的for循环就是插入排序，只不过不是对全部元素，而是按偏移量分组的元素，分组方式举例：
			// 假设数列为：{1, 8, 9, 0, 2, 8, 7, 5}那
			// 第一轮偏移量为8/2 = 4，分组为：{1, 2}，{8, 8}，{9, 7}，{0, 5}，分别排序并合并后为：{1, 8,
			// 7, 0, 2, 8, 9, 5}
			// 第二轮偏移量为4/2 = 2，分组为：{1, 7, 2, 9}，{8, 0, 8, 5}，分别排序并合并后为：{1, 0, 2,
			// 5, 7, 8, 9, 8}
			// 最后一轮偏移量为1，分组为{1, 0, 2, 5, 7, 8, 9, 8}，直接做插入排序
			for (int i = offSet; i < data.length; ++i) {
				Double key = data[i];
				int j = i - offSet;
				while (j >= 0 && (ascFlag ? data[j] > key : data[j] < key)) {
					data[j + offSet] = data[j];
					j -= offSet;// 指针往前移
				}
				data[j + offSet] = key;// 插入到正确位置
			}
			offSet = offSet / 2;// 偏移量减半
		}
	}

	/**
	 * 交换数组元素位置
	 * 
	 * @param data
	 * @param a
	 * @param b
	 */
	public static void swap(Double[] data, int a, int b) {
		if (a == b)
			return;
		Double temp = data[a];
		data[a] = data[b];
		data[b] = temp;
	}
}
