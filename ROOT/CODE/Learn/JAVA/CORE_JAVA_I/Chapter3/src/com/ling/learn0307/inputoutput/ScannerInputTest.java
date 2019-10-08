package com.ling.learn0307.inputoutput;

import java.util.Scanner;

/**
 * 控制台输入
 *
 * Chapter3/com.ling.learn0307.inputoutput.ScannerInputTest.java
 *
 * author lingang
 *
 * createTime 2019-10-07 23:25:48
 *
 */
public class ScannerInputTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		System.out.println("输入姓名，回车结束：");
		String name = in.nextLine(); // 读取一行输入

		System.out.println("输入年龄：");
		Integer age = in.nextInt(); // 读取一个整数，空格和回车都可结束，输入非数字的内容会抛出异常

		System.out.println("姓名 ：" + name);
		System.out.println("年龄 ：" + age);

		System.out.println("输入多个字符串参数，空格隔开，回车结束输入：");
		String word = in.next();
		while (!word.equals(".")) {
			System.out.println(word); // 读取字符串，空格隔开，回车结束输入
			word = in.next();
		}
		in.close();
	}
}
