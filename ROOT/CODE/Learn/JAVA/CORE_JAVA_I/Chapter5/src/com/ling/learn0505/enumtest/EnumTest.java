package com.ling.learn0505.enumtest;

import java.util.Arrays;

/**
 * 枚举类型
 *
 * Chapter5/com.ling.learn0505.enumtest.EnumTest.java
 *
 * author lingang
 *
 * createTime 2019-10-18 01:20:32
 *
 */
public class EnumTest {
	public static void main(String[] args) {
		Size S = Size.SMALL;
		Size L = Size.LARGE;
		System.out.println(S);// 返回S.toString，即字面值SMALL
		System.out.println(L.ordinal());// LARGE在Size中定义的位置
		System.out.println(Size.valueOf("LARGE") == L);// true，可以直接用==比较枚举类型是否相等
		System.out.println(S.compareTo(S));// 0
		System.out.println(S.compareTo(L));// -2
		System.out.println(L.compareTo(S));// 2
		System.out.println(Size.values());// values方法返回数组
		System.out.println(Arrays.toString(Size.values()));// 使用Arrays.toString和Arrays.asList方法都可以遍历打印数组
		System.out.println(Arrays.asList(Size.values()));// 使用Arrays.toString和Arrays.asList方法都可以遍历打印数组
		System.out.println(L instanceof Object); // true，枚举类型也是对象
		System.out.println(S.getFieldValue()); // s 枚举对象可以访问枚举类型中的方法
		Enum<Size> M = Size.MEDIUM;// 所以枚举类型都是Enum的子类
		System.out.println(((Size) M).getFieldValue());// m
	}
}

enum Size {
	SMALL("s"), MEDIUM("m"), LARGE("l");

	private String fieldValue; // 枚举类型中也可以定义域和方法

	/**
	 * @param fieldValue
	 */
	private Size(String fieldValue) {
		this.fieldValue = fieldValue;
	}

	public String getFieldValue() {
		return fieldValue;
	}

	public void setFieldValue(String fieldValue) {
		this.fieldValue = fieldValue;
	}

}
