package com.ling.algorithms01.basic;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 选择排序，思路：
 * 
 * 从0至n-1，对每一个位置，都从剩下的元素中选择最小/最大的元素，然后将该元素替换到这个位置
 * 
 * 时间复杂度：θ(n²)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms01.basic.SelectionSort.java
 *
 * author lingang
 *
 * createTime 2020-07-27 10:41:18
 *
 */
public class SelectionSort {
	public static void main(String[] args) {
		// 生成随机数
		Double[] data = Stream.<Double>generate(Math::random).limit(20)
				.map(d -> Double.parseDouble(String.format("%.2f", d * 100))).toArray(Double[]::new);
		System.out.println(Arrays.toString(data));
		// 正序
		selectionSort(data, true);
		System.out.println(Arrays.toString(data));
		// 倒序
		selectionSort(data, false);
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 选择排序
	 * 
	 * @param data
	 * @param ascFlag
	 */
	public static void selectionSort(Double[] data, boolean ascFlag) {
		if (data.length <= 1)
			return;
		for (int i = 0; i < data.length; ++i) {
			Double temp = data[i];
			int tempIndex = i;
			for (int j = i + 1; j < data.length; ++j) {
				if (ascFlag ? data[j] < temp : data[j] > temp) {
					temp = data[j];
					tempIndex = j;
				}
			}
			swap(data, i, tempIndex);
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
