package com.ling.learn1402.interrupt;

/**
 * 中断线程
 *
 * Chapter14/com.ling.learn1402.interrupt.InterruptTest.java
 *
 * author lingang
 *
 * createTime 2019-12-07 13:55:51
 *
 */
public class InterruptTest {
	public static void main(String[] args) {
		/*
		 * 1. 线程中断和sleep方法 1) 当线程处于中断状态，调用sleep方法，会清除中断状态并抛出InterruptedException
		 * 2) 当sleep方法在执行过程中，调用同一线程的interrupt方法，也会抛出InterruptedException
		 */
		Thread t0 = new Thread(new Runnable() {
			public void run() {
				System.out.println("Thread t0 is running");
				long start = System.currentTimeMillis();
				// 停顿3秒钟
				while (System.currentTimeMillis() - start < 3000) {
				}
				System.out.println(Thread.currentThread().isInterrupted());// true
																			// 线程已中断，如果注释掉上面几行停顿3秒的代码，则为false
																			// 线程未中断
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.out.println(Thread.currentThread().isInterrupted());
				}
				System.out.println("Thread t0 is completed");
			}
		});
		t0.start();
		long start = System.currentTimeMillis();
		// 停顿1秒钟
		while (System.currentTimeMillis() - start < 1000) {
		}
		t0.interrupt();

		/* 2. interrupted方法和isInterrupted方法 
		 * 		1)interrupted方法先检查中断状态，然后会清除中断状态
		 * 		2)isInterrupted方法只会检查中断状态，而不会清除中断状态
		 * */
		Thread t1 = new Thread(new Runnable() {
			public void run() {
				long start = System.currentTimeMillis();
				while (System.currentTimeMillis() - start < 1000) {
				}
				System.out.println("t1.isInterrupted : " + Thread.interrupted());// 检查中断状态，并清除中断状态(true-false)
			}
		});
		t1.start();
		t1.interrupt();
		System.out.println("t1.isInterrupted : " + t1.isInterrupted());// true
		System.out.println("t1.isInterrupted : " + t1.isInterrupted());// true isInterrupted方法只检查中断状态而不改变中断状态
		long start1 = System.currentTimeMillis();
		while (System.currentTimeMillis() - start1 < 2000) {
		}
		System.out.println("t1.isInterrupted : " + t1.isInterrupted());// false，因为被51行的interrupted方法清除了中断状态

	}
}
