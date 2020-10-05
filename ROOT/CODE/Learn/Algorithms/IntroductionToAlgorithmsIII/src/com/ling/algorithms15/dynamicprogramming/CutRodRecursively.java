package com.ling.algorithms15.dynamicprogramming;

/**
 * 钢条切割递归实现
 * 
 * 时间复杂度为：θ(2^n)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms15.dynamicprogramming.CutRodRecursively.java
 *
 * author ling
 *
 * createTime 2020-10-05 09:48:14
 *
 */
public class CutRodRecursively {
	private static long time = 0;

	public static void main(String[] args) {
		// { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 }表示钢条长度为1时，收益为1；长度为2时，收益为5...
		System.out.println(cutRodRecursively(new double[] { 1, 5, 8, 9, 10, 17, 17, 20, 24, 30 }, 10));//注意：这里假设钢条长度不能超过价格数组的长度
		System.out.println("时间复杂度：" + time);// 2^n -1
	}

	public static double cutRodRecursively(double[] priceArr, int n) {
		if (n <= 0)
			return 0;
		double rMax = 0;// 保存最大收益值
		for (int i = 0; i < n; ++i) {
			++time;// 记录时间复杂度
			double ri = priceArr[i] + cutRodRecursively(priceArr, n - i - 1);// 数组从0开始，所以减去i再减1才是剩下的长度
			if (ri > rMax)
				rMax = ri;
		}
		return rMax;
	}
}
