package com.ling.learn0114.parallelstream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

/**
 * 并行流
 *
 * ChapterII01/com.ling.learn0114.parallelstream.ParallelStreamTest.java
 *
 * author lingang
 *
 * createTime 2020-01-19 20:33:40
 *
 */
public class ParallelStreamTest {
	private static long count = 0;

	public static void main(String[] args) {
		Stream<Integer> si = Stream.iterate(1, a -> a + 1);// 1,2,3,......
		si.limit(10000).forEach(i -> {
			if (i % 2 == 0)
				++count;
		});
		System.out.println(count);// 5000，串行流没问题

		count = 0;
		si = Stream.iterate(1, a -> a + 1).parallel();// 1,2,3,......
		si.limit(10000).forEach(i -> {
			if (i % 2 == 0)
				++count;
		});
		System.out.println(count);// 4288，并行流有问题，因为出现了竞争

		// 并行统计的正确方法
		count = 0;
		si = Stream.iterate(1, a -> a + 1).parallel();// 1,2,3,......
		count = si.limit(10000).filter(i -> i % 2 == 0).count();
		System.out.println(count);

		// 对于一些不在意顺序的并行操作，可以使用unordered方法明确表示对顺序不感兴趣
		Arrays.asList("AA", "BBB", "DDD", "CCCC").parallelStream().unordered().limit(3);

		// 1/2：流创建以后，又修改来原list-不会报错，但是不推荐，因为容易造成歧义
		List<String> lstr = new ArrayList<>();
		lstr.add("A");
		lstr.add("BB");
		lstr.add("CCC");
		// Stream<String> sstr1 = lstr.stream();// 创建流
		// lstr.add("DDDD");// 再往创建流使用list中加入元素
		// System.out.println(Arrays.toString(sstr1.toArray()));// 流能读取到新加入的元素

		// 2/2：流操作过程中改变原list，流操作的结果是未定义的
		Stream<String> sstr2 = lstr.stream();
		sstr2.forEach(s -> {
			if (s.length() % 2 == 0) // java.lang.NullPointerException，在流操作中修改了原list，那么流操作的结果就是未定义的
				lstr.remove(s);
		});

	}
}
