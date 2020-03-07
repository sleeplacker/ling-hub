package com.ling.learn0901.extendedclass;

import java.util.Date;

/**
 * 引用引导类加载器加载的类
 *
 * ChapterII09_security/com.ling.learn0901.extendedclass.ExtendedClassRefBootClass.java
 *
 * author lingang
 *
 * createTime 2020-03-08 01:23:45
 *
 */
public class ExtendedClassRefBootClass {
	public static void call() {
		System.out.println("class from bootstrap class loader : " + new Date());
	}
}
