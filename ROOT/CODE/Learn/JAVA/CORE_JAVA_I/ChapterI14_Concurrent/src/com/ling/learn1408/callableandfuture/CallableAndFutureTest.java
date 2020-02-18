package com.ling.learn1408.callableandfuture;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

/**
 * 执行可以返回值的任务
 *
 * Chapter14/com.ling.learn1408.callableandfuture.CallableAndFutureTest.java
 *
 * author lingang
 *
 * createTime 2019-12-22 23:46:33 
 *
 */
public class CallableAndFutureTest {
	public static void main(String[] args) throws Exception {
		/* Callable接口 */
		Callable<Integer> caller = new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {// Callable接口只有一个方法
				Thread.sleep(3000);
				return 2;
			}

		};
		System.out.println(caller.call());// Callable接口不能与线程协同工作(不能作为Thread的构造函数参数传入)，所以调用的时候不会启动新线程，要等待call方法执行结束

		/*
		 * FutureTask可以将Callable转换为Future和Runnable，也就是可以将它的对象传入Thread中执行，
		 * 也可以在他的对象上调用Future的方法
		 */
		FutureTask<Integer> ft = new FutureTask<>(caller);
		// System.out.println(ft.get());//直接调用Future的get方法会一直阻塞，因为这个任务还没有被执行，即Callable的call方法还没被调用
		// 启动新线程调用Callable的call方法
		new Thread(ft).start();// FutureTask实现了Runnable接口，所以可以传入Thread的构造函数
		System.out.println(ft.get());// get方法会阻塞，直到FutureTask对应的线程执行完
	}
}
