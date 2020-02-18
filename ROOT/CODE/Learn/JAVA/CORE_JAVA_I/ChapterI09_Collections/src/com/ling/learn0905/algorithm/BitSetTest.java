package com.ling.learn0905.algorithm;

import java.util.BitSet;

/**
 * 使用位集实现素数个数的统计
 *
 * Chapter09/com.ling.learn0905.algorithm.BitSetTest.java
 *
 * author lingang
 *
 * createTime 2019-11-26 23:48:27
 *
 */
public class BitSetTest {
	public static void main(String[] args) {
		int n = 2000000;
		long start = System.currentTimeMillis();
		BitSet bs = new BitSet(n + 1);
		int count = 0;
		int i;
		// 初始全部是素数
		for (i = 2; i <= n; ++i) {
			bs.set(i);
		}
		i = 2;
		while (i * i <= n) {
			if (bs.get(i)) {
				// 将所有已知素数的倍数全部置0
				++count;
				int k = 2 * i;
				while (k <= n) {
					bs.clear(k);
					k += i;
				}
			}
			++i;
		}

		while (i <= n) {
			if (bs.get(i))
				++count;
			++i;
		}
		long end = System.currentTimeMillis();
		System.out.println(count + "个素数");
		System.out.println("耗时：" + (end - start) + "ms");

	}
}
