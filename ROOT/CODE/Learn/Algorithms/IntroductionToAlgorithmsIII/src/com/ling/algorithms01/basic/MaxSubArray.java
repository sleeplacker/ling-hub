package com.ling.algorithms01.basic;

import java.security.SecureRandom;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 最大(连续)子数组问题，三种算法
 * 
 * 1. 暴力求解：计算所有组合的子数组和，时间复杂度为θ(n²)
 * 
 * 2. 分治算法，思路是将子数组出现的位置分三种情况：
 * 
 * 1) 跨越中点
 * 
 * 2) 未跨越中点，且在左半边
 * 
 * 3) 未跨越中点，且在右半边
 * 
 * 情况1)很容易在线性时间找出最大子数组
 * 
 * 对于情况2)和3)，递归调用此算法即可
 * 
 * 3. 线性算法，思路：
 * 
 * 将整个数组划分为多个不相交的子数组(这些子数组的和>=0，且是最大的子数组，即如果子数组和再加上后面一个元素就会<0)
 * 
 * 那么最大子数组必定在这些子数组中，只需用一个变量保存最大子数组和就能在线性查找中比较并得到最终的最大子数组和。
 * 
 * 最终的最大子数组是从上面某个子数组的起点开始，直到到达相加最大和的位置。为什么是从起点开始？反证法：假如不包含起点，
 * 
 * 也就是从起点之后构造子数组，由于划分出的子数组有这样的性质：从起点开始往后累加的结果都是非负数，那么如果不从起点构造
 * 
 * 最大子数组，就会使最终累加和少了一个非负数，这样得到的就不是最大子数组
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms01.basic.MaxSubArray.java
 *
 * author lingang
 *
 * createTime 2020-07-16 11:13:51
 *
 */
public class MaxSubArray {
	public static void main(String[] args) {
		// 生成随机数列
		// Integer[] data = { 1, -2, 47, -5, 19, -99, 12, 88, -2, 5, -3, 8, -9
		// };
		Integer[] data = Stream.generate(() -> {
			return new SecureRandom().nextInt(500) - 250;
		}).limit(100).toArray(Integer[]::new);
		System.out.println(Arrays.toString(data));// 原数组
		System.out.println(Arrays.toString(forceFindMaxSubArray(data)));// 暴力查找最大子数组
		System.out.println(Arrays.toString(devideAndConquerFindMaxSubArray(data)));// 分治法查找最大子数组
		System.out.println(Arrays.toString(linearFindMaxSubArray(data)));// 线性算法查找最大子数组
	}

	/**
	 * 暴力算法
	 * 
	 * @param data
	 * @return
	 */
	public static Integer[] forceFindMaxSubArray(Integer[] data) {
		int len = data.length;
		if (len <= 1)
			return data;
		int maxSum = 0;
		int maxStart = 0;
		int maxEnd = 0;
		for (int i = 0; i < len; ++i) {
			int sum = 0;
			int tempSum = 0;
			int tempEnd = i;
			for (int j = i; j < len; ++j) {
				tempSum += data[j];
				if (sum < tempSum) {
					sum = tempSum;
					tempEnd = j;
				}
			}
			if (maxSum < sum) {
				maxSum = sum;
				maxStart = i;
				maxEnd = tempEnd;
			}
		}
		return Arrays.copyOfRange(data, maxStart, maxEnd + 1);
	}

	/**
	 * 分治算发
	 * 
	 * @param data
	 * @return
	 */
	public static Integer[] devideAndConquerFindMaxSubArray(Integer[] data) {
		Integer[] result = findMaxSubArray(data, 0, data.length - 1);
		return Arrays.copyOfRange(data, result[0], result[1] + 1);
	}

	/**
	 * 递归查找最大子数组
	 * 
	 * @param data
	 * @param low-区间左边界(包含)
	 * @param high-区间右边界(包含)
	 * @return
	 */
	public static Integer[] findMaxSubArray(Integer[] data, int low, int high) {
		if (low == high)
			return new Integer[] { low, high, data[low] };// 当只有一个元素是，触底返回
		int mid = (low + high) / 2;// 设置中点
		Integer[] leftResult = findMaxSubArray(data, low, mid);// 查找区间内中点往左的最大子数组(包含中点)
		Integer[] rightResult = findMaxSubArray(data, mid + 1, high);// 查找区间内中点往右的最大子数组(不包含中点)
		Integer[] crossResult = findCrossMaxSubArray(data, low, mid, high);// 查找跨越中点的最大子数组
		// 从上面三个区间中选择子数组和最大的一个
		if (leftResult[2] >= rightResult[2] && leftResult[2] >= crossResult[2])
			return leftResult;
		else if (rightResult[2] >= leftResult[2] && rightResult[2] >= crossResult[2])
			return rightResult;
		else
			return crossResult;
	}

	/**
	 * 跨中点情况查找最大子数组
	 * 
	 * @param data
	 * @param start-区间左边界(包含)
	 * @param mid-跨越的中点
	 * @param end-区间右边界(包含)
	 * @return
	 */
	private static Integer[] findCrossMaxSubArray(Integer[] data, int start, int mid, int end) {
		int tempSum = data[mid];
		int maxSum = tempSum;
		int low = mid;
		int high = mid;
		for (int i = mid - 1; i >= start; --i) {// 向左查找边界
			tempSum += data[i];
			if (maxSum < tempSum) {
				low = i;
				maxSum = tempSum;
			}
		}

		tempSum = maxSum;// 从左边最大子数组的位置开始向右查找
		for (int i = mid + 1; i <= end; ++i) {// 向右查找边界
			tempSum += data[i];
			if (maxSum < tempSum) {
				high = i;
				maxSum = tempSum;
			}

		}
		return new Integer[] { low, high, maxSum };
	}

	/**
	 * 线性算法
	 * 
	 * @param data
	 * @return
	 */
	public static Integer[] linearFindMaxSubArray(Integer[] data) {
		int len = data.length;
		int tempMaxSum = data[0];// 临时最大子数组和-初始为第一个元素
		int tempBegin = 0;// 临时起点
		int maxSum = tempMaxSum;// 最终的最大子数组和-初始为临时最大子数组和
		int maxBegin = 0;// 最终起点
		int maxEnd = 0;// 最终终点

		for (int i = 1; i < len; ++i) {
			if (tempMaxSum >= 0) {// 如果当前临时最大子数组和为非负，则继续加长临时子数组
				tempMaxSum += data[i];
			} else {// 否则结束该子数组，更换新起点
				tempMaxSum = data[i];
				tempBegin = i;
			}
			if (maxSum < tempMaxSum) {
				// 每次迭代都有可能更新临时最大子数组，所以每次迭代都比较最终最大子数组是否小于临时最大子数组，如果小于就更新最终最大子数组
				maxSum = tempMaxSum;
				maxBegin = tempBegin;// 更新起点
				maxEnd = i;// 更新终点
			}
		}
		return Arrays.copyOfRange(data, maxBegin, maxEnd + 1);
	}
}
