package com.ling.algorithms08.lineartimesort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 计数排序-对元素出现次数进行计数
 * 
 * 使用条件：元素取值在一个较小的给定范围
 * 
 * 思路：
 * 
 * 使用一个数组B来记录每个元素出现的次数，这个数组每个位置对应了一个取值，
 * 
 * 所有位置就覆盖了元素取值范围
 * 
 * 对每个元素，使用元素的值作为位置在数组B中找到位置，然后该位置的计数值+1(计数值初始为0)
 * 
 * 然后在数组B从左往右将相邻的元素值相加，之后每个位置值表示小于等于当前位置值的元素个数
 * 
 * 最后就能通过数组B知道每个元素的位置，形象的看P109的图
 * 
 * 时间复杂度：θ(n)
 * 
 * 计数排序是稳定排序
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms08.lineartimesort.CountingSort.java
 *
 * author lingang
 *
 * createTime 2020-07-28 16:01:23
 *
 */
public class CountingSort {
	public static void main(String[] args) {
		// 生成随机数组-取值范围：0-99
		Random r = new Random();
		Integer[] data = Stream.<Integer>generate(() -> {
			return r.nextInt(100);
		}).limit(50).toArray(Integer[]::new);

		System.out.println(Arrays.toString(data));
		// 正序
		Integer[] result = countingSort(data, 100, true);
		System.out.println(Arrays.toString(result));
		// 倒序
		result = countingSort(data, 100, false);
		System.out.println(Arrays.toString(result));
	}

	/**
	 * 计数排序
	 * 
	 * @param data
	 * @param range-元素取值范围，例如range=100，表示取值范围为0-99
	 * @param ascFlag
	 */
	public static Integer[] countingSort(Integer[] data, Integer range, boolean ascFlag) {
		if (data.length <= 1)
			return data;
		Integer[] countArr = new Integer[range];// 限制元素取值范围为0-99，超过这个范围的数无法使用计数排序
		Arrays.fill(countArr, 0);// 将计数数组所有元素初始化为0
		Integer[] result = new Integer[data.length];// 定义结果数组变量
		for (Integer e : data)// 对待排序元素进行计数
			++countArr[e];

		for (int i = 1; i < countArr.length; ++i)// 从左往右将所有相邻计数值相加-得到元素值小于等于计数范围内每个数字的个数
			countArr[i] = countArr[i - 1] + countArr[i];

		for (int i = data.length - 1; i >= 0; --i) {// 从待排序数组最后一个元素往前定位排序后位置
			if (ascFlag)
				result[countArr[data[i]] - 1] = data[i];// 比如第i个位置的元素在排序后位置为：计数数组中该元素值对应的位置的值
			else
				result[result.length - countArr[data[i]]] = data[i];
			// 如果结果数组中已经占用了某个位置，那么在计数数组中该位置的值-1，当遇到相同元素时，就能将其放到左边，保持了排序的稳定性
			--countArr[data[i]];
		}
		return result;
	}
}
