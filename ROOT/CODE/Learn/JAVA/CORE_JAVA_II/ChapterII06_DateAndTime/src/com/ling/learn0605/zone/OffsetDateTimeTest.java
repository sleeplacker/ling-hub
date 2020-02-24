package com.ling.learn0605.zone;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

/**
 * UTC时间偏移量
 * 
 * 偏移量为正：相同时间，秒数值更小，相当于调快时间
 * 偏移量为负：相同时间，秒数值更大，相当于调慢时间
 *
 * ChapterII06_DateAndTime/com.ling.learn0605.zone.OffsetDateTimeTest.java
 *
 * author lingang
 *
 * createTime 2020-02-24 22:57:40
 *
 */
public class OffsetDateTimeTest {
	public static void main(String[] args) {
		// 偏移量范围：-18小时到+18小时
		OffsetDateTime odt = OffsetDateTime.of(2010, 1, 1, 0, 0, 0, 0, ZoneOffset.UTC);
		System.out.println(odt + "：" + odt.toEpochSecond());
		odt = OffsetDateTime.of(2010, 1, 1, 0, 0, 0, 0, ZoneOffset.MIN);
		System.out.println(odt + "：" + odt.toEpochSecond());
		odt = OffsetDateTime.of(2010, 1, 1, 0, 0, 0, 0, ZoneOffset.MAX);
		System.out.println(odt + "：" + odt.toEpochSecond());

		// 自定义偏移量
		odt = OffsetDateTime.of(2010, 1, 1, 0, 0, 0, 0, ZoneOffset.ofHours(1));// 偏移+1个小时
		System.out.println(odt + "：" + odt.toEpochSecond());
		odt = OffsetDateTime.of(2010, 1, 1, 0, 0, 0, 0, ZoneOffset.ofTotalSeconds(-1800));// 偏移-30分钟
		System.out.println(odt + "：" + odt.toEpochSecond());
	}
}
