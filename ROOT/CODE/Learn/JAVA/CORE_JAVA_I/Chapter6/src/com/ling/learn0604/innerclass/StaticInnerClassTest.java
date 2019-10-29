package com.ling.learn0604.innerclass;

/**
 * 静态内部类
 * 
 * 1. 当内部类不需要访问外部类的对象，可以将内部类定义为static的，这样内部类中就不需要保存指向外部类对象的引用
 * 
 * 2. 静态方法中只能构造静态内部类对象，不能构造非静态内部类的对象
 * 
 * 3. 静态内部类中可以定义静态域和静态方法，但是非静态内部类不行
 * 
 * 4. 接口中的内部类自动是static和public的，因为接口中没有实例域供内部类访问，所以内部类也不需要指向外部类对象的引用
 *
 * Chapter6/com.ling.learn0604.innerclass.StaticInnerClassTest.java
 *
 * author lingang
 *
 * createTime 2019-10-29 13:17:47
 *
 */
public class StaticInnerClassTest {

}

class OuterClass {
	@SuppressWarnings("unused")
	public static void fun() {
		/* 静态方法中只能构造静态内部类对象 */
		StaticInnerClass sic = new StaticInnerClass();
		/*
		 * No enclosing instance of type OuterClass is accessible. Must qualify
		 * the allocation with an enclosing instance of type OuterClass
		 *
		 * 不能在静态方法中构造非静态内部类对象
		 */
		// NormalInnerClass nic = new NormalInnerClass();
	}

	static class StaticInnerClass { // 静态内部类
		static int iVal; // 静态内部类中可以有静态域

		static void fun1() {// 静态内部类中可以有静态方法

		}
	}

	class NormalInnerClass {// 普通内部类
		// static int iVal; // 普通内部类中不能有静态域
		// static void fun1(){// 普通内部类中不能有静态方法
		// }
	}
}

interface IT {
	class SC { // 接口中的内部类自动是static和public的，因为接口中没有实例域供内部类访问，所以内部类也不需要指向外部类对象的引用
		static int iv;// 可以定义静态域和静态方法，说明是静态内部类

		static void fun2() {
		}
	}
}
