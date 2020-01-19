package com.ling.learn0113.primitivestream;

import java.util.Arrays;
import java.util.IntSummaryStatistics;
import java.util.OptionalInt;
import java.util.Random;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * 基本类型流
 *
 * ChapterII01/com.ling.learn0113.primitivestream.PrimitiveStreamTest.java
 *
 * author lingang
 *
 * createTime 2020-01-19 19:17:04
 *
 */
public class PrimitiveStreamTest {
	public static void main(String[] args) {
		// 创建基本类型流-of方法
		IntStream is = IntStream.of(1, 2, 3, 4, 5);// IntStream还可以存储short，char，byte和boolean类型
		LongStream ls = LongStream.of(1, 2, 3, 4, 5);
		DoubleStream ds = DoubleStream.of(1, 2, 3, 4, 5);// DoubleStream还可以存储float类型
		System.out.println(Arrays.toString(is.toArray()));
		System.out.println(Arrays.toString(ls.toArray()));
		System.out.println(Arrays.toString(ds.toArray()));

		// 创建基本类型流-Arrays.stream方法
		is = Arrays.stream(new int[] { 1, 2, 3, 4, 5 });// 通过数组创建
		is = Arrays.stream(new int[] { 1, 2, 3, 4, 5 }, 2, 4);// 通过数组并指定区间创建
		System.out.println(Arrays.toString(is.toArray()));
		// LongStream和DoubleStream也有相应版本的方法

		// 创建基本类型流-rang方法
		is = IntStream.range(0, 100);// 不包含右边界：0,1,2,3,...,99
		System.out.println(Arrays.toString(is.toArray()));
		is = IntStream.rangeClosed(1, 100);// 包含右边界：1,2,3,4,...,100
		System.out.println(Arrays.toString(is.toArray()));
		// LongStream也有相应版本的方法，但DoubleStream没有

		// 对象流转换为基本类型流
		Stream<String> ss = Stream.of("A", "BB", "CCC", "DDDD", "EEEEE");
		is = ss.mapToInt(String::length);// 将字符串流中的字符串映射为其长度来创建基本类型流
		System.out.println(Arrays.toString(is.toArray()));

		// 基本类型流转换为对应装箱类型的对象流
		is = Arrays.stream(new int[] { 1, 2, 3, 4, 5 });
		Stream<Integer> si = is.boxed();
		System.out.println(si.toArray()[0].getClass().getSimpleName());

		// 基本类型流的方法和对象流方法基本相似，下面是最主要的差异
		// 1.toArray方法会返回基本类型数组
		is = Arrays.stream(new int[] { 1, 2, 3, 4, 5 });
		System.out.println("is.toArray返回数组的元素类型 : " + is.toArray().getClass().getComponentType());

		// 2.产生可选结果的方法会返回一个OptionalInt，OptionalLong或OptionalDouble
		is = Arrays.stream(new int[] { 1, 2, 3, 4, 5 });
		OptionalInt oi = is.findAny();// OptionalInt不是泛型类
		oi.getAsInt();// get方法变成了getAsInt方法，同样还有getAsLong和getAsDouble

		// 3.具有返回总和、平均值、最大值和最小值的sum、average、max和min方法，而对象流没有这些方法
		is = Arrays.stream(new int[] { 1, 2, 3, 4, 5 });
		System.out.println(is.average());// 流被终结了，不能继续调用is.max等方法

		// 4.
		// summaryStatics方法会产生一个类型为IntSummaryStatics、LongSummaryStatics、或DoubleSummaryStatics的对象
		is = Arrays.stream(new int[] { 1, 2, 3, 4, 5 });
		IntSummaryStatistics iss = is.summaryStatistics();
		System.out.println("统计信息汇总，总和：" + iss.getSum() + ", 平均值：" + iss.getAverage() + "，最大值：" + iss.getMax() + "，最小值："
				+ iss.getMin());

		// 通过字符串产生码点流
		String str = "\uD835\uD846 is the set of octonions.";
		IntStream cps = str.codePoints();
		System.out.println(Arrays.toString(cps.toArray()));

		// Random的ints、longs和doubles方法会返回随机数构成的基本类型流
		System.out.println("-----Random : ");
		IntStream ris = new Random().ints();
		IntStream ris1 = new Random().ints(0, 100);// 指定随机数范围
		IntStream ris2 = new Random().ints(5);// 指定随机数个数
		IntStream ris3 = new Random().ints(5, 0, 50);// 指定随机数个数和范围
		System.out.println(Arrays.toString(ris.limit(10).toArray()));
		System.out.println(Arrays.toString(ris1.limit(10).toArray()));
		System.out.println(Arrays.toString(ris2.limit(10).toArray()));// 如果limit方法指定的个数比ints方法指定的个数大，则取较小那个数
		System.out.println(Arrays.toString(ris3.limit(10).toArray()));
	}
}
