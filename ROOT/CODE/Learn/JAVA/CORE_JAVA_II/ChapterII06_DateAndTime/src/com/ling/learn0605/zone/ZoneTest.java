package com.ling.learn0605.zone;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Set;

/**
 * 时区时间
 *
 * ChapterII06_DateAndTime/com.ling.learn0605.zone.ZoneTest.java
 *
 * author lingang
 *
 * createTime 2020-02-24 19:20:52
 *
 */
public class ZoneTest {
	public static void main(String[] args) {
		// 获取所有时区
		Set<String> zones = ZoneId.getAvailableZoneIds();
		System.out.println("现在时区数量：" + zones.size());
		System.out.println("时区列表：" + zones);

		// 根据zoneId获取时区-zoneId区分大小写
		ZoneId china_shanghai = ZoneId.of("Asia/Shanghai");// 上海
		ZoneId china_chongqing = ZoneId.of("Asia/Chongqing");// 重庆
		ZoneId usa_ny = ZoneId.of("America/New_York");// 纽约
		System.out.println(china_shanghai.getRules());
		System.out.println(china_chongqing.getRules());
		System.out.println(usa_ny.getRules());

		// 将本地时间转换为时区时间
		LocalDateTime ldt = LocalDateTime.of(2020, 2, 24, 18, 33, 40, 666);
		ZonedDateTime zdt_chongqing = ldt.atZone(china_chongqing);
		ZonedDateTime zdt_newyork = ldt.atZone(usa_ny);
		System.out.println("重庆时间：" + zdt_chongqing);
		System.out.println("重庆时间：" + ZonedDateTime.of(ldt, china_chongqing));// 另一种方式
		System.out.println("纽约时间：" + zdt_newyork);
		System.out.println("纽约时间：" + ZonedDateTime.of(ldt, usa_ny));// 另一种方式

		// 直接创建一个时区时间
		zdt_newyork = ZonedDateTime.of(2020, 2, 24, 18, 33, 40, 666, usa_ny);
		System.out.println("纽约时间：" + zdt_newyork);

		// 获取时区时间在不同时区的时间点
		Instant ins_cq = zdt_chongqing.toInstant();
		Instant ins_ny = zdt_newyork.toInstant();
		System.out.println(ins_cq.toEpochMilli());
		System.out.println(ins_ny.toEpochMilli());// 说明中国的18点比美国的18点早些到来
		System.out.println(ldt.atZone(ZoneId.of("UTC")).toInstant().toEpochMilli());// 格林威治皇家天文台处18点时的时间点，同样比美国早

		// ZonedDateTime类的还有很多方法和LocalDateTime类型，详细参考第296页

	}
}
