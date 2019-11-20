package com.ling.learn0404.staticfieldandmethod;

/**
 * main方法
 * 
 * 分别使用命令行执行java com.ling.learn0404.staticfieldandmethod.MainMethodTest
 *  * 和java com.ling.learn0404.staticfieldandmethod.Student，会分别执行MainMethodTest类和Student类中的main方法
 *
 * Chapter4/com.ling.learn0404.staticfieldandmethod.MainMethodTest.java
 *
 * author lingang
 *
 * createTime 2019-10-12 23:30:42
 *
 */
public class MainMethodTest {
	public static void main(String[] args) {
		System.out.println("Unit test at class MainMethodTest");
		Student stu = new Student("ling");
		System.out.println(stu);
	}
}

class Student {
	private String name;

	Student(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + "]";
	}

	public static void main(String[] args) {
		System.out.println("Unit test at class Student . . .");
	}
}
