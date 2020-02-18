package com.ling.learn1404.property;

/**
 * 守护线程
 * 
 * 守护线程用于为其他线程提供服务，当只剩下守护线程时，虚拟机就会退出
 * 
 * 不应该在守护线程中访问固有资源，如文件，数据库，因为守护线程可能在任何时候被中断
 *
 * Chapter14/com.ling.learn1404.property.DaemonThreadTest.java
 *
 * author lingang
 *
 * createTime 2019-12-09 22:12:37
 *
 */
public class DaemonThreadTest {
	public static void main(String[] args) {
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("daemon thread begin...");
				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("daemon thread end");
			}
		});

		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				System.out.println("user thread begin...");
				try {
					Thread.sleep(2000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("user thread end");
			}
		});
		t1.setDaemon(true); // 将t1设置为守护线程
		/* 同时启动t1和t2 */
		t1.start();
		t2.start();
		// 打印结果-说明用户线程执行完成后，虚拟机就结束运行了
		// daemon thread begin...
		// user thread begin...
		// user thread end
	}
}
