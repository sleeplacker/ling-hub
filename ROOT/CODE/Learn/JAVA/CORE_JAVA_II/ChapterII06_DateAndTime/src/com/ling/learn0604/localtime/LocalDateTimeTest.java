package com.ling.learn0604.localtime;

import java.time.LocalDateTime;

/**
 * 日期时间类：即包含了日期和时间的类，同样支持加减，比较，获取属性等操作
 *
 * ChapterII06_DateAndTime/com.ling.learn0604.localtime.LocalDateTimeTest.java
 *
 * author lingang
 *
 * createTime 2020-02-24 19:03:18
 *
 */
public class LocalDateTimeTest {
	public static void main(String[] args) {
		LocalDateTime dt = LocalDateTime.now();
		System.out.println(dt);

		LocalDateTime createDt = LocalDateTime.of(2020, 2, 24, 18, 33);
		createDt = LocalDateTime.of(2020, 2, 24, 18, 33, 40);
		createDt = LocalDateTime.of(2020, 2, 24, 18, 33, 40, 666);
		System.out.println(createDt);

		createDt = createDt.plusDays(35);
		System.out.println(createDt);

		createDt = createDt.plusSeconds(3);
		System.out.println(createDt);

		System.out.println(createDt.getYear());
		System.out.println(createDt.getMonth());
		System.out.println(createDt.getMonthValue());
		System.out.println(createDt.getDayOfYear());
		System.out.println(createDt.getDayOfMonth());
		System.out.println(createDt.getDayOfWeek());
		System.out.println(createDt.getHour());
		System.out.println(createDt.getMinute());
		System.out.println(createDt.getSecond());
		System.out.println(createDt.getNano());
	}
}
