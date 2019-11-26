package com.ling.learn0905.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 集合与数组的转换
 *
 * Chapter09/com.ling.learn0905.algorithm.CollectionToArray.java
 *
 * author lingang
 *
 * createTime 2019-11-26 23:40:25 
 *
 */
public class CollectionToArray {
public static void main(String[] args) {
	/*1. 数组转集合*/
	String[] strArr = {"A","B","C"};
	List<String> list = new ArrayList<String>(Arrays.asList(strArr));
	
	/*2. 集合转数组*/
//	String[] arr = (String[]) list.toArray();//虽然编译通过，但是运行报错：java.lang.ClassCastException
	String[] arr = list.toArray(new String[0]);
	String[] newArr = list.toArray(new String[5]);// 参数中的数组长度比列表长，会创建新数组并返回
	System.out.println(Arrays.toString(arr));
	System.out.println(Arrays.toString(newArr));
}
}
