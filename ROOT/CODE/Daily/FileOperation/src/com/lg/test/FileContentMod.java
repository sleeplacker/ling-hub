package com.lg.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileContentMod {
	public static void main(String[] args) throws IOException {
		Files.lines(Paths.get("E:/books/C程序设计语言/C程序设计语言(第2版_新版)/uft.txt")).forEach(line -> {
			System.out.print(line.substring(0, line.length()-3));
			System.out.println(Integer.parseInt(line.substring(line.length()-3,line.length()))-1);
		});
	}
}
