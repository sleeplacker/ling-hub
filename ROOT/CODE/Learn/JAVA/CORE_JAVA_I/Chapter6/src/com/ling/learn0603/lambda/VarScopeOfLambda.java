package com.ling.learn0603.lambda;

import javax.swing.Timer;

/**
 * lambda表达式中变量的作用域
 * 
 * lambda表达式可以捕获外部变量(捕获的变量会进入lambda表达式闭包，所以当外部变量已经离开作用域，lambda表达式中还是能使用该变量)，但是捕获的变量必须是实际不可变的变量(不需要一定是final声明的变量，只有变量赋值后不会改变就行)
 *
 * Chapter6/com.ling.learn0603.lambda.VarScopeOfLambda.java
 *
 * author lingang
 *
 * createTime 2019-10-25 01:24:01
 *
 */
public class VarScopeOfLambda {
	private String msg2 = " msg2";
	public static void main(String[] args) throws InterruptedException {
		new VarScopeOfLambda().repeatMsg("Hello", 3000);
		// 因为定时器中的操作是在新线程中执行，为了防止main方法立即执行完，睡上10秒才能看定时器效果
		Thread.sleep(10000);
	}

	public void repeatMsg(String msg, int delay) {
		/*
		 * 下面一行会报错：Local variable msg defined in an enclosing scope must be
		 * final or effectively final
		 */
		// msg = "new msg";

		/*
		 * lambda表达式中能取到调用它的方法的参数，这里lambda表达式捕获(capture)了外部变量msg
		 * 
		 * 在lambda表达式中使用this关键字，是引用的外部类的，而不是lambda表达式本身
		 */
		new Timer(delay, event -> System.out.println(msg+this.msg2)).start();
	}
}
