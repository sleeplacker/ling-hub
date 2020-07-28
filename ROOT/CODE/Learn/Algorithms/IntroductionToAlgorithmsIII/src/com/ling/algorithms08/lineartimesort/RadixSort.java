package com.ling.algorithms08.lineartimesort;

import java.util.Arrays;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 基数排序
 * 
 * 使用条件：排序的数据位数不超过d位，d
 * 
 * 思路：
 * 
 * 对每位数做稳定排序(从低位到高位的顺序，且稳定排序算法通常使用计数排序算法)，
 * 
 * 只是每一轮保存的是待排序元素的整个数，而不是某一位数
 * 
 * 时间复杂度：θ(d(n+k))，其中d为元素数字的位数，k为每位取值范围
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms08.lineartimesort.RadixSort.java
 *
 * author lingang
 *
 * createTime 2020-07-28 23:40:02
 *
 */
public class RadixSort {

	public static void main(String[] args) {
		// 生成随机数组-取值范围：0-99
		Random r = new Random();
		Integer[] data = Stream.<Integer>generate(() -> {
			return r.nextInt(1000);
		}).limit(10).toArray(Integer[]::new);

		System.out.println(Arrays.toString(data));
		// 正序
		Integer[] result = radixSort(data, 3, true);
		System.out.println(Arrays.toString(result));
		// 倒序
		result = radixSort(data, 3, false);
		System.out.println(Arrays.toString(result));
	}

	/**
	 * 基数排序
	 * 
	 * @param data
	 * @param digitLen-待排序元素的最高位数
	 * @param ascFlag
	 * @return
	 */
	public static Integer[] radixSort(Integer[] data, Integer digitLen, boolean ascFlag) {
		if (data.length <= 1)
			return data;
		Integer[] countArr = new Integer[10];// 十进制数每位取值为0-9

		Integer[] result = new Integer[data.length];// 定义结果数组变量
		for (int digitIndex = 1; digitIndex <= digitLen; ++digitIndex) {
			Arrays.fill(countArr, 0);// 将计数数组所有元素初始化为0
			Integer[] tempData = new Integer[data.length];
			for (int i = 0; i < data.length; ++i)// 将待排序元素的一位存到一个临时数组中
				tempData[i] = getDigitValue(data[i], digitIndex);

			for (Integer e : tempData) // 对待排序元素的一位进行计数
				++countArr[e];

			for (int i = 1; i < countArr.length; ++i)// 从左往右将所有相邻计数值相加-得到元素值小于等于计数范围内每个数字的个数
				countArr[i] = countArr[i - 1] + countArr[i];

			for (int i = data.length - 1; i >= 0; --i) {// 从待排序数组最后一个元素往前定位排序后位置
				if (ascFlag || digitIndex < digitLen)// 注意：由于这里计数排序会有多轮，最后一轮才输出结果，所以如果不是最后一轮，倒序不调换输出顺序
					result[countArr[tempData[i]] - 1] = data[i];// 比如第i个位置的元素在排序后位置为：计数数组中该元素值对应的位置的值
				else
					result[result.length - countArr[tempData[i]]] = data[i];
				// 如果结果数组中已经占用了某个位置，那么在计数数组中该位置的值-1，当遇到相同元素时，就能将其放到左边，保持了排序的稳定性
				--countArr[getDigitValue(data[i], digitIndex)];
			}
			data = Arrays.copyOf(result, result.length);// 更新待排序数组
			System.out.println("\tDigit at point " + digitIndex + " : " + Arrays.toString(data));// 打印出此轮排序结束后的序列
		}
		return result;
	}

	/**
	 * 获取十进制数d第i位的值
	 * 
	 * @param d
	 * @param i
	 * @return
	 */
	public static Integer getDigitValue(Integer d, Integer i) {
		Double temp = Double.valueOf(d) % Math.pow(10, i) / Math.pow(10, i - 1);
		return temp.intValue();
	}
}
