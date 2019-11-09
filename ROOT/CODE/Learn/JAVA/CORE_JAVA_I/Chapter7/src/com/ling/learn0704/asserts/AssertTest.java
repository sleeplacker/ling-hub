package com.ling.learn0704.asserts;

/**
 * 断言
 * 
 * 1. 开启断言的方式：java -enableassertions 要运行的class名， 或者 java -ea:要开启断言的类名
 * 要运行的class名， 或者 java -ea:要开启断言的包名 要运行的class名。 当开启包名断言时，整个包和子包的断言都会被打开，
 * 如果-ea后面不跟:类名或者包名，则表示开启默认包的断言。 禁用断言使用-disableassertions或-da虚拟机参数。
 *
 * Chapter7/com.ling.learn0704.asserts.AssertTest.java
 *
 * author lingang
 *
 * createTime 2019-11-08 16:47:04
 *
 */
public class AssertTest {
	public static void main(String[] args) {
		double x = -1;
		// assert x >= 0;// 普通断言
		/* 断言失败会抛出java.lang.AssertionError异常 */
		// assert x >= 0 : x;// 带表达式的断言
		double y = Math.sqrt(x);
		System.out.println(y);

		/* 使用断言代替注释 */
		int iVal = 2;
		if (iVal % 3 == 0) {
			// ...
		} else if (iVal % 3 == 1) {
			// ...
		} else {// iVal%3==2
			assert iVal % 3 == 2 : iVal;// 使用断言代替上一行的注释
		}
	}

}
