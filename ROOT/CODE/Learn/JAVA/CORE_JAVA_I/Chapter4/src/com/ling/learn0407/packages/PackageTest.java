package com.ling.learn0407.packages;

import java.util.*;
import java.sql.*;
import static java.lang.Math.*; // 静态导入Math类中全部的静态域和静态方法
/**
 * 包
 * 
 * 1. import的两个包中包含相同类名，使用这个类时会有编译错误
 * 
 * 2. 静态导入语法为为import static 导入内容;静态导入后，可以直接使用导入的静态域或静态方法，前面无需加类名
 *
 * Chapter4/com.ling.learn0407.packages.PackageTest.java
 *
 * author lingang
 *
 * createTime 2019-10-14 13:21:02 
 *
 */
public class PackageTest {
	public static void main(String[] args) {
		// Date d = new Date(); // The type Date is
		// ambiguous，import的两个包中包含相同类名，使用这个类时会有编译错误

		System.out.println(E); // 直接使用已静态导入的静态域
		System.out.println(sqrt(2)); // 直接使用已静态导入的静态方法
	}
}
