package com.ling.learn0708.properties;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * 资源文件
 * 
 * 此例访问资源文件只能访问ASCII码格式的文件，不能包含中文字符等
 *
 * ChapterII07_Internationalization/com.ling.learn0708.properties.PropertiesTest.java
 *
 * author lingang
 *
 * createTime 2020-02-28 22:39:49
 *
 */
public class PropertiesTest {
	public static void main(String[] args) {
		/*
		 * 定位资源包，定位详细方式参考第334页底部
		 */
		ResourceBundle currentResources = ResourceBundle.getBundle("config/msg/consMsg", Locale.CHINA);// zh_CN
		System.out.println(currentResources.getString("HKD"));
		System.out.println(currentResources.getString("PropertiesVersion"));// 找到了
		/*
		 * XXX_zh_CN会继承XXX_zh和XXX的属性，Common属性仅在consMsg.properties中，
		 * 找到Common属性是因为consMsg_zh_CN.properties继承了consMsg.properties的属性，
		 * 同样也继承了consMsg_zh.properties
		 */
		System.out.println(currentResources.getString("Common"));

		currentResources = ResourceBundle.getBundle("config/msg/consMsg", Locale.TAIWAN);// zh_TW
		System.out.println(currentResources.getString("PropertiesVersion"));// 找到了consMsg_zh.properties

		currentResources = ResourceBundle.getBundle("config/msg/consMsg", Locale.US);// en_US
		System.out.println(currentResources.getString("PropertiesVersion"));// 找到了consMsg_zh_CN.properties，因为默认Locale是zh_CN

		Locale.setDefault(Locale.FRANCE);// 现在修改默认Locale为fr_FR
		currentResources = ResourceBundle.getBundle("config/msg/consMsg", Locale.US);// en_US
		System.out.println(currentResources.getString("PropertiesVersion"));// 现在只能找到默认包consMsg.properties

	}
}
