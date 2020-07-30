package com.ling.algorithms09.search;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 同时查找最大值和最小值的改进算法
 * 
 * 普通查找算法，对每个元素，都和当前最大值和最小值比较一下，总比较次数为2(n-1)
 * 
 * 改进算法：每次处理两个元素，先将两个元素进行比较，然后将较小元素和当前最小值比较，
 * 
 * 将较大元素和当前最大值比较，总比较次数为大约3n/2
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms09.search.SearchMinMax.java
 *
 * author lingang
 *
 * createTime 2020-07-30 10:01:40
 *
 */
public class SearchMinMax {
	public static void main(String[] args) {
		// 生成随机数列
		Double[] data = Stream.<Double>generate(Math::random).limit(20)
				.map(d -> Double.parseDouble(String.format("%.2f", d * 100))).toArray(Double[]::new);
		System.out.println(Arrays.toString(data));
		// 查找最大和最大值
		System.out.println(Arrays.toString(searchMinAndMax(data)));
	}

	/**
	 * 同时查找最大和最小值
	 * 
	 * @param data
	 * @return
	 */
	public static Double[] searchMinAndMax(Double[] data) {
		if (data.length <= 1)
			return data.length == 1 ? new Double[] { data[0], data[0] } : null;
		Double min = Double.MAX_VALUE;
		Double max = Double.MIN_VALUE;
		if (data.length % 2 == 1) {// 如果数组长度为奇数，就先处理第1个元素
			min = data[0];
			max = data[0];
		}
		for (int i = data.length % 2; i < data.length; i += 2) {
			Double temp1 = data[i];
			Double temp2 = data[i + 1];
			if (temp1 < temp2) {// 第1次比较
				if (temp1 < min)// 第2次比较
					min = temp1;
				if (temp2 > max)// 第3次比较
					max = temp2;
			} else {
				if (temp1 > max)
					max = temp1;
				if (temp2 < min)
					min = temp2;
			}

		}
		return new Double[] { min, max };
	}
}
