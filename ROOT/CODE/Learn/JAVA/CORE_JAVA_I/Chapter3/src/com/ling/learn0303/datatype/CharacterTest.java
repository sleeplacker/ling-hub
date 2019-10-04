package com.ling.learn0303.datatype;

/**
 * Character类型数据
 *
 *1.java中的char类型长度为两个字节(16位二进制数)，所以解释了为什么char类型是UTF-16字符
 *
 * Chapter3/com.ling.learn0303.datatype.CharacterTest.java
 *
 * author lingang
 *
 * createTime 2019-10-03 22:44:45 
 *
 */
public class CharacterTest {
	public static void main(String\u005B\u005D args) { // "\u005B\u005D"在这里直接等同于"[]"，而不是字符串
		// \u00A0 is a newline
		char normalValue = 'A';
		char utf16Value = '\u00A0'; // 换行符
		System.out.println(normalValue);
		System.out.println(utf16Value);
		// D:\\users 如果把这里的两个反斜杠换成一个就会有语法错误，因为"反斜杠u"后面必须为4位16进制数字 
		System.out.println(Integer.toBinaryString(Character.MAX_VALUE)); // 1.java中的char类型长度为两个字节(16位二进制数)，所以解释了为什么char类型是UTF-16字符
	}
}
