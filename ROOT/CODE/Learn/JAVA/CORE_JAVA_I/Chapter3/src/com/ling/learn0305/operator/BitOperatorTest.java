package com.ling.learn0305.operator;

/**
 * 位运算符
 *
 *1.正数右移，高位补0
 *
 *2.int类型数据移位数要模32，所以右移33位和右移1位是一样的(long型模64)
 *
 *3.负数右移，高位补1
 *
 *4.正数左移，低位补0
 *
 *5.负数左移，低位补0
 *
 *6.正数无符号右移，高位补0，结果和右移相同
 *
 *7.负数无符号右移，高位补0，结果和右移不同
 *
 *8.没有算术左移操作符
 *
 *
 * Chapter3/com.ling.learn0305.operator.BitOperatorTest.java
 *
 * author lingang
 *
 * createTime 2019-10-04 00:56:16 
 *
 */
public class BitOperatorTest {
public static void main(String[] args) {
	int positiveValue = 0b11111111;
	int negativeValue = -0b11111111;
	System.out.println(Integer.toBinaryString(positiveValue));//11111111，移位前
	System.out.println(Integer.toBinaryString(negativeValue));//11111111111111111111111100000001，移位前
	System.out.println("------------------------------------------");
	System.out.println(Integer.toBinaryString(positiveValue >> 1));//1111111，1.正数右移，高位补0
	System.out.println(Integer.toBinaryString(positiveValue >> 33));//1111111，2.int类型数据移位数要模32，所以右移33位和右移1位是一样的(long型模64)
	System.out.println(Integer.toBinaryString(negativeValue >> 1));//11111111111111111111111110000000，3.负数右移，高位补1
	System.out.println(Integer.toBinaryString(positiveValue << 1));//111111110，4.正数左移，低位补0
	System.out.println(Integer.toBinaryString(negativeValue << 1));//11111111111111111111111000000010，5.负数左移，低位补0
 	System.out.println(Integer.toBinaryString(positiveValue >>> 1));//1111111，6.正数无符号右移，高位补0，结果和右移相同
	System.out.println(Integer.toBinaryString(negativeValue >>> 1));//1111111111111111111111110000000，7.负数无符号右移，高位补0，结果和右移不同
// System.out.println(negativeValue <<< 1); // 8.没有算术左移操作符
}
}
