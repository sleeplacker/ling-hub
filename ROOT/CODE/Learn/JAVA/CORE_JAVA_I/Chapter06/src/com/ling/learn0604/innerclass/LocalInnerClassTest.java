package com.ling.learn0604.innerclass;

/**
 * 局部内部类
 * 
 * 1. 局部内部类只能在声明它的块中被访问，外部类和其他任何地方都不能访问
 * 
 * 2. 在局部内部类中使用外部方法的参数或者局部变量，则这些使用的变量都会被内部类保存一份，没有使用的则不会保存，
 * 		测试方法：到ReflectVisitClassTest.java中输入：com.ling.learn0604.innerclass.A$1B会看到有相关的域定义
 *
 * Chapter6/com.ling.learn0604.innerclass.LocalInnerClassTest.java
 *
 * author lingang
 *
 * createTime 2019-10-29 00:07:21
 *
 */
public class LocalInnerClassTest {
	public static void main(String[] args) {

	}
}

class A { // 外部内
	@SuppressWarnings("unused")
	void fun1(int iVal, String str) { // 外部方法
		int localVal = 0;// 局部变量
		String localStr = "aa";// 局部变量
		// public class B { // 局部内部类不能被public和private修饰符，因为它的作用域只有声明它的块
		// private class B {
		class B { // 局部内部类-在代码块(例如方法)中声明的类
			void funcInB() {
				System.out.println(localVal); // 在局部内部类中使用外部方法的参数或者局部变量，则这些使用的变量都会被内部类保存一份，没有使用的则不会保存
				System.out.println(iVal);
			}
		}

		B bb = new B();// 只有在声明内部类的块中才可以访问局部内部类
	}

	@SuppressWarnings("unused")
	void fun2() {
		/* 局部内部类只能在声明它的块中被访问，外部类不能访问 */
		// B b = new B();
		C c = new C(); // 普通内部类可以被外部类访问
	}

	class C {

	}
}
