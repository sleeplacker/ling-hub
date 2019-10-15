package com.ling.learn0501.superandsubclass;

/**
 * 子类中的方法查找
 * 
 * 查找原则，分为两个维度：
 * 
 * ①先检查方法的匹配性，如果多个方法都能匹配参数，则选择最匹配的方法，不管方法离当前类有多少层继承
 * ②再检查方法的距离，如果子类和父类有相同方法签名且完全匹配参数的方法，优先选择当前这个子类的方法
 *
 * warning：子类覆盖父类方法时，可见性不能低于父类该方法的可见性，否则有编译错误
 *
 * Chapter5/com.ling.learn0501.superandsubclass.MethodSearchTest.java
 *
 * author lingang
 *
 * createTime 2019-10-16 00:40:13
 *
 */
public class MethodSearchTest extends Super {
	public static void main(String[] args) {
		MethodSearchTest test = new MethodSearchTest();
		/*
		 * call super f(int)
		 * 
		 * 虽然当前类的void f(double d)方法也适合该参数(自动类型转换)，但还是选择父类中参数类型更加匹配的
		 */
		test.f(1);

		/*
		 * call sub f(short)
		 * 
		 * 子类和父类有相同方法签名且完全匹配参数的方法，优先选择当前这个子类的void f(int i)方法
		 */
		test.f((short) 1);//
		/*
		 * call super f(Manager)，
		 * 
		 * 虽然当前类的void f(Employee
		 * man)方法也适合该参数(Employee是Manager的父类)，但还是选择父类中参数类型更加匹配的void f(Employee
		 * man)方法
		 */
		test.f(new Manager("ling", 10000, 200));
	}

//	void f(String s) { // 编译错误：Cannot reduce the visibility of the inherited method from Super ，子类覆盖父类方法时，可见性不能低于父类该方法的可见性
//		System.out.println("call sub f(String)");
//	}

	void f(short i) {
		System.out.println("call sub f(short)");
	}

	void f(double d) {
		System.out.println("call sub f(double)");
	}

	void f(Employee man) {
		System.out.println("call sub f(Employee)");
	}

}

class Super {
	void f(int i) {
		System.out.println("call super f(int)");
	}

	void f(short i) {
		System.out.println("call super f(short)");
	}

	public void f(String s) {
		System.out.println("call super f(String)");
	}

	void f(Manager man) {
		System.out.println("call super f(Manager)");
	}
}
