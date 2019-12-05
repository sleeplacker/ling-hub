package com.ling.learn1302.properties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * 属性和属性文件
 *
 * Chapter13/com.ling.learn1302.properties.PropertiesTest.java
 *
 * author lingang
 *
 * createTime 2019-12-05 23:03:24 
 *
 */
public class PropertiesTest {
public static void main(String[] args) throws FileNotFoundException, IOException {
	/*1. 属性的保存和读取*/
	Properties props = new Properties();
	props.setProperty("AA", "aa");
	props.setProperty("BB", "bb");
	props.setProperty("CC", "cc");
	props.store(new FileOutputStream("resource/msg/MyProps.properties"), "comments");// 将属性保存到文件
	Properties props2 = new Properties();
	props2.load(new FileInputStream("resource/msg/MyProps.properties"));//从文件读出属性
	System.out.println(props2);
	
	/*2. 设置默认属性值*/
	System.out.println(props2.getProperty("DD"));// 没有DD属性，返回null
	System.out.println(props2.getProperty("CC","defaultValue"));//使用默认值，但是有属性CC还是返回对应的cc
	System.out.println(props2.getProperty("DD","defaultValue"));//使用默认值，没有属性DD，会返回默认值defaultValue
	
	/*3. 二级属性映射*/
	Properties props3 = new Properties();
	props3.setProperty("CC", "ccccc");
	props3.setProperty("DD", "ddddd");
	props3.setProperty("EE", "eeeee");
	props3.store(new FileOutputStream("resource/msg/SecondLevelProps.properties"), "二级属性映射");
	
	Properties props0 = new Properties(props3);// 将二级属性映射传入一级属性映射
	props0.setProperty("AA", "aa");
	props0.setProperty("BB", "bb");
	props0.setProperty("CC", "cc");
	props0.store(new FileOutputStream("resource/msg/FirstLevelProps.properties"), "一级属性映射");
	
	System.out.println(props0.getProperty("AA"));//aa 二级属性映射中没有，一级属性映射中有，显示一级属性映射中的值
	System.out.println(props0.getProperty("CC"));//cc 二级属性映射中有，一级属性映射中也有，显示一级属性映射中的值
	System.out.println(props0.getProperty("EE"));//eeeee 二级属性映射中有，一级属性映射没有，显示二级属性映射中的值
	
}
}
