package com.ling.learn0501.protectedtestsamepackage;

/**
 * protected关键字总结，要看com.ling.learn0501.protectedtestsamepackage和com.ling.learn0501.protectedtestotherpackage这两个包中的所有类
 * 
 * 1. 子类继承本包中的父类，可以访问父类的包、protected域或方法 
 * 
 * 2. 子类继承其他包中的父类，子类只能访问父类的protected域或方法
 * 
 * 3. 子类对象和父类在同一包，子类对象可以访问父类的包、protected和public域或方法
 * 
 * 4. 子类对象和父类不在同一包，子类对象只能访问父类的public域，而不能访问父类的包和protected域或方法
 *
 * Chapter5/com.ling.learn0501.protectedtestsamepackage.ProtectedTest.java
 *
 * author lingang
 *
 * createTime 2019-10-16 13:46:54
 *
 */
public class ProtectedSamePackageTest {
	public static void main(String[] args) {
		SubClass1 sub1 = new SubClass1();
		SubClass2 sub2 = new SubClass2();
		/* 子类对象和父类在同一包，子类对象可以访问父类的包、protected和public域 */
		System.out.println("sub1.dname = " + sub1.dname);//
		System.out.println("sub1.pname = " + sub1.pname);
		System.out.println("sub1.name = " + sub1.getName());
		/* 子类对象和父类不在同一包，子类对象只能访问父类的public域，而不能访问父类的包和protected域 */
		// System.out.println("sub2.dname = "+sub2.dname);//The field SuperClassInOtherPackage.dname is not visible
		// System.out.println("sub2.pname = "+sub2.pname);//The field SuperClassInOtherPackage.pname is not visible
		System.out.println("sub2.name = " + sub2.getName());
	}
}