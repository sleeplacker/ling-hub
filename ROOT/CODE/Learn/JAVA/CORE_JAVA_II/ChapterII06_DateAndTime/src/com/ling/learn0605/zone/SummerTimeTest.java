package com.ling.learn0605.zone;

import java.time.Duration;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;

/**
 * 夏令时：有些国家在夏天将时间调快1个小时，再在冬天时将时间调慢回去，中国现在没有夏令时
 * 
 * 注意：夏时令和冬时令调时间时，时区也会发生变化
 *
 * ChapterII06_DateAndTime/com.ling.learn0605.zone.SummerTimeTest.java
 *
 * author lingang
 *
 * createTime 2020-02-24 22:25:18
 *
 */
public class SummerTimeTest {
	public static void main(String[] args) {
		// 设置时间为2013-3-31 2:30
		ZonedDateTime normalTime = ZonedDateTime.of(2013, 3, 31, 2, 30, 0, 0, ZoneId.of("Asia/Chongqing"));
		// 重庆时间显示正确，因为中国在2013年是不支持夏令时的
		System.out.println(normalTime);
		ZonedDateTime summerTime = ZonedDateTime.of(2013, 3, 31, 2, 30, 0, 0, ZoneId.of("Europe/Berlin"));
		// 德国柏林时间显示的是3:30，因为该地区支持夏令时，时间在此时被调快了一个小时，2:30是不存在的时间，ZonedDateTime自动推到3:30
		System.out.println(summerTime);

		// 冬时令-将时间调慢1小时，那么这个小时，比如2:30这个时刻会过两次，但是两次在时间线其实是不同时刻，ZonedDateTime的做法是取较早的一个时刻
		ZonedDateTime winterTime = ZonedDateTime.of(2013, 10, 27, 2, 30, 0, 0, ZoneId.of("Europe/Berlin"));
		System.out.println(winterTime);
		winterTime = winterTime.plusHours(1);// 加一个小时
		System.out.println(winterTime);// 结果还是2:30，只不过时区变了
		winterTime = winterTime.plusHours(1);// 再加一个小时
		System.out.println(winterTime);// 现在已经不在冬时令范围，所以时间变成了3:30，时区不变

		// 在使用夏时令的地区，日期加操作要注意
		// 比如今天10点开了会
		ZonedDateTime toDayMeeting = ZonedDateTime.of(2013, 3, 30, 10, 0, 0, 0, ZoneId.of("Europe/Berlin"));
		System.out.println(toDayMeeting);
		// 希望下周10点再开会
		// 如果使用Duration，那计算的是绝对的时间，即7天1秒都不少，不管夏时令，这样在支持夏时令的地区，由于时间调快了1个小时，所以下周开会时间就变成了11点
		ZonedDateTime nextMeeting = toDayMeeting.plus(Duration.ofDays(7));
		System.out.println(nextMeeting);

		// 这时应该使用Period来指示日期区间，它会处理调时间的情况
		nextMeeting = toDayMeeting.plus(Period.ofDays(7));
		System.out.println(nextMeeting);
	}
}
