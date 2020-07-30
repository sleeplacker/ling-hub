package com.ling.algorithms09.search;

import java.util.Arrays;
import java.util.stream.Stream;

import com.ling.algorithms07.quicksort.QuickSort;

/**
 * 随机查找：找出一个数组中第i小的元素
 * 
 * 一种思路：现将数组排序，然后取第i个元素，时间复杂度为θ(nlgn)
 * 
 * 更快的方法：利用快速排序的分区方法进行随机查找
 * 
 * 时间复杂度：最坏情况为θ(n²)，平均情况为θ(n)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms09.search.RandomizedSearch.java
 *
 * author lingang
 *
 * createTime 2020-07-30 15:15:25
 *
 */
public class RandomizedSearch {
	public static void main(String[] args) {
		// 生成随机数列
		Double[] data = Stream.<Double>generate(Math::random).limit(20)
				.map(d -> Double.parseDouble(String.format("%.2f", d * 100))).toArray(Double[]::new);
		System.out.println(Arrays.toString(data));
		// 查找第6小的数
		System.out.println(randomizedSearch(data, 6, true));
		// 排序验证查找结果是否正确
		QuickSort.quickSort(data, true, false);
		System.out.println(Arrays.toString(data));

		// 查找第6大的数
		System.out.println(randomizedSearch(data, 6, false));
		// 排序验证查找结果是否正确
		QuickSort.quickSort(data, false, false);
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 查找排名第ranking的元素
	 * 
	 * @param data
	 * @param ranking-排名
	 * @param ascFlag-正序情况排名为第ranking小，倒序情况排名为第ranking大
	 * @return
	 */
	public static Double randomizedSearch(Double[] data, int ranking, Boolean ascFlag) {
		if (data.length < ranking)// 长度小于排名，返回空
			return null;
		// 调用随机查找递归方法
		return randomizedSearch(data, ranking, 0, data.length - 1, ascFlag);
	}

	/**
	 * 随机查找递归方法
	 * 
	 * @param data
	 * @param ranking
	 * @param p-开始位置(包含)
	 * @param r-结束位置(包含)
	 * @param ascFlag
	 * @return
	 */
	public static Double randomizedSearch(Double[] data, int ranking, int p, int r, Boolean ascFlag) {
		// 如果开始位置=结束位置，则无需分区，q=p；否则调用快速排序分区方法的随机版本
		int q = p == r ? p : QuickSort.partition(data, p, r, ascFlag, true);
		// 如果分区得到的位置q刚好等于排名，则返回这个位置的元素，算法结束
		if (q == ranking - 1)// 排名从1开始，数组从0开始，排名需要-1再
			return data[q];
		if (q > ranking - 1)// 如果分区得到的位置>排名，则排名元素在分区前面部分
			return randomizedSearch(data, ranking, p, q - 1, ascFlag);
		else// 否则查找元素在分区后面部分
			return randomizedSearch(data, ranking, q + 1, r, ascFlag);
	}
}
