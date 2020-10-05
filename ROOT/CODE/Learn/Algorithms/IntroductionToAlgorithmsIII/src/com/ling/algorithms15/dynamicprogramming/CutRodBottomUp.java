package com.ling.algorithms15.dynamicprogramming;

import java.util.Arrays;

/**
 * 钢条切割自底向上实现(同样有备忘机制，但是无需再判断子问题是否已经计算过)
 * 
 * 时间复杂度：θ(n²)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms15.dynamicprogramming.CutRodBottomUp.java
 *
 * author ling
 *
 * createTime 2020-10-05 12:05:36
 *
 */
public class CutRodBottomUp {
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
		Arrays.fill(rArr, Double.MIN_VALUE);
		for (int i = 1; i < n; ++i) {
			double rMax = priceArr[i];// 初始最大值为不切割的收益
			for (int j = 0; j < i; ++j) {// 计算各种切割方式子问题，不含不切割情况
				++time;// 记录时间复杂度
				double rj = priceArr[j] + rArr[i - j - 1];// 数组从0开始，所以减去j再减1才是剩下的长度
				if (rj > rMax)
					rMax = rj;
			}
			rArr[i] = rMax;
		}

		return rArr[n - 1];
	}
}
