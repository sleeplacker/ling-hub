package com.ling.learn0901.classloader;

import java.util.Date;

/**
 * 试图使用自己定义的Date类
 * 
 * 从打印结果看还是使用到了系统定义的Date类，因为类加载机制会先委托父类加载器去加载类，如果加载到了就不会再在其他路径加载
 *
 * ChapterII09_security/com.ling.learn0901.classloader.DateTest.java
 *
 * author lingang
 *
 * createTime 2020-03-08 02:03:23
 *
 */
public class DateTest {
	public static void main(String[] args) {
		Date d = new Date();
		System.out.println(d);// Sun Mar 08 02:04:01 CST 2020
	}
}
