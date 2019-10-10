package com.ling.learn0402.classandobject;

import java.time.LocalDate;

/**
 * LocalDate类的使用
 *
 * Chapter4/com.ling.learn0402.classandobject.LocalDateTest.java
 *
 * author lingang
 *
 * createTime 2019-10-10 23:30:02
 *
 */
public class LocalDateTest {
	public static void main(String[] args) {
		LocalDate nowDate = LocalDate.now();
		int date = nowDate.getDayOfMonth();

		LocalDate firstDate = nowDate.minusDays(date - 1);

		System.out.println("Mon Tue Wed Thu Fri Sat Sun");
		for (int i = 1; i < firstDate.getDayOfWeek().getValue(); ++i) {
			System.out.print("    ");
		}
		while (firstDate.getMonthValue() == nowDate.getMonthValue()) {
			System.out.printf("%3d", firstDate.getDayOfMonth());
			if (firstDate.equals(nowDate)) {
				System.out.print("*");
			} else {
				System.out.print(" ");
			}

			if (firstDate.getDayOfWeek().getValue() == 7) {
				System.out.println();
			}
			firstDate = firstDate.plusDays(1);
		}
	}
}
