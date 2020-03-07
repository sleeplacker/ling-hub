package com.ling.learn0901.classloader;

/**
 * 用来被ExtendedClassRefSystemClass.java引用
 *
 * ChapterII09_security/com.ling.learn0901.classloader.SystemClassToBeUsed.java
 *
 * author lingang
 *
 * createTime 2020-03-08 01:28:02
 *
 */
public class SystemClassToBeUsed {
	private String name;

	public SystemClassToBeUsed(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SystemClassToBeUsed [name=" + name + "]";
	}

}
