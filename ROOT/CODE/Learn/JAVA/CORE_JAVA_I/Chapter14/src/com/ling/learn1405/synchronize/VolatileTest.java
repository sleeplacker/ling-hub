package com.ling.learn1405.synchronize;

/**
 * volatile(不稳定的)关键字
 * 
 * 学习地方：https://www.ibm.com/developerworks/cn/java/j-jtp06197.html
 *
 * Chapter14/com.ling.learn1405.synchronize.VolatileTest.java
 *
 * author lingang
 *
 * createTime 2019-12-12 22:56:44
 *
 */
public class VolatileTest {
	public static void main(String[] args) {
		Computer bean = new Computer();
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					bean.working();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();
		new Thread(new Runnable() {
			@Override
			public void run() {
				bean.shutdown();
				System.out.println("shutdown time: " + System.currentTimeMillis());
			}
		}).start();
	}
}

class Computer {
	private boolean shutdownFlag;

	public void shutdown() {
		this.shutdownFlag = !this.shutdownFlag;
	}

	public void working() throws InterruptedException {
		while (shutdownFlag == !shutdownFlag) {
			// System.out.println("working . . ."+);
			// Thread.sleep(2000);
		}
		System.out.println("work end time: " + System.currentTimeMillis());
	}
}