package com.ling.learn0701.locale;

import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Locale;

/**
 * Locale对象
 * 
 * Locale对象的组成结果包含5部分，包括必须的语言部分，和后面的4个可选部分，具体参考第305页介绍
 *
 * ChapterII07_Internationalization/com.ling.learn0701.locale.LocaleTest.java
 *
 * author lingang
 *
 * createTime 2020-02-25 20:43:41
 *
 */
public class LocaleTest {
	public static void main(String[] args) {
		// 获取默认Locale
		Locale defaultLoc = Locale.getDefault();
		System.out.println(defaultLoc);
		// 设置默认Locale，只作用于本程序，不会修改系统参数
		Locale.setDefault(new Locale("en", "US"));
		System.out.println(Locale.getDefault());

		// 根据语言或国家创建Locale
		Locale zh = new Locale("zh");// 只传1个语言参数
		System.out.println(zh);

		Locale de = new Locale("de", "DE");// 传语言和国家参数
		System.out.println(de);

		// 使用预定义的Locale对象
		zh = Locale.CHINA;// 预定义语言/国家Locale
		System.out.println(zh);
		System.out.println(zh.getDisplayName());// 以易读的方式显示国家和语言信息

		zh = Locale.CHINESE;
		System.out.println(zh);// 预定义的语言Locale，不包含国家信息
		System.out.println(zh.getDisplayName());

		// 指定查看语言/国家使用的Locale
		System.out.println(de.getDisplayName());// 不指定，默认是英文
		System.out.println(de.getDisplayName(Locale.CHINA));// 指定用中文来展示德国的Locale

		// 获取Locale对象中的国家和语言
		System.out.println(zh.getLanguage());
		System.out.println(zh.getCountry());// 国家可以为空
		System.out.println(zh.getDisplayLanguage());
		System.out.println(zh.getDisplayLanguage(Locale.TAIWAN));
		System.out.println(zh.getDisplayCountry());// 国家可以为空
		System.out.println(zh.getDisplayCountry(Locale.ENGLISH));// 国家可以为空
		System.out.println("---end---");

		// 查看Locale对应的标签
		System.out.println(zh.toLanguageTag());
		zh = Locale.JAPAN;
		System.out.println(zh.toLanguageTag());

		// 从标签创建Locale对象，注意分隔符是-而不是_
		zh = Locale.forLanguageTag("zh-CN");
		System.out.println(zh.getDisplayName());
		zh = Locale.forLanguageTag("zh-HK");
		System.out.println(zh.getDisplayName());
		zh = Locale.forLanguageTag("zh-HANS");
		System.out.println(zh.getDisplayName());

		// 语言和国家没有联系，中文可以和韩国，德国放在一起组成Locale对象
		zh = Locale.forLanguageTag("zh-KR");
		System.out.println(zh.getDisplayName());
		zh = Locale.forLanguageTag("zh-DE");
		System.out.println(zh.getDisplayName());

		// 返回虚拟机能识别的所有Locale数组
		Locale[] locales = Locale.getAvailableLocales();
		System.out.println(Arrays.toString(locales));

		// 也可以用与Locale相关的工具类返回虚拟机能识别的所有Locale数组，结果和上面一致
		locales = NumberFormat.getAvailableLocales();
		System.out.println(Arrays.toString(locales));
	}
}
