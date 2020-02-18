package com.ling.learn0108.collecteresult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LongSummaryStatistics;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 收集结果
 *
 * ChapterII01/com.ling.learn0108.collecteresult.CollecteResultTest.java
 *
 * author lingang
 *
 * createTime 2020-01-13 23:04:10
 *
 */
public class CollecteResultTest {
	public static void main(String[] args) {
		Stream<String> stream = Stream.of("Row the boat gently down the street".split(" "));
		// 使用toArray生成结果数组
		System.out.println(Arrays.toString(stream.toArray()));

		// 将结果收集到集合或列表中
		stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.collect(Collectors.toList()));// 收集到List
		stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.collect(Collectors.toSet()));// 收集到Set
		stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.collect(Collectors.toCollection(ArrayList::new)));// 收集到指定类型的容器
		stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.collect(Collectors.joining()));// 按字符串拼接
		stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.collect(Collectors.joining(", ")));// 按字符串并指定分隔符拼接

		// 将结果约简为数字类型
		stream = Stream.of("Row the boat gently down the street".split(" "));
		LongSummaryStatistics sum = stream.collect(Collectors.summarizingLong(String::length));//以字符串元素长度来约简
		System.out.println(sum.getMax());//最长元素
		System.out.println(sum.getMin());//最短元素
		System.out.println(sum.getAverage());//长度平均值
		System.out.println(sum.getSum());//总长度
	}
}
