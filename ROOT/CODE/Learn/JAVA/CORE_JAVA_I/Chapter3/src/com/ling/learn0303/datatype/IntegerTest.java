package com.ling.learn0303.datatype;

/**
 * 整数类型
 * 
 * @author lingang
 *
 */
public class IntegerTest {
	public static void main(String[] args) {
		System.out.println("int : "+Integer.MIN_VALUE + " ~ " +Integer.MAX_VALUE); // int型数据取值范围 : -2147483648 ~ 2147483647
		System.out.println("short : "+Short.MIN_VALUE + " ~ " +Short.MAX_VALUE); // short型数据取值范围 : -32768 ~ 32767
		System.out.println("long : "+Long.MIN_VALUE + " ~ " +Long.MAX_VALUE); // long型数据取值范围: -9223372036854775808 ~ 9223372036854775807
		System.out.println("byte : "+Byte.MIN_VALUE + " ~ " +Byte.MAX_VALUE); // byte型数据取值范围: -128 ~ 127
		
		int hexValue = 0xFF; // 十六进制数值
		int octValue = 010; // 八进制数值 
		int binValue = 0b10; // 二进制数值
		int formatVaule = 1_23_456_7890; // 带下划线格式化的数值，java7后支持，编译器会自动去掉下划线
		System.out.println(hexValue);
		System.out.println(octValue);
		System.out.println(binValue);
		System.out.println(formatVaule);
	}
}
