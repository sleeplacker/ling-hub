package com.ling.learn0807.extendingeneric;

import java.util.ArrayList;
import java.util.List;

/**
 * 泛型类型的继承规则
 *
 * Chapter8/com.ling.learn0807.extendingeneric.ExtendInGenericTest.java
 *
 * author lingang
 *
 * createTime 2019-11-20 01:25:24
 *
 */
public class ExtendInGenericTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Manager manager = new Manager();
		Employee emp = manager;// OK
		List<Manager> managers = new ArrayList<>();
		/*
		 * 1. 泛型类的类型参数的继承关系不适用于泛型类
		 * 
		 * 所以下面一行编译报错：Type mismatch: cannot convert from List<Manager> to
		 * List<Employee>
		 */
		// List<Employee> emps = managers;
	}
}

class Employee {

}

class Manager extends Employee {

}