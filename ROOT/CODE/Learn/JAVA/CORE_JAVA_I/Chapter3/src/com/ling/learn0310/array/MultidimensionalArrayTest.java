package com.ling.learn0310.array;

import java.util.Arrays;

/**
 * 多维数组操作
 *
 * Chapter3/com.ling.learn0310.array.MultidimensionalArrayTest.java
 *
 * author lingang
 *
 * createTime 2019-10-10 00:47:26
 *
 */
public class MultidimensionalArrayTest {
	public static void main(String[] args) {
		/* 1. 数组遍历*/
		System.out.println("1. 数组遍历");
		int[][] twoDArr = new int[2][2];
		for(int[] oneDArr : twoDArr) {
			for(int iValue : oneDArr) {
				System.out.println(iValue);
			}
		}
		System.out.println(Arrays.deepToString(twoDArr)); // Arrays.deepToString方法可以格式元输出多维原生类型数组

		/* 2. 数组没有维度限制*/
		System.out.println("\n2. 数组没有维度限制");
		int[][][][][][][][][][] tenDArr = new int[2][2][2][2][2][2][2][2][2][2];
		System.out.println(Arrays.deepToString(tenDArr));

		/* 3. 不规则数组，其实java中的数组都是一维的，二维数组的每个元素都是一维数组对象，
		 * 所以每个一维数组对象的元素个数不一定是相同的，所以才能形成不规则数组*/
		int [][] irregularArr = new int[2][];
		irregularArr[0] = new int[3];
		irregularArr[1] = new int[2];
		System.out.println(Arrays.deepToString(irregularArr));
	}
}
