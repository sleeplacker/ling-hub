package com.ling.learn0106.reduction;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 约简操作
 *
 * ChapterII01/com.ling.learn0106.reduction.ReductionTest.java
 *
 * author lingang
 *
 * createTime 2020-01-13 21:04:05
 *
 */
public class ReductionTest {
	public static void main(String[] args) {
		Stream<String> stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.count());// count方法将流约简为流中元素个数

		stream = Stream.of("Row the boat gently down the street".split(" "));
		Optional<String> minStr = stream.min((a, b) -> a.length() > b.length() ? 1 : -1);// min方法将流约简为元素中的最小值，同理max方法约简为最大值
		System.out.println(minStr.get());

		stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.filter(s -> s.length() == 4).findFirst());// findFirst查找满足条件的第一个元素

		stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.parallel().filter(s -> s.length() == 4).findAny());// findAny方法查找满足条件的任一元素，通常用于并行查找，因此这里的查找结果可能是boat也肯是down

		stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.parallel().anyMatch(s -> s.length() > 6));// 判断流中是否包含满足条件的元素

		stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.parallel().allMatch(s -> s.length() == 4));// 判断流中元素是否全满足条件

		stream = Stream.of("Row the boat gently down the street".split(" "));
		System.out.println(stream.parallel().noneMatch(s -> s.length() > 6));// 判断流中元素是否全不满足条件

	}
}
