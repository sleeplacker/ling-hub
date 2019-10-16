package com.ling.learn0501.protectedtestotherpackage;

import com.ling.learn0501.protectedtestsamepackage.SubClass1;
import com.ling.learn0501.protectedtestsamepackage.SubClass2;

/**
 * 将com.ling.learn0501.protectedtestsamepackage包中的ProtectedSamePackageTest.java搬到此包中
 * 
 * 得出一个新的结论：子类对象和父类在同一包，但是子类和引用子类对象的类不在同一包，只能访问父类的protected和public域，而不能访问包访问权限的域
 *
 * Chapter5/com.ling.learn0501.protectedtestsamepackage.ProtectedTest.java
 *
 * author lingang
 *
 * createTime 2019-10-16 13:46:54
 *
 */
public class ProtectedOtherPackageTest {
	public static void main(String[] args) {
		SubClass1 sub1 = new SubClass1();
		SubClass2 sub2 = new SubClass2();
		SuperClassInOtherPackage sub3 = sub2;
		/* 子类对象和父类不在同一包，子类对象只能访问父类的public域，而不能访问父类的包和protected域 */
		// System.out.println("sub1.dname = " + sub1.dname);//不可见
		// System.out.println("sub1.pname = " + sub1.pname);//不可见
		System.out.println("sub1.name = " + sub1.getName());
		/*
		 * 子类对象和父类在同一包，但是子类和引用子类对象的类不在同一包，只能访问父类的protected和public域，而不能访问包访问权限的域
		 */
		// System.out.println("sub2.dname = "+sub2.dname);//不可见
		/*
		 * sub2和sub3指向同一对象，由于sub2是SubClass2类的变量，而SubClass2类不在本包，所以不能通过sub2访问包域
		 * 而sub3是SuperClassInOtherPackage对象，在本包中，所以可以通过sub3访问包域
		 */
		System.out.println("sub3.dname = " + sub3.dname);
		System.out.println("sub2.pname = " + sub2.pname);
		System.out.println("sub2.name = " + sub2.getName());
	}
}