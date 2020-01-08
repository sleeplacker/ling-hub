package com.ling.learn0102.createstream;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 流的创建
 *
 * ChapterII01/com.ling.learn0102.createstream.StreamCreateTest.java
 *
 * author lingang
 *
 * createTime 2020-01-08 23:20:20 
 *
 */
public class StreamCreateTest {
	public static void main(String[] args) {
		// 从数组(或者可变长参数列表)创建流
		String[] strs = { "Three", "Little", "Indian" };
		Stream<String> stream1 = Stream.of(strs);
		stream1.forEach(s -> System.out.print(s + " "));
		System.out.println();

		Stream<String> stream2 = Arrays.stream(strs);// 另一种创建流的方法
		stream2.forEach(s -> System.out.print(s + " "));
		System.out.println();
		Stream<String> stream3 = Arrays.stream(strs, 1, 3);// 指定数组区间来创建流
		stream3.forEach(s -> System.out.print(s + " "));

		// 创建空流
		Stream<String> emptyStream = Stream.empty();
		System.out.println(emptyStream.count());

		// 创建无限流
		Stream<Double> stream4 = Stream.generate(Math::random);// 流中的每个元素都是单独产生的
		Iterator<Double> iter1 = stream4.iterator();
		System.out.println(iter1.next());
		System.out.println(iter1.next());// . . .

		Stream<Integer> stream5 = Stream.iterate(2, x -> x * x);// 流中每个元素的产生依赖上一个元素运算得出
		Iterator<Integer> iter2 = stream5.iterator();
		System.out.println(iter2.next());
		System.out.println(iter2.next());
		System.out.println(iter2.next());

		// JDK中能创建流的现成方法
		Stream<String> stream6 = Pattern.compile("\\|").splitAsStream("Down|The|Stream");// 使用正则表达式分隔字符串来创建流
		stream6.forEach(s -> System.out.print(s + " "));
		System.out.println();

		try (Stream<String> stream7 = Files.lines(Paths.get(new URI("file:/D:/aaa.txt")))) {//获得文件中所有行的流
			stream7.forEach(s -> System.out.print(s + " "));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (URISyntaxException e1) {
			e1.printStackTrace();
		}
	}
}
