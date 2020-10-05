package com.ling.algorithms15.dynamicprogramming;

import java.util.Arrays;

/**
 * 钢条切割带备忘录的自顶向下实现(空间代价换取时间代价)
 * 
 * 时间复杂度为：θ(n²)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms15.dynamicprogramming.CutRodMemorized.java
 *
 * author ling
 *
 * createTime 2020-10-05 11:13:26
 *
 */
public class CutRodMemorized {
	private static long time = 0;

	public static void main(String[] args) {
		System.out.println(cutRodMemorized(new double[] { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 }, 9));
		System.out.println("时间复杂度：" + time);
	}

	public static double cutRodMemorized(double[] priceArr, int n) {
		double[] rArr = new double[n];// 记录每个子问题的计算结果
		Arrays.fill(rArr, Double.MIN_VALUE);
		return cutRodMemorizedAux(priceArr, n, rArr);
	}

	public static double cutRodMemorizedAux(double[] priceArr, int n, double[] rArr) {
		if (n <= 0)
			return 0;
		if (rArr[n - 1] != Double.MIN_VALUE)
			return rArr[n - 1];// 如果子问题已经计算过，则直接返回
		double rMax = 0;// 保存最大收益值
		for (int i = 0; i < n; ++i) {
			++time;// 记录时间复杂度
			double ri = priceArr[i] + cutRodMemorizedAux(priceArr, n - i - 1, rArr);// 数组从0开始，所以减去i再减1才是剩下的长度
			if (ri > rMax)
				rMax = ri;
		}
		rArr[n - 1] = rMax;// 将子问题的最优解保存
		return rMax;
	}
}
