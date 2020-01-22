package com.ling.learn0202.textio;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * 文本读入
 *
 * ChapterII02/com.ling.learn0202.textio.TextInputTest.java
 *
 * author lingang
 *
 * createTime 2020-01-22 11:33:19
 *
 */
public class TextInputTest {
	public static void main(String[] args) throws FileNotFoundException {
		/**
		 * 1. Scanner类读取，可以按行或者按单词读取，也可按不同数据类型读取
		 */
		Scanner scanner = new Scanner(new File("D:/abc.txt"));
		System.out.println("按行读取：");
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		System.out.println("按单词读取(空格分隔)：");
		scanner = new Scanner(new File("D:/abc.txt"));
		while (scanner.hasNext()) {
			System.out.println(scanner.next());
		}

	}
}
