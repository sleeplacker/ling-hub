package com.ling.learn0105.othertransform;

import java.util.stream.Stream;

/**
 * 其他流转换操作
 *
 * ChapterII01/com.ling.learn0105.othertransform.OtherStreamTransformTest.java
 *
 * author lingang
 *
 * createTime 2020-01-13 20:46:57
 *
 */
public class OtherStreamTransformTest {
	public static void main(String[] args) {
		// 去重
		Stream<String> stream = Stream.of("AA", "CC", "BB", "BB");
		stream.distinct().forEach(s -> System.out.print(s + " "));// distinct方法去掉重复元素
		System.out.println();

		// 排序
		stream = Stream.of("AA", "CC", "BB", "BB");
		stream.sorted().forEach(s -> System.out.print(s + " "));// sorted方法对元素进行排序，也可以使用带Comparator参数的版本
		System.out.println();

		// 元素跟踪
		stream = Stream.of("AA", "CC", "BB", "BB");
		stream.peek(s -> System.out.print("访问了元素：" + s + " ")).forEach(s -> System.out.print(s + " "));// 每访问一个元素，都会对该元素调用peek方法函数式接口参数指定的方法
	}
}
