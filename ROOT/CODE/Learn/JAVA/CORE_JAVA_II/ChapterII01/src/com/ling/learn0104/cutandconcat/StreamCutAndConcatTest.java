package com.ling.learn0104.cutandconcat;

import java.util.stream.Stream;

/**
 * 流的剪切和连接
 *
 * ChapterII01/com.ling.learn0104.cutandconcat.StreamCutAndConcatTest.java
 *
 * author lingang
 *
 * createTime 2020-01-08 23:50:48 
 *
 */
public class StreamCutAndConcatTest {
	public static void main(String[] args) {
		// 流的剪切-通常用于无限流
		Stream<Double> infiniteStream = Stream.generate(Math::random);
		infiniteStream.limit(5).forEach(d -> System.out.print(d + " "));// 截取无限流的前5个元素
		System.out.println();

		// 跳过前面的元素
		Stream<String> stream1 = Stream.of("###", "aaaa", "BBB", "ccccc");
		stream1.skip(1).forEach(s -> System.out.print(s + " "));// 跳过第一个元素
		System.out.println();

		// 连接两个流
		stream1 = Stream.of("###", "aaaa", "BBB", "ccccc");
		Stream<String> stream2 = Stream.of("dd", "EEEE", "FFF");
		Stream.concat(stream1, stream2).forEach(s -> System.out.print(s + " "));//将两个流连接起来
	}
}
