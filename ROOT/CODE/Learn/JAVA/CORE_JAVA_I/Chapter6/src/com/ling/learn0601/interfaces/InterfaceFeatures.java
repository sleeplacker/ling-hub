package com.ling.learn0601.interfaces;

/**
 * 接口特性
 * 
 * 1. 接口方法默认是public的，定义接口方法时可以省略public修饰符
 * 
 * 2. 接口中定义的变量默认是常量，可以省略public static final修饰符
 * 
 * 3. 接口中可以定义静态方法
 * 
 * 4. 接口中可以为方法定义默认实现，方法实现中可以定义局部变量，调用方法等，只是不能访问实例域，因为接口里面不能定义实例域
 * 		在接口中定义默认实现的作用：
 * 		① 如果一个接口中有多个方法，但是实现类却只需使用其中的一部分，则可以在接口中为一些方法定义默认实现，这样在实现类中就不必实现这些方法
 * 		② 当已经有大量代码实现了某个接口，但是需要在该接口中加入方法，那就需要修改大量代码来实现接口中新增的方法，如果定义了默认实现，则不必修改老代码
 * 
 *
 * Chapter6/com.ling.learn0601.interfaces.InterfaceFeatures.java
 *
 * author lingang
 *
 * createTime 2019-10-24 00:01:10 
 *
 */
public class InterfaceFeatures implements InterfaceTest {
	
	public static void main(String[] args) {
		new InterfaceFeatures().defaultFun();
	}

	@Override
	public void fun() {
		System.out.println(NAME);
	}

	/* 接口中定义了默认实现的方法不需要实现 */

}

interface InterfaceTest {
	void fun(); // 接口方法默认是public的

	String NAME = "LING";// 接口中定义的变量默认是常亮-public static final String name

	static void staticFun() {// 接口中可以定义静态方法
		System.out.println("call static fun in interface");
	}

	default void defaultFun() {// 接口中可以为方法定义默认实现
		staticFun();// 默认实现中可以调用静态方法
		fun();// 默认实现中可以调用接口中的方法
		System.out.println("call default fun");
	}
}
