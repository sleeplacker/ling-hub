package com.ling.learn0501.protectedtestsamepackage;

/**
 * 子类继承本包中的父类，可以访问父类的包访问权限域和protected访问权限域
 *
 * Chapter5/com.ling.learn0501.protectedtestsamepackage.SubClass1.java
 *
 * author lingang
 *
 * createTime 2019-10-16 13:42:01 
 *
 */
public class SubClass1 extends SuperClassInSamePackage {
	public String getName() {
		return dname + pname; // 在同一包中子类可以访问父类的包访问权限域和protected访问权限域
	}
}
