package com.ling.learn1405.synchronize;

/**
 * sun-jdk8 win7操作系統，测试不出不用volatile有什么问题
 *
 * Chapter14/com.ling.learn1405.synchronize.TestWithoutVolatile.java
 *
 * author lingang
 *
 * createTime 2019-12-16 00:34:40 
 *
 */
public class TestWithoutVolatile {
	private static boolean bChanged;
	// private static volatile boolean bChanged;

	public static void main(String[] args) throws InterruptedException {
		new Thread() {

			@Override
			public void run() {
				for (;;) {
					if (bChanged == !bChanged) {
						System.out.println("!=");
						System.exit(0);
					}
				}
			}
		}.start();
		Thread.sleep(1);
		new Thread() {

			@Override
			public void run() {
				for (;;) {
					bChanged = !bChanged;
				}
			}
		}.start();
	}

}