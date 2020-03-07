package com.ling.learn0901.extendedclass;

import com.ling.learn0901.classloader.SystemClassToBeUsed;

/**
 * 引用系统(应用)类加载器加载的类
 *
 * ChapterII09_security/com.ling.learn0901.extendedclass.ExtendedClassRefSystemClass.java
 *
 * author lingang
 *
 * createTime 2020-03-08 01:27:22
 *
 */
public class ExtendedClassRefSystemClass {
	public static void call() {
		System.out.println("class from system class loader : " + new SystemClassToBeUsed("my app class"));
	}
}
