package com.ling.learn0308.flowofcontrol;

/**
 * 标签的用法
 *
 *1. 标签可以跳出多重循环
 *
 *2. 没有循环的地方可以使用标签，可以让控制流跳出代码块
 *
 * Chapter3/com.ling.learn0308.flowofcontrol.LabelTest.java
 *
 * author lingang
 *
 * createTime 2019-10-09 00:08:11
 *
 */
public class LabelTest {
	public static void main(String[] args) {
		/* 1. 标签可以跳出多重循环 */
		int age = 0;
		Label1: while (true) {
			while (true) {
				++age;
				if (age >= 100) {
					break Label1;
				}
			}
		}
		System.out.println(age);
		
		/* 2. 没有循环的地方可以使用标签，可以让控制流跳出代码块 */
		Label2:
		{
			long curTime = System.currentTimeMillis();
			if(curTime % 2==0 ) {
				break Label2; // 跳出当前代码块
			}
			System.out.println("标签后面的操作"); // 执行程序时，有50%的几率会打印这句
		}
		
	}
}
