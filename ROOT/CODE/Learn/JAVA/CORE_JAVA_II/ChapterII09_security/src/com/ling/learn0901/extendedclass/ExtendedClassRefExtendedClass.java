package com.ling.learn0901.extendedclass;

/**
 * 引用扩展类加载器加载的类
 *
 * ChapterII09_security/com.ling.learn0901.extendedclass.ExtendedClassRefExtendedClass.java
 *
 * author lingang
 *
 * createTime 2020-03-08 01:25:46
 *
 */
public class ExtendedClassRefExtendedClass {
	public static void call() {
		System.out.println("class from extended class loader : " + new ExtendedClassToBeUsed("extended class"));
	}
}
