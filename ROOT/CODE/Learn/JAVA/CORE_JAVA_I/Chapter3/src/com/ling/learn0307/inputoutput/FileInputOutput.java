package com.ling.learn0307.inputoutput;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.Scanner;

/**
 * 文件输入输出
 *
 * Chapter3/com.ling.learn0307.inputoutput.FileInputOutput.java
 *
 * author lingang
 *
 * createTime 2019-10-08 00:58:48 
 *
 */
public class FileInputOutput {
public static void main(String[] args) throws IOException {
	/* 1. 读文件 */
	System.out.println(System.getProperty("user.dir")); // JVM启动路径，即java命令执行路径
	Scanner fileIn = new Scanner(Paths.get("./resource/myFile.txt"),"UTF-8");
	String fileContent = fileIn.hasNext() ? fileIn.nextLine() : "";
	System.out.println(fileContent);
	fileIn.close();
	
	/* 2. 写文件 */
	PrintWriter out = new PrintWriter("./resource/myFile.txt","UTF-8");
	out.append(fileContent); // 每次写文件都会对文件从头开始写，所以每次写之前先将文件原内容写在最前
	out.append("new content");
	out.close();
}
}
