package com.ling.learn0706.debugtips;

/**
 * 测试虚拟机参数-Xlint，使用了该参数，编译器会检查出代码问题并给出警告
 *
 * Chapter7/com.ling.learn0706.debugtips.XlintTest.java
 *
 * author lingang
 *
 * createTime 2019-11-11 20:41:53
 *
 */
public class XlintTest {
	public static void main(String[] args) throws InterruptedException {
		int i = 1;
		switch (i) {
		case 1:
			System.out.println(1);

		default:
			System.out.println("other");
		}
		Thread.sleep(111111);
	}
}
