package com.ling.learn1404.property;

/**
 * 线程优先级
 * 
 * 线程优先级会被映射到操作系统的线程优先级，所以在不同的操作系统上，线程优先级的表现不同，比如Windows和linux对线程优先级处理就不同
 * 
 * 线程优先级属性不应该被滥用，应该在不得不使用且很熟悉优先级机制的情况下才能使用
 *
 * Chapter14/com.ling.learn1404.property.PriorityTest.java
 *
 * author lingang
 *
 * createTime 2019-12-09 21:35:08
 *
 */
public class PriorityTest {
	public static void main(String[] args) {
		Thread t = new Thread();
		/* 为线程设置优先级，数字越大优先级越高 */
		t.setPriority(1);// 1-10
		t.setPriority(Thread.MIN_PRIORITY);// 1
		t.setPriority(Thread.NORM_PRIORITY);// 5
		t.setPriority(Thread.MAX_PRIORITY);// 10

		/* 让当前线程处于让步状态，会让其他优先级与自己相同的线程先被调度 */
		Thread.yield();

	}
}
