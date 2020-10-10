package com.ling.algorithms16.greedyalgorithms;

import java.util.ArrayList;
import java.util.List;

/**
 * 活动选择贪心算法
 * 
 * 在活动数组已经按照活动结束时间的升序排序的前提下，贪心算法的时间复杂度为：θ(n)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms16.greedyalgorithms.ActivitiesSelection.java
 *
 * author ling
 *
 * createTime 2020-10-11 00:15:09
 *
 */
public class ActivitiesSelection {
	public static void main(String[] args) {
		/* 注意，活动数组已经按照活动结束时间的升序排序 */
		int[] startTimes = { 1, 3, 0, 5, 3, 5, 6, 8, 8, 2, 12 };// 11个活动的开始时间
		int[] finishTimes = { 4, 5, 6, 7, 9, 9, 10, 11, 12, 14, 16 };// 11个活动的结束时间

		/* 1. 贪心递归算法求解活动选择问题 */
		List result = new ArrayList<>();
		result.add(0);// 第一个结束的活动是必选的
		selectRecursively(startTimes, finishTimes, 1, 11, result);
		System.out.println(result);

		/* 2. 贪心迭代算法求解活动选择问题 */
		System.out.println(selectIteratively(startTimes, finishTimes));
	}

	/**
	 * 递归算法求解活动选择问题
	 * 
	 * @param startTimes
	 * @param finishTimes
	 * @param m-已经处理完的活动位置
	 * @param n-活动总数
	 * @param result-记录活动选择结果
	 */
	private static void selectRecursively(int[] startTimes, int[] finishTimes, int k, int n, List result) {
		int m = k + 1;// 从下一个活动开始查找
		while (m <= n - 1 && startTimes[m] < finishTimes[k])
			++m;
		if (m <= n - 1) {// 查找成功
			result.add(m);// 加入新活动
			selectRecursively(startTimes, finishTimes, m, n, result);// 继续查找活动
		} else// 查找失败，结束查找
			return;
	}

	/**
	 * 迭代算法求解活动选择问题
	 * 
	 * @param startTimes
	 * @param finishTimes
	 * @return
	 */
	private static List selectIteratively(int[] startTimes, int[] finishTimes) {
		List result = new ArrayList<>();
		result.add(0);// 第一个活动必选
		int n = startTimes.length;// 活动数量
		int k = 0;// k指向第一个活动
		for (int i = 1; i < n; ++i)
			if (startTimes[i] > finishTimes[k]) {// 查找兼容活动
				result.add(i);// 查找成功，将该活动加入结果集
				k = i;// 让k指向该活动
			}
		return result;
	}
}
