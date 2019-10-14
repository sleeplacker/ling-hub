package com.ling.learn0408.searchclass;

/**
 * 查找类
 * 
 * 1. 虚拟机查找类：
 * 
 * ①首先查看存储在jre/lib和jre/lib/ext目录下的归档文件(jar文件)中存放的系统类文件
 * 
 * ②查找类路径中的class文件，包括指定存放class文件的目录(如：D:/classdir)，当前目录(.)，存放jar文件路径(D:/jarfile)
 * 依次查看并运行runClassInClassDir.bat，runClassInJar.bat，runClassSetMultipleClasspath.bat，runClassWithoutClasspath.bat
 * 
 * 2. 编译器(javac)查找类：
 * 
 * ①查找所有的import指令(包括默认的import java.lang.*)，确定其中是否包含被引用的类，如果找到一个以上的类，会产生编译错误
 * 
 * ②还要查看源文件，如果源文件比类文件新，则重新编译源文件，如果是导入当前包中的非公有类，
 * 需要查看当前包的所有源文件，因为非公有类定义在与类名不用的源文件中
 *
 * Chapter4/com.ling.learn0408.searchclass.SearchClassTest.java
 *
 * author lingang
 *
 * createTime 2019-10-14 23:24:42
 *
 */
public class SearchClassTest {
	public static void main(String[] args) throws InterruptedException {
		System.out.println("找到了！两秒后再见");
		Thread.sleep(2000);
	}
}
