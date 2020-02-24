package com.ling.learn0603.dateadjuster;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.Temporal;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

/**
 * 日期调整器：方便指定诸如某个月第1个星期日这样的日期
 *
 * ChapterII06_DateAndTime/com.ling.learn0603.dateadjuster.DataAdjusterTest.java
 *
 * author lingang
 *
 * createTime 2020-02-24 18:14:32
 *
 */
public class DataAdjusterTest {
	public static void main(String[] args) {
		// 指定2020年2月第一个星期日
		LocalDate adjDate = LocalDate.of(2020, 2, 1);// 先创建当月第一天的日期
		// 使用TemporalAdjusters类来调整日期，指向第一个星期日
		adjDate = adjDate.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
		System.out.println(adjDate);

		// TemporalAdjusters还有很多调整方法，详细看第294页或者在类后面打.看

		// 自定义调整器：获取下个一周末日期
		adjDate = adjDate.with(new TemporalAdjuster() {

			@Override
			public Temporal adjustInto(Temporal temporal) {
				LocalDate temp = (LocalDate) temporal;
				do {
					temp = temp.plusDays(1);
				} while (temp.getDayOfWeek().getValue() < 6);
				return temp;
			}
		});
		System.out.println(adjDate);

		// 自定义调整器：避免强制类型转换
		adjDate = adjDate.with(TemporalAdjusters.ofDateAdjuster(adjuster -> {
			LocalDate temp = (LocalDate) adjuster;
			do {
				temp = temp.plusDays(1);
			} while (temp.getDayOfWeek().getValue() >= 6);
			return temp;
		}));
		System.out.println(adjDate);
	}
}
