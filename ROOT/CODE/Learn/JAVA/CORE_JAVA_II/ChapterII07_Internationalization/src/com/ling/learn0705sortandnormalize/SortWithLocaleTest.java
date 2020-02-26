package com.ling.learn0705sortandnormalize;

import java.text.Collator;
import java.util.Arrays;
import java.util.Locale;

/**
 * 与Locale相关的排序
 *
 * ChapterII07_Internationalization/com.ling.learn0705sortandnormalize.SortWithLocaleTest.java
 *
 * author lingang
 *
 * createTime 2020-02-26 12:54:29
 *
 */
public class SortWithLocaleTest {
	public static void main(String[] args) {

		String[] words = { "able", "America", "zebra", "Benz", "Ångström" };// Ångström是计量单位，1埃=10的-10次方

		// 默认排序方式：按UTF-16的编码值排序，即大写字母->小写字母->拉丁文字母
		// [America, Benz, able, zebra, Ångström]
		Arrays.sort(words);
		System.out.println(Arrays.toString(words));

		// 使用默认的排序器(这里相当于使用Locale.CHINA)：
		// 会按不区分大小写，也不区分是不是拉丁字母来排序
		// [able, America, Ångström, Benz, zebra]
		Collator coll = Collator.getInstance();
		Arrays.sort(words, coll);
		System.out.println(Arrays.toString(words));

		// 使用瑞典的Locale来排序：瑞典的Å在大小写字母之后
		// [able, America, Benz, zebra, Ångström]
		coll = Collator.getInstance(Locale.forLanguageTag("sv-SE"));
		Arrays.sort(words, coll);
		System.out.println(Arrays.toString(words));

	}
}
