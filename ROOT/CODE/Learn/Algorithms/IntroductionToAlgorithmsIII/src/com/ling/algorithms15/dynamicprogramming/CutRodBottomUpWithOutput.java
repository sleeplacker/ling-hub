package com.ling.algorithms15.dynamicprogramming;

import java.util.Arrays;

/**
 * 钢条切割自底向上实现-输出切割方案
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms15.dynamicprogramming.CutRodBottomUpWithOutput.java
 *
 * author ling
 *
 * createTime 2020-10-05 15:31:37
 *
 */
public class CutRodBottomUpWithOutput {
	private static long time = 0;

	public static void main(String[] args) {
		// { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 }表示钢条长度为1时，收益为1；长度为2时，收益为5...
		System.out.println(cutRodBottomUp(new double[] { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 }, 9));// 注意：这里假设钢条长度不能超过价格数组的长度
		System.out.println("时间复杂度：" + time);// 2^n -1
	}

	public static double cutRodBottomUp(double[] priceArr, int n) {
		if (n <= 0)
			return 0;
		double[] rArr = new double[n];
		int[] cutLen = new int[n];
		cutLen[0] = 1;
		Arrays.fill(rArr, Double.MIN_VALUE);
		for (int i = 1; i < n; ++i) {
			double rMax = priceArr[i];// 初始最大值为不切割的收益
			cutLen[i] = i + 1;
			for (int j = 0; j < i; ++j) {// 计算各种切割方式子问题，不含不切割情况
				++time;// 记录时间复杂度
				double rj = priceArr[j] + rArr[i - j - 1];// 数组从0开始，所以减去j再减1才是剩下的长度
				if (rj > rMax) {
					rMax = rj;
					cutLen[i] = j + 1;
				}
			}
			rArr[i] = rMax;
		}
		/**
		 * 切割方式构造：以长度为9的钢条为例，下面是输出数组
		 * 切割方式：[1, 2, 3, 2, 2, 6, 1, 2, 3]
		 * 那么切割的
		 * 		第1段为数组第9个位置的的长度：3
		 * 		第2段为数组第9-3=6个位置的长度：6
		 * 3+6已经达到长度9，所以最终切割方案就是3,6
		 */
		System.out.println("切割方式：" + Arrays.toString(cutLen));
		return rArr[n - 1];
	}
}
