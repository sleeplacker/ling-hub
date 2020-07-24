package com.ling.algorithms07.quicksort;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 快速排序
 * 
 * 思路：分之策略
 * 
 * 1) 将待排序数组划分成3部分：选定的一个元素作为关键字key作为一个部分，小于key的部分和大于等于key的部分
 * 
 * 2) 递归地对小于key的部分和大于等于key的部分做快速排序
 * 
 * 最坏情况(即每次划分所有其他元素值都>=key或<=key)时间复杂度：θ(n²)
 * 
 * 最好情况(每次划分都是1:1)和平均情况：O(nlgn)
 * 
 * 关于不平衡划分：就是每次都按1:9或者1:99的比例划分，时间复杂度依然为O(nlgn)，只是常量部分有所不同
 * 
 * 随机化版本：即每次选择用于划分比较的元素时都从指定的区间中随机选择一个，这样能避免特定的输入序列会出现最坏情况，需要θ(n²)的时间
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms07.quicksort.QuickSort.java
 *
 * author lingang
 *
 * createTime 2020-07-24 23:52:38
 *
 */
public class QuickSort {
	// 随机数生成器
	public static SecureRandom sr = new SecureRandom();

	public static void main(String[] args) {
		// 生成随机数组
		Double[] data = Stream.<Double>generate(Math::random).limit(20)
				.map(d -> Double.parseDouble(String.format("%.2f", d * 100))).toArray(Double[]::new);
		System.out.println(Arrays.toString(data));
		// 正序随机化快速排序
		quickSort(data, true, true);
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 快速排序
	 * 
	 * @param data-待排序数据
	 * @param ascFlag-正序标志
	 * @param randomFlag-随机化标志
	 */
	public static void quickSort(Double[] data, boolean ascFlag, boolean randomFlag) {
		if (data.length <= 1)
			return;
		// 调用快速排序递归方法
		quickSort(data, 0, data.length - 1, ascFlag, randomFlag);
	}

	/**
	 * 快速排序递归方法
	 * 
	 * @param data
	 * @param p-开始位置(包含)
	 * @param r-结束位置(包含)
	 * @param ascFlag
	 * @param randomFlag
	 */
	public static void quickSort(Double[] data, int p, int r, boolean ascFlag, boolean randomFlag) {
		if (p >= r)// 如果待排序长度小于等于0，说明已无需排序
			return;
		// 假定按正序排序，将待排序数组拆为3段：选定的key、小于key的部分和大于等于key的部分
		int q = partition(data, p, r, ascFlag, randomFlag);
		// 分别对小于key的部分和大于等于key的部分递归进行快速排序
		quickSort(data, p, q - 1, ascFlag, randomFlag);
		quickSort(data, q + 1, r, ascFlag, randomFlag);
	}

	/**
	 * 对指定区间进行划分
	 * 
	 * @param data
	 * @param p
	 * @param r
	 * @param ascFlag
	 * @param randomFlag
	 * @return
	 */
	public static int partition(Double[] data, int p, int r, boolean ascFlag, boolean randomFlag) {
		if (randomFlag) {
			// 如果指定了随机化标识，那么要从指定区间随机选择一个元素作为key，这里将随机选择的元素替换到r位置
			int randIndex = p + sr.nextInt(r - p);
			swap(data, r, randIndex);
		}
		Double key = data[r];// 指定key
		int i = p - 1;// i为小于key部分的右边界(这部分可能为空，即i一次都不增加时，p>i，则p-i为空区间)
		for (int j = p; j < r; ++j) {// j为大于等于key部分的右边界，左边界为i+2(同样这部分也可能为空，当i每次都增加时，i=j，i+2>j，则i+2到j为空区间)
			if (ascFlag ? data[j] < key : data[j] > key) {
				// 正序时，如果当前值比key小，则向右移动小于key部分的边界
				++i;
				// 将小于key的元素换到当前小于key部分新的右边界位置
				swap(data, i, j);
			}
		}
		// 将key所在位置元素换到小于key部分的后面一个位置，即i+1
		swap(data, i + 1, r);
		// 返回key元素新位置
		return i + 1;
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
