package com.ling.learn0901.extendedclass;

/**
 * 这个类用来让ExtendedClassRefExtendedClass.java引用
 *
 * ChapterII09_security/com.ling.learn0901.extendedclass.ExtendedClassToBeUsed.java
 *
 * author lingang
 *
 * createTime 2020-03-08 01:26:16
 *
 */
public class ExtendedClassToBeUsed {
	private String name;

	public ExtendedClassToBeUsed(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "ExtendedClassToBeUsed [name=" + name + "]";
	}

}
