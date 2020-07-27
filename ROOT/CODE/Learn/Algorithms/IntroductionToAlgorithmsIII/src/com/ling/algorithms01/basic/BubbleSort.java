package com.ling.algorithms01.basic;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 冒泡排序，思路：
 * 
 * n轮比较相邻两个元素位置，然后根据大小交换位置
 * 
 * 每一轮结束时就确认了一个元素的位置
 * 
 * 时间复杂度：θ(n²)
 * 
 * 冒泡排序是稳定排序方法
 * 
 * 类型，冒泡排序有两类：
 * 
 * 正向比较，反向冒泡：即从左往右比较元素，最先确立最后一个元素的位置(本例就是这种类型)
 * 
 * 反向比较，正向冒泡：即从右往左比较元素，最先确立第一个元素的位置
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms01.basic.BubbleSort.java
 *
 * author lingang
 *
 * createTime 2020-07-27 10:12:42
 *
 */
public class BubbleSort {
	public static void main(String[] args) {
		// 生成随机数
		Double[] data = Stream.<Double>generate(Math::random).limit(20)
				.map(d -> Double.parseDouble(String.format("%.2f", d * 100))).toArray(Double[]::new);
		System.out.println(Arrays.toString(data));
		// 正序
		bubbleSort(data, true);
		System.out.println(Arrays.toString(data));
		// 倒序
		bubbleSort(data, false);
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 冒泡排序
	 * 
	 * @param data
	 * @param ascFlag
	 */
	public static void bubbleSort(Double[] data, boolean ascFlag) {
		if (data.length <= 1)
			return;
		for (int i = 0; i < data.length - 1; ++i)
			for (int j = 0; j < data.length - i - 1; ++j)
				if (ascFlag ? data[j] > data[j + 1] : data[j] < data[j + 1])
					swap(data, j, j + 1);
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
