package com.ling.learn0606.dateformatandparse;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.time.format.TextStyle;
import java.util.Locale;

/**
 * 日期的格式化和解析
 *
 * ChapterII06_DateAndTime/com.ling.learn0606.dateformatandparse.DateFormatAndParseTest.java
 *
 * author lingang
 *
 * createTime 2020-02-24 23:19:02
 *
 */
public class DateFormatAndParseTest {
	public static void main(String[] args) {
		ZonedDateTime date = ZonedDateTime.now();

		/* 1. 预定义的格式化器 -具体说明见第299页*/
		System.out.println("1. 预定义的格式化器");
		System.out.println(DateTimeFormatter.BASIC_ISO_DATE.format(date));// 20200224+0800
		System.out.println(DateTimeFormatter.ISO_LOCAL_DATE.format(date));// 2020-02-24
		System.out.println(DateTimeFormatter.ISO_LOCAL_TIME.format(date));// 23:27:23.075
		System.out.println(DateTimeFormatter.ISO_LOCAL_DATE_TIME.format(date));//2020-02-24T23:27:23.075
		System.out.println(DateTimeFormatter.ISO_OFFSET_DATE.format(date));//2020-02-24+08:00
		System.out.println(DateTimeFormatter.ISO_OFFSET_TIME.format(date));//23:27:23.075+08:00
		System.out.println(DateTimeFormatter.ISO_OFFSET_DATE_TIME.format(date));// 2020-02-24T23:27:23.075+08:00
		System.out.println(DateTimeFormatter.ISO_ZONED_DATE_TIME.format(date));//2020-02-24T23:27:23.075+08:00[Asia/Shanghai]
		System.out.println(DateTimeFormatter.ISO_INSTANT.format(date));//2020-02-24T15:27:23.075Z
		System.out.println(DateTimeFormatter.ISO_DATE.format(date));// 2020-02-24+08:00
		System.out.println(DateTimeFormatter.ISO_TIME.format(date));// 23:27:23.075+08:00
		System.out.println(DateTimeFormatter.ISO_DATE_TIME.format(date));//2020-02-24T23:27:23.075+08:00[Asia/Shanghai]
		System.out.println(DateTimeFormatter.ISO_ORDINAL_DATE.format(date));// 2020-055+08:00
		System.out.println(DateTimeFormatter.ISO_WEEK_DATE.format(date));//2020-W09-1+08:00
		System.out.println(DateTimeFormatter.RFC_1123_DATE_TIME.format(date));//Mon, 24 Feb 2020 23:27:23 +0800
		
		/* 2. Locale相关的格式化风格 */
		System.out.println("\n2. Locale相关的格式化风格");
		//	指定长度风格
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT);//20-2-24 下午11:33
		System.out.println(formatter.format(date));
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM);//2020-2-24 23:33:41
		System.out.println(formatter.format(date));
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG);//2020年2月24日 下午11时33分41秒
		System.out.println(formatter.format(date));
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);//2020年2月24日 星期一 下午11时33分41秒 CST
		System.out.println(formatter.format(date));
		
		// 指定Locale
		formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL);//2020年2月24日 星期一 下午11时33分41秒 CST
		System.out.println(formatter.withLocale(Locale.CHINA).format(date));//中国地区格式：2020年2月24日 星期一 下午11时38分59秒 CST
		System.out.println(formatter.withLocale(Locale.CHINESE).format(date));//中文格式： 2020年2月24日 星期一 下午11时38分59秒 CST
		System.out.println(formatter.withLocale(Locale.SIMPLIFIED_CHINESE).format(date));//简体中文格式：2020年2月24日 星期一 下午11时38分59秒 CST
		System.out.println(formatter.withLocale(Locale.TRADITIONAL_CHINESE).format(date));//繁体中文格式：2020年2月24日 星期一 下午11時38分59秒 CST
		System.out.println(formatter.withLocale(Locale.TAIWAN).format(date));//台湾地区格式： 2020年2月24日 星期一 下午11時38分59秒 CST
		System.out.println(formatter.withLocale(Locale.ENGLISH).format(date));//英语格式： Monday, February 24, 2020 11:38:59 PM CST
		System.out.println(formatter.withLocale(Locale.JAPAN).format(date));//日本地区格式：2020年2月24日 23時38分59秒 CST
		
		// 根据不同的Locale显示不同格式的星期和月份的名字
		for (DayOfWeek w : DayOfWeek.values()) {
			System.out.print(w.getDisplayName(TextStyle.SHORT, Locale.CHINA) + " ");//星期一 星期二 星期三 星期四 星期五 星期六 星期日 
		}
		System.out.println();
		for (DayOfWeek w : DayOfWeek.values()) {
			System.out.print(w.getDisplayName(TextStyle.SHORT, Locale.JAPAN) + " ");//月 火 水 木 金 土 日 
		}
		System.out.println();
		for (DayOfWeek w : DayOfWeek.values()) {
			System.out.print(w.getDisplayName(TextStyle.SHORT, Locale.ENGLISH) + " ");//Mon Tue Wed Thu Fri Sat Sun 
		}
		System.out.println();
		
		for(Month m : Month.values()){
			System.out.print(m.getDisplayName(TextStyle.FULL, Locale.CHINA) + " ");//一月 二月 三月 四月 五月 六月 七月 八月 九月 十月 十一月 十二月 
		}
		System.out.println();
		
		for(Month m : Month.values()){
			System.out.print(m.getDisplayName(TextStyle.FULL, Locale.JAPAN) + " ");//1 2 3 4 5 6 7 8 9 10 11 12 
		}
		System.out.println();
		
		for(Month m : Month.values()){
			System.out.print(m.getDisplayName(TextStyle.FULL, Locale.ENGLISH) + " ");//Jan Feb Mar Apr May Jun Jul Aug Sep Oct Nov Dec 
		}
		
		System.out.println();
		
		/*3. 自定义格式格式化*/
		System.out.println("\n3. 自定义格式格式化");
		formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");//年月日时分秒毫秒
		System.out.println(formatter.format(date));
		formatter = DateTimeFormatter.ofPattern("G|e|K|a");//分别是：纪元|星期(周日=1)|时钟时间|上午下午
		System.out.println(formatter.format(date));
		formatter = DateTimeFormatter.ofPattern("VV|z|x|O");//分别是：时区ID|时区名|时区偏移量|本地化的时区偏移量
		System.out.println(formatter.format(date));
		
		
		/* 4. 日期解析 */
		System.out.println("\n4. 日期解析");
		LocalDate parsedDate = LocalDate.parse("2018-09-03");// 使用的ISO_LOCAL_DATE格式器
		System.out.println(parsedDate);
		parsedDate = LocalDate.parse("2020年01月01号", DateTimeFormatter.ofPattern("yyyy年MM月dd号"));// 使用自定义格式器
		System.out.println(parsedDate);

		// 解析成ZonedDateTime对象
		ZonedDateTime parsedTime = ZonedDateTime.parse("2019-12-12 12:12:12 +0800",
				DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss xx"));
		System.out.println(parsedTime);
	}
}
