package com.ling.algorithms01.basic;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 插入排序
 * 
 * 时间复杂度：
 * 
 * 最佳情况 ：θ(n)
 * 
 * 最坏和平均情况：θ(n²)
 * 
 * 具体分析过程见15-16页
 * 
 * 对运行时间的简化处理：
 * 
 * 1. 去除常量，假如运行时间为c₁n+c₂，那么应该简化为θ(n)
 * 
 * 2. 只保留最高阶的项，去除低阶项，假如运行时间为c₁n²+c₂n+c₃，那么应该简化为θ(n²)
 * 
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms01.basic.InsertionSort.java
 *
 * author lingang
 *
 * createTime 2020-07-09 22:58:38
 *
 */
public class InsertionSort {
	public static void main(String[] args) {
		// 生成1000个随机数
		Double[] data = Stream.<Double>generate(Math::random).limit(1000)
				.map(d -> Double.parseDouble(String.format("%.2f", d * 100))).toArray(Double[]::new);
		// 排序前
		System.out.println(Arrays.toString(data));
		// 排序
		insertionSort(data, true);
		// 排序后
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 插入排序
	 * 
	 * @param data-排序数列
	 * @param ascFlag-正序标志
	 */
	public static void insertionSort(Double[] data, boolean ascFlag) {
		int len = data.length;// 待排序数组的长度
		for (int i = 1; i < len; ++i) {// 从第2个位置开始按序插入数据
			Double key = data[i];// 保存待插入数据
			int j = i - 1;// 将指针移动到待插入数据的前一个位置(待插入数据前的元素已经是有序的)
			while (j >= 0 && (ascFlag ? data[j] > key : data[j] < key)) {// 将比待插入数据更大(或更小)的数往后移动一个位置，直到指针到达数组首部或者遇到小于等于(或大于等于)待插入数据的元素
				data[j + 1] = data[j];
				j = j - 1;// 指针往前移
			}
			data[j + 1] = key;// 插入位置为指针停留的的后一个位置
		}
	}

}
