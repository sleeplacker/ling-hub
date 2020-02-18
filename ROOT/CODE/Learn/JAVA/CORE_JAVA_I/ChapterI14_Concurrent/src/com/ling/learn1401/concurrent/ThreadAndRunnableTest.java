package com.ling.learn1401.concurrent;

/**
 * 线程和任务
 * 
 * 1. 线程使用方式一：重写Thread类的run方法来执行任务
 * 
 * 2. 线程使用方式二：将任务放到Runnable实现类，再将该类对象传给Thread的构造函数
 * 
 * 3. 为什么不推荐使用方式二：因为应该将线程调度和要执行的任务解耦
 *
 * Chapter14/com.ling.learn1401.concurrent.ThreadAndRunnableTest.java
 *
 * author lingang
 *
 * createTime 2019-12-07 13:33:24
 *
 */
public class ThreadAndRunnableTest {
	public static void main(String[] args) {
		/* 1. 线程使用方式一：重写Thread类的run方法来执行任务 */
		Thread t0 = new Thread() {

			@Override
			public void run() {
				System.out.println("Thread t0 is running");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thread t0 is completed");
			}

		};
		t0.start();

		/* 2. 线程使用方式二：将任务放到Runnable实现类，再将该类对象传给Thread的构造函数 */
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				System.out.println("Thread t1 is running");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("Thread t1 is completed");
			}
		});
		t1.start();
	}
}
