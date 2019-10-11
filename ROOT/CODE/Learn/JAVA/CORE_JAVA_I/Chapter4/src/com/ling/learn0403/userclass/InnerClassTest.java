package com.ling.learn0403.userclass;

/**
 * 同一个java文件放了两个类
 * 
 * 1. 如果第二个类为内部类，会多编译一个名为InnerClassTest$InnerClass.class的class文件
 * 
 * 2. 如果第二个类与第一个类没有包含关系，则会多编译一个名为SecondClassInSameFile.class的class文件
 *
 * Chapter4/com.ling.learn0402.userclass.InnerClassTest.java
 *
 * author lingang
 *
 * createTime 2019-10-11 23:24:41 
 *
 */
public class InnerClassTest {
	class InnerClass {
		
	}
}

class SecondClassInSameFile {
	
}
