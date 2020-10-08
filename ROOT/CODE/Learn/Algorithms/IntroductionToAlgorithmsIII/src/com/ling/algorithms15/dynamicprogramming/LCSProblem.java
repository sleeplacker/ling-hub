package com.ling.algorithms15.dynamicprogramming;

import java.util.Stack;

/**
 * 最长公共子序列问题
 * 
 * 可以看出，不管是计算LCS还是构造LCS结果，都是两层for循环，所以时间复杂度为：θ(mn)，m和n分别为两个序列的长度
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms15.dynamicprogramming.LCSProblem.java
 *
 * author ling
 *
 * createTime 2020-10-08 23:36:08
 *
 */
public class LCSProblem {
	public static void main(String[] args) {
		char[] str1 = "ABCBDAB".toCharArray();
		char[] str2 = "BDCABA".toCharArray();

		// 计算LCS
		int[][] arr = lcs(str1, str2);

		// 打印所有子问题的LCS
		System.out.println("所有子问题的LCS");
		printSubProblems(str1, str2, arr);

		// 打印构造的 LCS 序列
		System.out.println("构造的LCS序列为：");
		printLCS(str1, str2, arr);

		System.out.println("最长公共子序列长度：" + arr[str1.length][str2.length]);

	}

	/**
	 * 打印所有子问题
	 * 
	 * @param str1
	 * @param str2
	 * @param arr
	 */
	private static void printSubProblems(char[] str1, char[] str2, int[][] arr) {
		System.out.print("\t\t");
		for (int i = 0; i < str2.length; ++i)
			System.out.print(str2[i] + "\t");
		System.out.println();
		for (int i = 0; i <= str1.length; ++i) {
			if (i > 0)
				System.out.print(str1[i - 1]);
			System.out.print("\t");
			for (int j = 0; j <= str2.length; ++j) {
				System.out.print(arr[i][j] + "\t");
			}
			System.out.println();
		}
	}

	/**
	 * 打印LCS
	 * 
	 * @param str1
	 * @param str2
	 * @param arr
	 */
	private static void printLCS(char[] str1, char[] str2, int[][] arr) {
		Stack stack = new Stack<>();
		for (int i = str1.length, j = str2.length; i > 0 && j > 0;) {
			if (str1[i - 1] == str2[j - 1])
				stack.push(str1[i - 1]);
			int a = arr[i - 1][j - 1];
			int b = arr[i - 1][j];
			int c = arr[i][j - 1];
			if (a >= b && a >= c) {// 必须优先往左上移
				--i;
				--j;
			} else if (b >= c)
				--i;
			else
				--j;
		}
		while (!stack.isEmpty())
			System.out.print(stack.pop());
		System.out.println();
	}

	/**
	 * 计算最长公共子序列
	 * 
	 * @param cs
	 * @param cs2
	 * @return
	 */
	private static int[][] lcs(char[] cs, char[] cs2) {
		int[][] arr = new int[cs.length + 1][cs2.length + 1];// int数组自动填0值，所以不用手动赋值
		for (int i = 0; i < cs.length; ++i)
			for (int j = 0; j < cs2.length; ++j) {
				if (cs[i] == cs2[j]) {
					arr[i + 1][j + 1] = arr[i][j] + 1;
				} else {
					if (arr[i][j + 1] > arr[i + 1][j])
						arr[i + 1][j + 1] = arr[i][j + 1];
					else
						arr[i + 1][j + 1] = arr[i + 1][j];
				}
			}
		return arr;
	}
}
