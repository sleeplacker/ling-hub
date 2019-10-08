package com.ling.learn0308.flowofcontrol;

import java.util.Scanner;

/**
 * switch语句的使用(尽量避免使用)
 * 
 * 1. case后面不需要用{}包住也可以执行多条语句
 * 
 * 2. 通常每个case后面都要写break，否则执行完该分支还会执行下面的分支(包括default分支)，知道遇到break
 * 
 * 3. switch支持的参数类型：char，byte，short，int，enum，String
 *
 * Chapter3/com.ling.learn0308.flowofcontrol.SwitchTest.java
 *
 * author lingang
 *
 * createTime 2019-10-08 12:51:40
 *
 */
public class SwitchTest {
	enum NumStr {
		One, Two, Three, Four, Five
	}
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		String number = in.next(); // 输入1-5的数字
		in.close();
		switch (number) {
		case "1":
			System.out.println("一");
			System.out.println("壹"); // 1. case后面不需要用{}包住也可以执行多条语句
			// break; // 2. 通常每个case后面都要写break，否则执行完该分支还会执行下面的分支(包括default分支)，知道遇到break
		case "2":
			System.out.println("二");
			// break;
		case "3":
			System.out.println("三");
			break;
		case "4":
			System.out.println("四");
			break;
		case "5":
			System.out.println("五");
			break;
		default:
			System.out.println("其他");
			break;
		}

		/* 3. switch支持的参数类型：char，byte，short，int，enum，String */
		char ch = 'a';
		byte bt = 10; 
		short sh = 10;
		int it = 10;
		NumStr ns = NumStr.One;
		switch (ch) {
		}
		switch (bt) {
		}
		switch (sh) {
		}
		switch (it) {
		}
		switch (ns) {
		case One:
			System.out.println("一");
			break;
		case Two:
			System.out.println("二");
			 break;
		case Three:
			System.out.println("三");
			break;
		case Four:
			System.out.println("四");
			break;
		case Five:
			System.out.println("五");
			break;
		default:
			System.out.println("其他");
			break;
		}
	}
}

