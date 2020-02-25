package com.ling.learn0704.dateandtime;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.time.temporal.WeekFields;
import java.util.Locale;

/**
 * 再谈日期和时间
 *
 * ChapterII07_Internationalization/com.ling.learn0704.dateandtime.DateAndTimeAgain.java
 *
 * author lingang
 *
 * createTime 2020-02-25 22:57:17
 *
 */
public class DateAndTimeAgain {
	public static void main(String[] args) {
		// DateTimeFormatter.ofLocalizedDate方法用于指定格式化的长短风格，包括：FULL，LONG，MEDIUM，SHORT
		// ofLocalizedDate是静态方法
		DateTimeFormatter dtf = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
		System.out.println(dtf.format(ZonedDateTime.now()));

		// withLocale方法用于指定日期打印的语言和地区信息
		// withLocale是实例方法
		dtf = dtf.withLocale(Locale.KOREA);
		System.out.println(dtf.format(ZonedDateTime.now()));

		// LocalDate，LocalTime，LocalDateTime和ZonedDateTime都有静态parse方法来解析日期或时间，需要指定格式化器
		LocalDate zdt = LocalDate.parse("20200225+0800", DateTimeFormatter.BASIC_ISO_DATE);
		zdt = LocalDate.parse("20200225+0800", DateTimeFormatter.BASIC_ISO_DATE);
		System.out.println(zdt);

		// 月份和星期的显示语言
		// 月份
		for (Month m : Month.values()) {
			System.out.print(m.getDisplayName(TextStyle.FULL, Locale.CHINA) + " ");
		}
		System.out.println();
		for (Month m : Month.values()) {
			System.out.print(m.getDisplayName(TextStyle.FULL, Locale.KOREA) + " ");
		}
		System.out.println();
		for (Month m : Month.values()) {
			System.out.print(m.getDisplayName(TextStyle.SHORT, Locale.CHINA) + " ");
		}
		System.out.println();
		for (Month m : Month.values()) {
			System.out.print(m.getDisplayName(TextStyle.NARROW, Locale.CHINA) + " ");
		}
		System.out.println();

		// FULL后面带_STANDALONE是表示单独使用时的显示，比如芬兰语中：在日期格式中一月是tammikuuta，单独使用时，一月是tammikuu
		for (Month m : Month.values()) {
			System.out.print(m.getDisplayName(TextStyle.FULL, Locale.forLanguageTag("fi-FI")) + " ");// 芬兰
		}
		System.out.println();
		for (Month m : Month.values()) {
			System.out.print(m.getDisplayName(TextStyle.FULL_STANDALONE, Locale.forLanguageTag("fi-FI")) + " ");// 芬兰
		}
		System.out.println();
		for (Month m : Month.values()) {
			System.out.print(m.getDisplayName(TextStyle.FULL_STANDALONE, Locale.CHINA) + " ");// 中文就没有单独月份格式
		}
		System.out.println();

		// 星期
		for (DayOfWeek d : DayOfWeek.values()) {
			System.out.print(d.getDisplayName(TextStyle.FULL, Locale.CHINA) + " ");
		}
		System.out.println();

		// 不同Locale中，每个星期的第一天可能不同
		DayOfWeek first = WeekFields.of(Locale.CHINA).getFirstDayOfWeek();
		System.out.println("中国每星期第一天是：" + first.getDisplayName(TextStyle.FULL, Locale.CHINA));
		first = WeekFields.of(Locale.US).getFirstDayOfWeek();
		System.out.println("美国每星期第一天是：" + first.getDisplayName(TextStyle.FULL, Locale.CHINA));
		first = WeekFields.of(Locale.GERMAN).getFirstDayOfWeek();
		System.out.println("德国每星期第一天是：" + first.getDisplayName(TextStyle.FULL, Locale.CHINA));

	}
}
