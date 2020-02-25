package com.ling.learn0702.numberformat;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * 数字格式化
 *
 * ChapterII07_Internationalization/com.ling.learn0702.numberformat.NumberFormatTest.java
 *
 * author lingang
 *
 * createTime 2020-02-25 21:22:52
 *
 */
public class NumberFormatTest {
	public static void main(String[] args) throws ParseException {
		/* 1. 数字金额的格式化 */
		// 使用德国Locale格式化数字金额
		Locale loc = Locale.forLanguageTag("de-DE");
		NumberFormat deFmt = NumberFormat.getCurrencyInstance(loc);
		double amt = 1234567.88;
		System.out.println(deFmt.format(amt));// 德国用小数点分隔数字，逗号当小数点，货币符号在后

		// 使用中国Locale格式化数字金额-会带上货币符号￥
		NumberFormat chFmt = NumberFormat.getCurrencyInstance(Locale.CHINA);
		System.out.println(chFmt.format(amt));

		/* 2. 数字金额的解析 */
		String inputText = "￥1,234,567.88";
		Number parsedNumber = chFmt.parse(inputText);
		System.out.println(parsedNumber.doubleValue());

		/* 3. 不同类型的格式化器 */
		// 数字格式化器
		chFmt = NumberFormat.getNumberInstance();
		System.out.println(chFmt.format(amt));
		chFmt = NumberFormat.getNumberInstance(Locale.TAIWAN);
		System.out.println(chFmt.format(amt));
		// 货币格式化器
		chFmt = NumberFormat.getCurrencyInstance();
		System.out.println(chFmt.format(amt));
		chFmt = NumberFormat.getCurrencyInstance(Locale.TAIWAN);
		System.out.println(chFmt.format(amt));
		// 百分比格式化器
		chFmt = NumberFormat.getPercentInstance();
		System.out.println(chFmt.format(amt));
		chFmt = NumberFormat.getPercentInstance(Locale.TAIWAN);
		System.out.println(chFmt.format(amt));

		/* 4. 格式化器的各种标志位的设置和获取 */
		chFmt = NumberFormat.getNumberInstance(Locale.CHINESE);
		// 是否只允许解析整数值
		System.out.println(chFmt.isParseIntegerOnly());// 默认是false
		chFmt.setParseIntegerOnly(true);// 设置为只允许解析整数值
		// 当设置为只允许解析整数值，并解析小数时，小数点后面会被截断
		System.out.println(chFmt.parse("3.88"));
		// 是否识别十进制分隔符-即12,345这种数字
		System.out.println(chFmt.isGroupingUsed());// 默认是ture
		chFmt.setGroupingUsed(false);// 设置为不能否识别十进制分隔符
		// 当设置为不能否识别十进制分隔符，并解析带分隔符的数字字符串时，会将第一个分隔符后面的全部截断
		System.out.println(chFmt.parseObject("12,345,678.99"));

		amt = 1234.56789;
		// 下面的4个方法有对应的set版本来设置新值
		System.out.println(chFmt.getMinimumIntegerDigits());// 整数最小位数：1
		System.out.println(chFmt.getMaximumIntegerDigits());// 整数最大位数：2147483647
		System.out.println(chFmt.getMinimumFractionDigits());// 小数点后最小位数：0
		System.out.println(chFmt.getMaximumFractionDigits());// 小数点后最大位数：3
		System.out.println(chFmt.format(amt));
		chFmt.setMaximumFractionDigits(4);
		System.out.println(chFmt.format(amt));
	}
}
