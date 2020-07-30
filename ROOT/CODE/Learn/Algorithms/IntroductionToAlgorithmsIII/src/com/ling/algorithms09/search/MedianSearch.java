package com.ling.algorithms09.search;

import java.util.Arrays;
import java.util.stream.Stream;

import com.ling.algorithms07.quicksort.QuickSort;

/**
 * 中位数查找：找出一个数组中第i小的元素
 * 
 * 性能：在最坏情况下也能在θ(n)的时间内查找到底i小的元素
 * 
 * 思路：查找算法基本和随机查找算法(RandomizedSearch.randomizedSearch方法)一样，
 * 
 * 并在其基础上进行改进，即每次划分时都找中位数划分，这样划分就比较平均，
 * 
 * 从而就避免了随机划分最坏情况时要做n次划分(即需要θ(n²)的时间)的情况
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms09.search.MedianSearch.java
 *
 * author lingang
 *
 * createTime 2020-07-30 17:22:18
 *
 */
public class MedianSearch {
	public static void main(String[] args) {
		// 生成随机数列
		Double[] data = Stream.<Double>generate(Math::random).limit(23)
				.map(d -> Double.parseDouble(String.format("%.2f", d * 100))).toArray(Double[]::new);
		System.out.println(Arrays.toString(data));
		// 查找第6小的数
		System.out.println(medianSearch(data, 6, true));
		// 排序验证查找结果是否正确
		QuickSort.quickSort(data, true, false);
		System.out.println(Arrays.toString(data));

		// 查找第6大的数
		System.out.println(medianSearch(data, 6, false));
		// 排序验证查找结果是否正确
		QuickSort.quickSort(data, false, false);
		System.out.println(Arrays.toString(data));
	}

	public static Double medianSearch(Double[] data, int ranking, Boolean ascFlag) {
		if (data.length < ranking)// 长度小于排名，返回空
			return null;
		// 调用随机查找递归方法
		return medianSearch(data, ranking, 0, data.length - 1, ascFlag);
	}

	public static Double medianSearch(Double[] data, int ranking, int p, int r, Boolean ascFlag) {
		int q = p == r ? p : medianPartition(data, p, r, ascFlag, true);
		// 如果分区得到的位置q刚好等于排名，则返回这个位置的元素，算法结束
		if (q == ranking - 1)// 排名从1开始，数组从0开始，排名需要-1再
			return data[q];
		if (q > ranking - 1)// 如果分区得到的位置>排名，则排名元素在分区前面部分
			return medianSearch(data, ranking, p, q - 1, ascFlag);
		else// 否则查找元素在分区后面部分
			return medianSearch(data, ranking, q + 1, r, ascFlag);
	}

	public static int medianPartition(Double[] data, int p, int r, boolean ascFlag, boolean randomFlag) {
		// 找中位数
		Double key = searchMid(data, p, r);
		for (int i = 0; i < data.length; ++i)
			if (data[i] == key) {
				swap(data, i, r);
				break;
			}
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
	 * 递归查找近似中位数(不一定是真正的中位数)
	 * 
	 * 每次将数组按5位一组分，并找出每组的中位数，
	 * 
	 * 然后对中位数组成的数组递归查找中位数，
	 * 
	 * 直至最后只找到1个中位数
	 * 
	 * @param data
	 * @param p
	 * @param r
	 * @return
	 */
	public static Double searchMid(Double[] data, int p, int r) {
		int len = (r - p + 1) % 5 == 0 ? (r - p + 1) / 5 : (r - p + 1) / 5 + 1;// 计算组数
		Double[] midArr = new Double[len];
		for (int i = p; i <= r; i += 5) {
			System.out.print(data[i]);
			int x = i + 1;
			while (x <= r && x <= i + 4) {
				System.out.print(", " + data[x]);
				++x;
			}
			System.out.print("\t\t");
			// 将元素按每组5个元素划分，并对每组取中位数
			int arrIndex = (i - p) / 5;// 计算组位置
			for (int j = i; j <= r && j <= i + 2; ++j) {
				Double min = data[j];
				int minIndex = j;
				for (int k = j + 1; k <= r && k <= i + 4; ++k) {
					if (min > data[k]) {
						min = data[k];
						minIndex = k;
					}
				}
				swap(data, j, minIndex);
			}
			if (r - i < 5)
				midArr[arrIndex] = data[i + (r - i) / 2];
			else
				midArr[arrIndex] = data[i + 2];
		}
		System.out.println();
		System.out.println("\t" + Arrays.toString(midArr));
		if (midArr.length <= 1)
			return midArr[0];
		else
			return searchMid(midArr, 0, midArr.length - 1);
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
