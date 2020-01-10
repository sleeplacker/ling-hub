package com.ling.learn0104.cutandconcat;

import java.text.DecimalFormat;
import java.util.Random;
import java.util.stream.Stream;
import static java.lang.Math.*;

/**
 * 正态分布测试
 *
 * ChapterII01/com.ling.learn0104.cutandconcat.GaussianTest.java
 *
 * author lingang
 *
 * createTime 2020-01-11 00:29:22
 *
 */
public class GaussianTest {
	private static ThreadLocal<DecimalFormat> df = ThreadLocal.withInitial(() -> new DecimalFormat("##0.0"));
	private static ThreadLocal<DecimalFormat> df4 = ThreadLocal.withInitial(() -> new DecimalFormat("##0.0000"));
	private static Double temp = 0.0;
	private static Random rand = new Random(47);

	public static void main(String[] args) {
		double average = 0;
		double standardDeviation = 5;
		double data = -2;
		System.out.println(df4.get().format(makeRate(data, average, standardDeviation)));
		print(average, standardDeviation);
	}

	/**
	 * 计算数字出现概率
	 * 
	 * @param data
	 * @param average
	 * @param standardDeviation
	 * @return
	 */
	public static double makeRate(double data, double average, double standardDeviation) {
		return 1 / (standardDeviation * sqrt(2 * PI)) * pow(E, -0.5 * pow((data - average) / standardDeviation, 2));
	}

	/**
	 * 打印一组正态分布数据，呈现形状
	 * 
	 * @param average-均值
	 * @param standardDeviation-标准差
	 */
	public static void print(double average, double standardDeviation) {
		Stream.generate(() -> standardDeviation * rand.nextGaussian() + average).limit(20000).sorted().forEach(d -> {
			String ds = df.get().format(d);
			if (!"0.0".equals(ds) && !"-0.0".equals(ds)) {
				String dt = df.get().format(temp);
				if (!ds.equals(dt)) {
					System.out.print("\n" + ds);
					temp = d;
				}
				System.out.print("|");
			}
		});
	}
}
