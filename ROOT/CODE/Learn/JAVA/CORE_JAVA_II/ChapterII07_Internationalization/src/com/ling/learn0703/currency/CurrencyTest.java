package com.ling.learn0703.currency;

import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;
import java.util.Set;

/**
 * 货币
 * 
 * NumberForamt.getCurrencyInstance获取的货币格式化器灵活度不够高，使用Currency类可以更灵活的处理货币
 *
 * ChapterII07_Internationalization/com.ling.learn0703.currency.CurrencyTest.java
 *
 * author lingang
 *
 * createTime 2020-02-25 21:59:00
 *
 */
public class CurrencyTest {
	public static void main(String[] args) {
		// 德国地区的数字和货币符号
		NumberFormat deFm = NumberFormat.getCurrencyInstance(Locale.forLanguageTag("de-DE"));
		System.out.println(deFm.format(12345.66));// 12.345,66 €
		// 设置为德国金额格式，中国的货币符号
		deFm.setCurrency(Currency.getInstance("CNY"));
		System.out.println(deFm.format(12345.66));// 12.345,66 CNY

		// Currency类的一些属性
		Currency cny = Currency.getInstance("CNY");
		System.out.println(cny.getCurrencyCode());// 货币代码
		System.out.println(cny.getSymbol());// 货币符号-默认Locale格式
		System.out.println(cny.getSymbol(Locale.US));// 货币符号-指定Locale格式
		System.out.println(cny.getDefaultFractionDigits());// 金额默认小数位数

		// 获取所有可用货币
		Set<Currency> curs = Currency.getAvailableCurrencies();
		Locale zhcn = Locale.CHINA;
		for (Currency c : curs) {
			System.out.println(c.getCurrencyCode() + ":" + c.getSymbol(zhcn));
		}

	}
}
