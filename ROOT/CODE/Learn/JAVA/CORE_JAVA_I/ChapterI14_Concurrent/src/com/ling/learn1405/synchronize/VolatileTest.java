package com.ling.learn1405.synchronize;

/**
 * volatile(不稳定的)关键字
 * 
 * 学习地方：https://www.ibm.com/developerworks/cn/java/j-jtp06197.html
 * 
 * volatile关键字作用：
 * 
 * 1. 保证变量可见性，让程序读变量总是从物理内存读取，而不是从线程的工作内存读取
 * 
 * 2. 禁止指令重排序，有些java代码看起来是一个操作，
 * 但是实际上包含多条虚拟机指令，jvm在不改变语义的情况下可能会对这些数值指令编译器进行克隆重拍下来，单线程情况小不会有问题吧，但多线操作情况可能会有问题，使用volatile关键字修饰变量能禁止指令重排序
 *
 * 然而：在PC机上，多线程情况下使用volatile和不使用volatile感觉不出任何区别，可能跟jvm配置有关，或许volatile确实只有在嵌入式系统上才比较常用
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
		/*volatile关键字保证内存可见性：遗憾：测试不出不用volatile有什么问题*/
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

		/* volatile关键字禁止指令重排序-遗憾：测试不出不用volatile有什么问题 */
		for (int i = 0; i < 100; ++i) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					BeanFactory.set();
				}
			}).start();
		}
		for (int i = 0; i < 100; ++i) {
			new Thread(new Runnable() {

				@Override
				public void run() {
					System.out.println(BeanFactory.getInstance());
				}
			}).start();
		}

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

class Bean {
	String name;

	/**
	 * @param name
	 */
	public Bean(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Bean [name=" + name + "]";
	}

}

class BeanFactory {
	private static Bean bean = new Bean("aa");

	public static void set() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		bean = new Bean("aa");
	}

	public static Bean getInstance() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// if (bean != null)
		return bean;
		// else
		// return new Bean("aa");
	}

}