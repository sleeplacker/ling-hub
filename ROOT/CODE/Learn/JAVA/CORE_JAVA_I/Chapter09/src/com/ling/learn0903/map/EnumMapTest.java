package com.ling.learn0903.map;

import java.util.EnumMap;
import java.util.EnumSet;

/**
 * 枚举集与映射
 *
 * Chapter09/com.ling.learn0903.map.EnumMapTest.java
 *
 * author lingang
 *
 * createTime 2019-11-22 23:45:39
 *
 */
public class EnumMapTest {
	public static void main(String[] args) {
		/* 所有枚举值 */
		EnumSet<Weekday> always = EnumSet.allOf(Weekday.class);
		System.out.println(always);
		/* 枚举值区间 */
		EnumSet<Weekday> workday = EnumSet.range(Weekday.MONDAY, Weekday.FRIDAY);
		System.out.println(workday);
		/* 选取特定的枚举值 */
		EnumSet<Weekday> someday = EnumSet.range(Weekday.MONDAY, Weekday.THURSDAY);
		System.out.println(someday);
		/* 一个也不选 */
		EnumSet<Weekday> never = EnumSet.noneOf(Weekday.class);
		System.out.println(never);

		/* 构造枚举映射 */
		EnumMap<Weekday, String> em = new EnumMap<>(Weekday.class);
		em.put(Weekday.MONDAY, "work");
		System.out.println(em);
	}
}

enum Weekday {
	MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY, SUNDAY
}