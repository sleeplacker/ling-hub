package com.lg.date202106_ThreadPoolExecutorTest;

/**
 * 工作类
 *
 * DailyTips/com.lg.date202106_ThreadPoolExecutorTest.RunAndLog2.java
 *
 * author ling
 *
 * createTime 2021-06-13 07:24:31
 *
 */
public class Work implements Runnable {
	private int cnt;

	public Work(int cnt) {
		this.cnt = cnt;
	}

	@Override
	public void run() {
		System.out.println("at thread [" + Thread.currentThread().getName() + "] work " + cnt + " start.");
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("at thread [" + Thread.currentThread().getName() + "] work " + cnt + " end.");
	}
}