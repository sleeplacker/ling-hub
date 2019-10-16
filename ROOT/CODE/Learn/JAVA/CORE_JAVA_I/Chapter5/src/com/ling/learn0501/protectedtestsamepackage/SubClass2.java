package com.ling.learn0501.protectedtestsamepackage;

import com.ling.learn0501.protectedtestotherpackage.SuperClassInOtherPackage;

/**
 * 子类继承其他包中的父类，子类只能访问父类的protected访问权限域
 *
 * Chapter5/com.ling.learn0501.protectedtestsamepackage.SubClass2.java
 *
 * author lingang
 *
 * createTime 2019-10-16 13:41:31 
 *
 */
public class SubClass2 extends SuperClassInOtherPackage {
	public String getName() {
		// return dname + pname; //The field SuperClassInOtherPackage.dname is not visible 不能方法其他包中的包访问权限域
		return "null" + pname; // 如果父类在其他包中，子类只能访问父类的protected访问权限域
	}
}
