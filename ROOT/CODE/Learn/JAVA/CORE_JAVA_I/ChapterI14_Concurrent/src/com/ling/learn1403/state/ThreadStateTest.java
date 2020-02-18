package com.ling.learn1403.state;

/**
 * 线程状态
 * 
 * 1. New(新创建) ： new Thread()后为新创建状态
 * 
 * 2. Runnable(可运行)：
 * 调用start()方法后线程为可运行状态，叫可运行而不叫运行的原因是因为操作系统时间片分配的原因，线程可能正在运行也可能在等待时间片的分配
 * 
 * 3. Blocked(被阻塞)：
 * 当线程视图获得一个锁，而这个锁被其他线程占用时，会被阻塞
 * 
 * 4. Waiting(等待)
 * 当线程在等待一个条件时进入等待状态，需要等待其他线程对它进行通知才能解除等待状态，得到激活并继续执行
 * 
 * 5. Timed waiting(计时等待)
 * 当调用一些带超时参数的方法时，会进入该状态，在超时期满或得到其他线程通知时会解除该状态，可以将等待状态看成是超时时间无穷大的计时等待状态
 * 
 * 6. Terminated(被终止)
 * 当run方法执行完或者执行过程中出现受查异常而未进行捕获会进入被终止状态
 * stop方法也可以是线程被终止，但是该方法已被弃用
 *
 * Chapter14/com.ling.learn1403.state.ThreadStateTest.java
 *
 * author lingang
 *
 * createTime 2019-12-07 16:20:00
 *
 */
public class ThreadStateTest {
	public static void main(String[] args) {
		Thread t = new Thread();
		System.out.println(t.getState()); // new Thread()后线程为新创建状态
		t.start();
		System.out.println(t.getState()); // 调用start()方法后线程处于可运行状态

	}
}
