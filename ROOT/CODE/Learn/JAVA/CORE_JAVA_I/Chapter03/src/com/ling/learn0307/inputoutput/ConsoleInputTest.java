package com.ling.learn0307.inputoutput;

import java.io.Console;

/**
 * 控制台输入-支持密码输入不可见(但是要在命令行运行该类)
 *
 * Chapter3/com.ling.learn0307.inputoutput.ConsoleInputTest.java
 *
 * author lingang
 *
 * createTime 2019-10-07 23:26:09 
 *
 */
public class ConsoleInputTest {
public static void main(String[] args) {
	Console console = System.console();
	String name = console.readLine("input name : ");
	char[] password = console.readPassword("input password : ");
	System.out.println("name : "+name);
	System.out.println("password : "+new String(password));
}
}
