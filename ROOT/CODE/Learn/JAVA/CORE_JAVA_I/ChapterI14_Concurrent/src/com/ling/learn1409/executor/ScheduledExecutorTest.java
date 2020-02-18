package com.ling.learn1409.executor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * 定时执行器
 *
 * Chapter14/com.ling.learn1409.executor.ScheduledExecutorTest.java
 *
 * author lingang
 *
 * createTime 2019-12-23 00:20:51
 *
 */
public class ScheduledExecutorTest {
	public static void main(String[] args) {
		/* 创建一个固定大小的定时执行线程池 */
		ScheduledExecutorService scheduledPool = Executors.newScheduledThreadPool(20);

		/* 在指定延迟后开始执行一个任务 */
		// scheduledPool.schedule(new Callable<Integer>() {
		//
		// @Override
		// public Integer call() throws Exception {
		// System.out.println("任务1：执行开始");
		// Thread.sleep(3000);
		// System.out.println("任务1：执行完毕");
		// return 1;
		// }
		// }, 1, TimeUnit.SECONDS);

		/*
		 * 在指定延迟后周期性的执行任务(不算任务执行时间)，假设一个周期的时间为T1，任务执行时间为T2，如果T1>=T2，那么执行周期为T1，
		 * 否则执行周期为T2
		 */
		scheduledPool.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				System.out.println("每2秒执行一次任务2，每次任务耗时1秒");
				try {
					// Thread.sleep(1000);
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 2, 2, TimeUnit.SECONDS);

		/*
		 * 在指定延迟后周期性的执行任务(需求加上任务执行时间)，假设一个周期的时间为T1，任务执行时间为T2，那么执行周期为T1+T2
		 */
		scheduledPool.scheduleWithFixedDelay(new Runnable() {

			@Override
			public void run() {
				System.out.println("每2秒执行一次任务3，每次任务耗时1秒");
				try {
					// Thread.sleep(1000);
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}, 2, 2, TimeUnit.SECONDS);

		/*
		 * 当调用了shutdown方法时，周期性的任务(
		 * 即scheduleAtFixedRate方法和scheduleWithFixedDelay方法启动的任务)不会被执行
		 */
		// scheduledPool.shutdown();

	}
}
