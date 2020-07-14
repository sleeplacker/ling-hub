package com.ling.algorithms01.basic;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 归并排序
 * 
 * 思路：
 * 
 * 1) 将数列平均分为左右两部分
 * 
 * 2) 对左右两部分分别递归做归并排序(直到做归并排序的长度不超过2，就开始做合并)
 * 
 * 3) 合并左右两部分(此时左右两部分已经是有序的)
 * 
 * 时间复杂度：θ(nlgn)
 * 
 * 简单分析：合并操作为线性时间θ(n)，递归部分大致需要lgn步(lgn=lg₂n)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms01.basic.MergeSort.java
 *
 * author lingang
 *
 * createTime 2020-07-13 23:44:37
 *
 */
public class MergeSort {
	public static void main(String[] args) {
		// 生成随机数
		Double[] data = Stream.<Double>generate(Math::random).limit(10)
				.map(d -> Double.parseDouble(String.format("%.2f", d * 100))).toArray(Double[]::new);
		// 排序前
		System.out.println(Arrays.toString(data));
		// 排序
		mergeSort(data, true);
		// 排序后
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 归并排序算法
	 * 
	 * @param data
	 * @param ascFlag-正序标识
	 */
	public static void mergeSort(Double[] data, boolean ascFlag) {
		if (data.length <= 1)// 如果待排序数列长度不超过1，则无需排序
			return;
		mergeSort(data, 0, data.length - 1, ascFlag);
	}

	/**
	 * 递归归并排序
	 * 
	 * @param data
	 * @param p
	 * @param r
	 */
	public static void mergeSort(Double[] data, int p, int r, boolean ascFlag) {
		int q = (p + r) / 2;// 计算待归并的数列中间位置，向下取整
		if (r - p + 1 > 2) {// 如果数列长度超过2才进行递归归并排序，否则直接合并
			mergeSort(data, p, q, ascFlag);
			mergeSort(data, q + 1, r, ascFlag);
		}
		merge(data, p, q, r, ascFlag);// 合并，其中数列p,p+1,p+2...q是有序的，且数列q+1,q+2...r也是有序的
	}

	/**
	 * 合并
	 * 
	 * @param data
	 * @param p
	 * @param q
	 * @param r
	 */
	public static void merge(Double[] data, int p, int q, int r, boolean ascFlag) {
		int lenLeft = q - p + 1 + 1;// 计算待合并左数列长度，且增加1个位置存放哨兵
		int lenRight = r - (q + 1) + 1 + 1;// 计算待合并右数列长度，且增加1个位置存放哨兵
		Double[] tempLeft = new Double[lenLeft];// 创建左数列数组
		System.arraycopy(data, p, tempLeft, 0, lenLeft - 1);// 填充左数列数组
		Double[] tempRight = new Double[lenRight];// 创建右数列数组
		System.arraycopy(data, q + 1, tempRight, 0, lenRight - 1);// 填充右数列数组
		tempLeft[lenLeft - 1] = ascFlag ? Double.MAX_VALUE : Double.MIN_VALUE;// 设置哨兵
		tempRight[lenRight - 1] = ascFlag ? Double.MAX_VALUE : Double.MIN_VALUE;// 设置哨兵
		int pLeft = 0;// 左数列数组指针
		int pRight = 0;// 右数列数组指针
		for (int i = p; i <= r; ++i) {// 限定待合并区间为：p,p+1,p+2...r
			if (ascFlag ? tempLeft[pLeft] > tempRight[pRight] : tempLeft[pLeft] < tempRight[pRight]) {// 从左右数列数组指针所指的元素选择较小(倒叙较大)的一个存放到原数组指针所指位置
				data[i] = tempRight[pRight];
				++pRight;// 指针+1
			} else {
				data[i] = tempLeft[pLeft];
				++pLeft;// 指针+1
			}
		}
	}
}
