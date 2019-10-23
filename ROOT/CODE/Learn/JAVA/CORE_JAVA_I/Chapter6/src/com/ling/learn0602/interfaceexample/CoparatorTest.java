package com.ling.learn0602.interfaceexample;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 自定义比较器
 *
 * Chapter6/com.ling.learn0602.interfaceexample.CoparatorTest.java
 *
 * author lingang
 *
 * createTime 2019-10-24 01:00:08
 *
 */
public class CoparatorTest {
	public static void main(String[] args) {
		String[] strs = { "BB", "CCC", "AAA", "DDDD" };
		System.out.println(Arrays.toString(strs));
		Arrays.sort(strs);// 按照String中的compareTo方法进行比较(按字典顺序排序)
		System.out.println(Arrays.toString(strs));
		Comparator<String> lengthComp = new Comparator<String>() { // 自定义比较器，按字符串长度排序

			@Override
			public int compare(String o1, String o2) {
				return o1.length() - o2.length();
			}
		};
		Arrays.sort(strs, lengthComp);
		System.out.println(Arrays.toString(strs));
	}

}
