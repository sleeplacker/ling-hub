package com.ling.learn0604.localtime;

import java.time.Duration;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

/**
 * 本地时间
 *
 * ChapterII06_DateAndTime/com.ling.learn0604.localtime.LocalTimeTest.java
 *
 * author lingang
 *
 * createTime 2020-02-24 18:45:10
 *
 */
public class LocalTimeTest {
	public static void main(String[] args) {
		LocalTime nowTime = LocalTime.now();
		System.out.println(nowTime);
		// 设置一个本地时间，根据参数个数不同可以设置不同精确度时间，最后一个参数精确到纳秒
		LocalTime createTime = LocalTime.of(16, 30);
		createTime = LocalTime.of(16, 30, 33);
		createTime = LocalTime.of(16, 30, 33, 333);
		System.out.println(createTime);

		// 时间的加减
		createTime = createTime.plusSeconds(77);
		System.out.println(createTime);
		createTime = createTime.minusHours(26);// 加减方法会自动对参数取模
		System.out.println(createTime);
		createTime = createTime.plus(Duration.of(3000, ChronoUnit.MILLIS));// 加减操作可以传入Duration参数
		System.out.println(createTime);

		// 时间比较
		System.out.println(nowTime.isAfter(createTime));
		System.out.println(nowTime.isBefore(createTime));

		// 获取时间属性
		System.out.println(createTime.getHour());
		System.out.println(createTime.getMinute());
		System.out.println(createTime.getSecond());
		System.out.println(createTime.getNano());

		// 获取时间相距0点0分0秒的长度
		System.out.println("今天过了" + createTime.toSecondOfDay() + "秒了");
		System.out.println("今天过了" + createTime.toNanoOfDay() + "纳秒了");

	}
}
