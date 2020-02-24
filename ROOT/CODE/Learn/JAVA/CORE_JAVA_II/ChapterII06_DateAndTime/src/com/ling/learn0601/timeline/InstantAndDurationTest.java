package com.ling.learn0601.timeline;

import java.time.Duration;
import java.time.Instant;

/**
 * 时间线
 * 
 * 翻译： Instant-瞬间，Duration-持续时间
 *
 * ChapterII06_DateAndTime/com.ling.learn0601.timeline.InstantAndDurationTest.java
 *
 * author lingang
 *
 * createTime 2020-02-24 16:55:24
 *
 */
public class InstantAndDurationTest {
	public static void main(String[] args) {
		Instant start = Instant.now();// 记录开始时间
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Instant end = Instant.now();// 记录结束时间
		Duration dur = Duration.between(start, end);// 计算时间差
		System.out.println("耗时：" + dur.toMillis() + "ms");// 以毫秒格式返回时间差
		System.out.println("耗时：" + dur.toNanos() + "ns");// 以纳秒格式返回时间差

		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Instant end2 = Instant.now();// 记录结束时间
		Duration dur2 = Duration.between(end, end2);

		// Duration和Instant之间可以进行各种计算，包括加减乘除
		Instant preEnd = end2.minus(dur2);// Instant和Duration计算
		Duration preDur = dur2.minus(dur);// Duration和Duration之间进行计算
		System.out.println(Duration.between(start, preEnd).toMillis());
		System.out.println(preDur.toMillis());
	}
}
