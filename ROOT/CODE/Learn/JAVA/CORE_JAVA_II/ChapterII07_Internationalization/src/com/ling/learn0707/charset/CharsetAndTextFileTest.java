package com.ling.learn0707.charset;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 文本文件和字符集
 *
 * ChapterII07_Internationalization/com.ling.learn0707.charset.CharsetAndTextFileTest.java
 *
 * author lingang
 *
 * createTime 2020-02-28 01:32:46
 *
 */
public class CharsetAndTextFileTest {
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		/* 1. 文本文件 */
		// 要读写遗留的文件时，可以在读写器中指定编码方式，比如古老的Windows-1252编码格式
		try (PrintWriter pw = new PrintWriter("src/com/ling/learn0707/charset/testWindows1252.txt", "Windows-1252")) {
			pw.println("Hello");
			pw.println("你好");
		}
		// 获得平台最佳编码机制：貌似和preference->workspace设置的的编码一致，在cmd执行时是GBK
		Charset platformEncoding = Charset.defaultCharset();
		System.out.println(platformEncoding);

		/*
		 * 2.
		 * 行结束符，UNIX系统只需要使用\n就能换行，Windows希望使用\r\n来换行，但是大部分Windows应用程序也能识别\n作为换行符
		 * ，但是有个例外是记事本，它只能识别\r\n
		 */
		try (PrintWriter pw = new PrintWriter("src/com/ling/learn0707/charset/endLineTest.txt", "UTF-8")) {
			pw.println("Hello");
			pw.println("Hi\nByeBye");
			pw.println("OK\r\nSeeYou");
			// 这里生成的文件，在eclipse和notepad++中打开是5行字，但是在记事本中打开是4行字，因为它只识别了\r\n换行符，而不能识别\n
		}
		try (PrintWriter pw = new PrintWriter("src/com/ling/learn0707/charset/endLineTest2.txt", "UTF-8")) {
			pw.printf("Hello%nWorld%n");
			// 不管是在Windows还是其他平台生成这个文件，在eclipse，notepad++和记事本中打开都是两行，因为%n会根据不同平台使用不同的行结束符号，在Windows中是\r\n，而在其他所有平台是\n
		}
	}
}
