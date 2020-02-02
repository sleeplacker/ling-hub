package com.ling.learn0205.fileoperation;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;
import java.util.List;

/**
 * Files读写文件
 *
 * ChapterII02/com.ling.learn0205.fileoperation.FileReadAndWriteTest.java
 *
 * author lingang
 *
 * createTime 2020-02-03 00:23:51
 *
 */
public class FileReadAndWriteTest {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("D:/filetest/filerw.txt");
		// 写文件
		Files.write(path, "aaa好好好\n".getBytes("UTF-8"));
		Files.write(path, "aaa好好好\n".getBytes("UTF-8"));// 不指定操作会覆盖文件原内容
		Files.write(path, "aaa好好好\n".getBytes("UTF-8"), StandardOpenOption.APPEND);// 指定为追加操作会在文件尾追加内容
		Files.write(path, Arrays.asList("bbb对对对", "ccc是是是"), StandardOpenOption.APPEND);// 可以将一个行列表追加到文件尾

		// 读文件
		byte[] bytes = Files.readAllBytes(path);// 读字节
		System.out.println(new String(bytes, "UTF-8"));
		List<String> lines = Files.readAllLines(path);// 将所有行读入list
		System.out.println(lines);

		// 操作大文件时，还是应该通过获取输入/输出流来操作
		InputStream is = Files.newInputStream(path);// 获得输入流
		OutputStream os = Files.newOutputStream(path);// 获得输出流
		Reader reader = Files.newBufferedReader(path);// 获得读入缓冲器
		Writer writer = Files.newBufferedWriter(path);// 获得写出缓冲器
		is.close();
		os.close();
		reader.close();
		writer.close();
	}
}
