package com.ling.learn0103.streamtransform;

import java.util.stream.Stream;

/**
 * 流的转换
 *
 * ChapterII01/com.ling.learn0103.streamtransform.StreamTransformTest.java
 *
 * author lingang
 *
 * createTime 2020-01-08 23:50:17 
 *
 */
public class StreamTransformTest {
	public static void main(String[] args) {
		// 筛选流-得到另一个流
		Stream<String> stream = Stream.of("aaa", "bb", "cccc");
		Stream<String> subStream1 = stream.filter(s -> s.length() > 2);// 筛选出长度大于2的元素
		subStream1.forEach(s -> System.out.print(s + " "));
		System.out.println();

		// 处理流元素-得到另一个流
		stream = Stream.of("aaa", "bb", "cccc");// 这里必须重新创建待处理的流，如果直接使用stream对象，会抛错：java.lang.IllegalStateException:
												// stream has already been
												// operated upon or closed
		Stream<String> subStream2 = stream.map(s -> s.toUpperCase());// 将流元素转换为大写
		subStream2.forEach(s -> System.out.print(s + " "));
		System.out.println();

		// 将流的元素转换为流后，再将得到的流拼接到原流上
		stream = Stream.of("a|a|a", "b|b", "c|c|c|c");
		Stream<String> subStream3 = stream.flatMap(s -> Stream.of(s.split("\\|")));
		subStream3.forEach(s -> System.out.print(s + " "));
	}
}
