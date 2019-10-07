package com.ling.learn0306.string;

import java.util.Scanner;

/**
 * 控制台输入
 *
 * Chapter3/com.ling.learn0306.string.SystemInputTest.java
 *
 * author lingang
 *
 * createTime 2019-10-07 15:24:26 
 *
 */
public class ConsoleInputTest {
public static void main(String[] args) {
	Scanner in = new Scanner(System.in);
	System.out.println("输入姓名，回车结束：");
	String name = in.nextLine();
	
	System.out.println("输入年龄：");
	Integer age=  in.nextInt(); // 输入非数字的内容会抛出异常
	
	System.out.println("姓名 ："+name);
	System.out.println("年龄 ："+age);
	
	System.out.println("输入多个字符串参数，空格隔开，回车结束43：");
	while(true) {
		System.out.println(in.next());
	}
}
}
