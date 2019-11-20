package com.ling.learn0306.string;

/**
 * 字符串
 *
 * Chapter3/com.ling.learn0306.string.StringTest.java
 *
 * author lingang
 *
 * createTime 2019-10-04 13:32:43
 *
 */
public class StringTest {
	public static void main(String[] args) {

		/* 1.使用String的Join方法拼接字符串，并用分隔符隔开 */
		String strJoin = String.join("|", "How", "are", "you"); // 第一个参数为分隔符，后面的参数可以有1-n个，n>=1
		System.out.println(strJoin);

		/*
		 * 2.字符串中的代码单元和码点，每个Unicode字符对应1个码点， 但是一个码点可能对应两个代码单元，大多数码点只对应1个代码单元
		 */
		String sentence = "𝕆 is the set of octonions"; // 𝕆由两个代码单元组成：\ud835\udd46
		System.out.println(sentence);
		System.out.println(sentence.charAt(0));//? 双代码单元字符，不能这样去字符
		System.out.println(sentence.charAt(1));//? 双代码单元字符，不能这样去字符
		System.out.println(sentence.charAt(3));
		System.out.println(Integer.toHexString(sentence.codePointAt(0)));
	}
}
