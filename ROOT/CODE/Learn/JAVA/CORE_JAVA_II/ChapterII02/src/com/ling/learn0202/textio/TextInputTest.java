package com.ling.learn0202.textio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

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
	public static void main(String[] args) throws UnsupportedEncodingException, IOException {
		/**
		 * 1. Scanner类读取，可以按行或者按单词读取，也可按不同数据类型读取；可以从文件读取，也可以从其他流读取
		 */
		Scanner scanner = new Scanner(new File("D:/filetest/abc.txt"));
		System.out.println("**按行读取：");
		while (scanner.hasNextLine()) {
			System.out.println(scanner.nextLine());
		}
		System.out.println("**按单词读取(空格分隔)：");
		scanner = new Scanner(new File("D:/filetest/abc.txt"));
		while (scanner.hasNext()) {
			System.out.println(scanner.next());
		}

		/**
		 * 2. Files类读取文件中的文本
		 */
		// 小文件使用Files.readAllBytes方法读取全部字节，再转String
		String content = new String(Files.readAllBytes(Paths.get(URI.create("file:/D:/filetest/abc.txt"))), "UTF-8");
		System.out.println("**小文件使用Files.readAllBytes方法读取全部字节，再转String：");
		System.out.println(content);

		// Files.readAllLines方法读取文本文件的所有行
		List<String> lines = Files.readAllLines(Paths.get(URI.create("file:/D:/filetest/abc.txt")), Charset.defaultCharset());
		System.out.println("**读取文本文件的所有行：");
		System.out.println(lines);

		// 对于大文本文件，把所有行放到List会占用很多内存，Files.lines方法读取文本文件的所有行到stream，这样可以惰性处理
		Stream<String> lineStream = Files.lines(Paths.get(URI.create("file:/D:/filetest/abc.txt")));
		System.out.println("**读取文本文件的所有行到流中：");
		System.out.println(Arrays.toString(lineStream.toArray()));

		/**
		 * 3. java早期版本处理文本输入的唯一方式是使用BufferedReader类
		 */
		BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream("D:/filetest/abc.txt")));
		System.out.println("**java早期使用BufferedReader类读取文本：");
		String line;
		while ((line = br.readLine()) != null) {
			System.out.println(line);
		}
		br.close();

		// jdk1.8新增在BufferedReader类新增了lines方法来将文本读到流对象，方便惰性处理大文件
		br = new BufferedReader(new InputStreamReader(new FileInputStream("D:/filetest/abc.txt")));
		System.out.println("**BufferedReader类新增了lines方法来将文本读到流对象：");
		lineStream = br.lines();
		System.out.println(Arrays.toString(lineStream.toArray()));
	}
}
