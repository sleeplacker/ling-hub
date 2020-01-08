package com.ling.learn0101.hellostream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

/**
 * 初识流
 *
 * ChapterII01/com.ling.learn0101.hellostream.StreamTest.java
 *
 * author lingang
 *
 * createTime 2020-01-08 22:33:38 
 *
 */
public class StreamTest {
	public static void main(String[] args) {
		List<String> list = Arrays.asList("row the boat down the stream".split(" "));

		// 传统的遍历list的方法
		for (String s : list) {
			System.out.println(s);
		}

		// 使用流的方式遍历list
		Stream<String> stream = list.stream();
		stream.forEach(str -> System.out.println("word : " + str));

		Stream<String> pstream = list.parallelStream();// 并行流
		pstream.forEach(str -> System.out.println("pword : " + str));// 并行流使用方式和普通流一样，只是底层会并行执行
	}
}
