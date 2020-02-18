package com.ling.learn0405.passargument;

/**
 * 方法参数传递
 * 
 * 1. java中的方法参数传递都是值传递方式，而不像C/C++可以使用引用传递方式
 * 
 * 2. 然而java中的变量值有两种：基本数据类型(数值或boolean类型)和对象类型，所以有如下规则： * 一个方法不能修改一个基本数据类型的参数 *
 * 一个方法可以改变一个对象参数的状态 * 一个方法不能让对象参数引用一个新的对象
 *
 * Chapter4/com.ling.learn0405.passargument.ArgumentPassTest.java
 *
 * author lingang
 *
 * createTime 2019-10-12 23:52:36
 *
 */
public class ArgumentPassTest {
	public static void main(String[] args) {
		/* 1. 试图改变方法参数变量的值 */
		System.out.println("1. 试图改变方法参数变量的值-失败");
		int iv = 5;
		doubleValue(iv);
		System.out.println(iv); // 输出为5，修改失败

		System.out.println("\n2. 试图修改方法参数变量指向的对象的状态-成功");
		Student ling = new Student("ling");
		System.out.println("before rename : " + ling);
		changeName(ling, "linzeyi"); // 修改名字为 linzeyi
		System.out.println("after rename : " + ling);

		System.out.println("\n3. 试图交换两个方法参数变量指向的对象-失败");
		Student lg = new Student("lingang");
		Student lzy = new Student("linzeyi");
		System.out.println("before exchange : ");
		System.out.println(lg);
		System.out.println(lzy);
		exchangeVariable(lg, lzy);
		System.out.println("after exchange : ");
		System.out.println(lg);
		System.out.println(lzy);
	}

	/* 试图修改方法参数的值 */
	private static void doubleValue(int iValue) {
		iValue = iValue * 2;
	}

	/* 试图修改学生对象的姓名 */
	private static void changeName(Student stu, String newName) {
		stu.setName(newName);
	}

	/* 试图交换两个变量值 */
	private static void exchangeVariable(Student stu1, Student stu2) {
		Student dTemp = stu1;
		stu1 = stu2;
		stu2 = dTemp;
	}

}

class Student {
	private String name;

	Student(String name) {
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + "]";
	}

}