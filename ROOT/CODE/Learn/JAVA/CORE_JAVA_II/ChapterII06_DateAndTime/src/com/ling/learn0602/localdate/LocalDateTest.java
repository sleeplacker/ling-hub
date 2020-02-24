package com.ling.learn0602.localdate;

import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.temporal.ChronoUnit;

/**
 * LocalDate类操作本地日期
 *
 * ChapterII06_DateAndTime/com.ling.learn0602.localdate.LocalDateTest.java
 *
 * author lingang
 *
 * createTime 2020-02-24 17:42:31
 *
 */
public class LocalDateTest {
	public static void main(String[] args) {
		LocalDate nowDate = LocalDate.now();// 当前本地日期
		System.out.println(nowDate);

		LocalDate birthDateOfII = LocalDate.of(2018, 9, 3);// 使用年月日的格式创建一个日期
		// 使用月份枚举值，因为LocalDate.of方法中的月份是从1开始，为了和java.util.Date中月份从0开始的情况区分，最好使用月份枚举值
		birthDateOfII = LocalDate.of(2018, Month.SEPTEMBER, 3);
		System.out.println(birthDateOfII);

		// 日期的加减
		birthDateOfII = birthDateOfII.plusDays(2);// 加2天
		System.out.println(birthDateOfII);
		birthDateOfII = birthDateOfII.minusDays(5);// 减5天-去到大月份的月底
		System.out.println(birthDateOfII);
		// 加或减1个月-由于下或上一个月不是大月份，会自动调整号数为当月最后一天
		birthDateOfII = birthDateOfII.plusMonths(1);
		System.out.println(birthDateOfII);
		// 再加或减1个月-但是如果从小月份的最后一天加或减一个月到大月份，这是不会去到大月份的最后一天，而是去到和小月分号数相同的日期
		birthDateOfII = birthDateOfII.minusMonths(1);
		System.out.println(birthDateOfII);

		// 计算日期区间
		birthDateOfII = LocalDate.of(2018, 9, 3);
		Period howOld = birthDateOfII.until(nowDate);
		System.out.println("小一一 " + howOld.getYears() + "岁" + howOld.getMonths() + "个月" + howOld.getDays() + "天了");
		System.out.println("小一一" + birthDateOfII.until(nowDate, ChronoUnit.DAYS));// 指定日期区间单位为日

		// 获取各种单位的日期
		System.out.println(nowDate.getYear());// 2020
		System.out.println(nowDate.getMonth());// FEBRUARY，月份枚举值
		System.out.println(nowDate.getMonthValue());// 2，月份号，从1开始
		System.out.println(nowDate.getDayOfYear());// 55，今年第几天
		System.out.println(nowDate.getDayOfMonth());// 24，每月号数
		System.out.println(nowDate.getDayOfWeek());//MONDAY，星期几的枚举值
		System.out.println(nowDate.getDayOfWeek().getValue());//1，星期几，从1开始

	}
}
