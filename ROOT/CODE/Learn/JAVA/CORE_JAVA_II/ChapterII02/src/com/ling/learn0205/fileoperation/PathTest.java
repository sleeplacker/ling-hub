package com.ling.learn0205.fileoperation;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 路径-Path类
 * 
 * Path类创建的对象不一定要对应着一个真实的目录或文件，而只是一个抽象的名字序列
 *
 * ChapterII02/com.ling.learn0205.fileoperation.PathTest.java
 *
 * author lingang
 *
 * createTime 2020-01-31 00:21:04
 *
 */
public class PathTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// 1. 创建绝对路径
		System.out.println("创建绝对路径：");
		Path absolutePath = Paths.get("D:/", "filetest");
		System.out.println(absolutePath);
		absolutePath = Paths.get("D:/filetest/dir2/dir2_2/test2.txt");// 可以直接从一个字符串创建多层目录结构路径
		System.out.println(absolutePath);

		// 2. 创建相对路径
		System.out.println("创建相对路径：");
		Path relativePath = Paths.get("dir1", "dir1_1", "test.txt");
		System.out.println(relativePath);

		// 3. 路径的组合
		System.out.println("路径的组合：");
		Path resolve1 = relativePath.resolve(absolutePath);// 如果resolve的参数是绝对路径，则返回该绝对路径
		System.out.println(resolve1);
		Path resolve2 = absolutePath.resolve(relativePath);// 如果resolve的参数是相对路径，则返回两个路径拼接结果
		System.out.println(resolve2);
		Path resolve3 = absolutePath.resolveSibling(relativePath);// resolveSibling方法会先退一层再和参数路径拼接
		System.out.println(resolve3);

		// 4. 路径的相对化
		System.out.println("路径的相对化：");
		Path p1 = Paths.get("/aa/bb/cc/dd");
		Path p2 = Paths.get("/aa/bb");
		Path p3 = p1.relativize(p2);// 较深路径相对较浅路径返回回退层数
		System.out.println(p3);
		Path p4 = p2.relativize(p1);// 较浅路径相对较深路径返回多出的路径
		System.out.println(p4);

		// 5. 消除冗余的.和..
		System.out.println("消除冗余的.和..：");
		Path tooMuch = Paths.get("/aa/bb/../cc/./dd");
		System.out.println(tooMuch);
		Path normal = tooMuch.normalize();// 将..转换为向前跳一层目录，将.直接去掉
		System.out.println(normal);

		// 6. 相对路径转绝对路径
		System.out.println("相对路径转绝对路径：");
		System.out.println(relativePath.toAbsolutePath());

		// 7. Path类其他常用方法
		Path p5 = Paths.get("/aa/bb/cc/dd.txt");
		System.out.println(p5.getParent());// 获取上层目录
		System.out.println(p5.getRoot());// 获取根目录
		System.out.println(p5.getFileName());// 获取文件名-如果是文件而不是目录
		File file = p5.toFile();// Path转File
	}
}
