package com.lg.test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * 打印日历-用于kindle笔记，操作步骤如下：
 * 
 * 1) 设置日期区间，并运行程序
 * 
 * 2) 将打印结果拷到word
 * 
 * 3) word另存为PDF
 *
 * FileOperation/com.lg.test.PrintDateForKindle.java
 *
 * author lingang
 *
 * createTime 2020-06-23 23:49:50
 *
 */
public class PrintDateForKindle {
	private static final LocalDate FROM_DATE = LocalDate.of(2020, 6, 1);// 开始日期
	private static final LocalDate TO_DATE = LocalDate.of(2021, 12, 31);// 结束日期
	private static LocalDate tempDate;
	private static String[] ts = new String[] { "", "\t\t", "\t\t\t\t", "\t\t\t\t\t\t", "\t\t\t\t\t\t\t\t",
			"\t\t\t\t\t\t\t\t\t\t", "\t\t\t\t\t\t\t\t\t\t\t\t" };

	public static void main(String[] args) {
		tempDate = FROM_DATE;
		while (tempDate.isBefore(TO_DATE)) {
			printDate(tempDate);
		}
	}

	private static void printDate(LocalDate date) {
		int dateOfMonth = date.getDayOfMonth();
		String dateStr = String.format("%2s", dateOfMonth);
		int dateOfWeek = date.getDayOfWeek().getValue();
		if (dateOfMonth == 1 || date.isEqual(FROM_DATE)) {
			// 起始日期或者每个月第一天， 要标明月份，且对准星期位置
			if (!date.isEqual(FROM_DATE))
				System.out.println("\n");
			System.out.print("\t\t\t\t\t\t");
			System.out.println(DateTimeFormatter.ofPattern(" yyyy 年 MM 月").format(date));
			System.out.println("\t\t一\t\t二\t\t三\t\t四\t\t五\t\t六\t\t日");

			System.out.print("\t\t" + ts[dateOfWeek - 1] + dateStr);
		} else {
			System.out.print("\t\t" + dateStr);
		}
		tempDate = tempDate.plusDays(1l);
		if (tempDate.getDayOfWeek().getValue() == 1)
			System.out.println();
	}
}
