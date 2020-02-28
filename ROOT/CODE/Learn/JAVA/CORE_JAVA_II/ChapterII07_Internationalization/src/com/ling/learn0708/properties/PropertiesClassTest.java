package com.ling.learn0708.properties;

import java.util.Date;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 属性类
 *
 * ChapterII07_Internationalization/com.ling.learn0708.properties.PropertiesClassTest.java
 *
 * author lingang
 *
 * createTime 2020-02-28 23:35:09
 *
 */
public class PropertiesClassTest {
	public static void main(String[] args) {
		// 注意属性类的定位要用.而不是/，所以这里不是用config/msg/ClassConsMsg，而是config.msg.ClassConsMsg
		ResourceBundle bundle = ResourceBundle.getBundle("config.msg.ClassConsMsg", Locale.CHINA);
		System.out.println(bundle.getString("Name"));
		Date birthday = (Date) bundle.getObject("Birthday");
		System.out.println(birthday);
	}
}
