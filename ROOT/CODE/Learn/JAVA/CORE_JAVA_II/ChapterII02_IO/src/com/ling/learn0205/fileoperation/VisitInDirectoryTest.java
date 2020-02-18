package com.ling.learn0205.fileoperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 访问目录中的项
 *
 * ChapterII02/com.ling.learn0205.fileoperation.VisitInDirectoryTest.java
 *
 * author lingang
 *
 * createTime 2020-02-04 00:28:53
 *
 */
public class VisitInDirectoryTest {
	public static void main(String[] args) throws IOException {
		// 1. 展示目录中的所有项，但是不进入子目录
		try (Stream<Path> entries = Files.list(Paths.get("D:/filetest"))) {
			System.out.println(entries.map(p -> p.toString() + "\n").collect(Collectors.toList()));
		}

		// 2_1. 展示目录中的所有项，以及递归展示子目录中的项
		try (Stream<Path> entries = Files.walk(Paths.get("D:/filetest"))) {
			System.out.println(entries.map(p -> p.toString() + "\n").collect(Collectors.toList()));
		}
		// 2_2. 展示目录中的所有项，以及递归展示子目录中的项，指定访问深度为1，等同于Files.list方法
		try (Stream<Path> entries = Files.walk(Paths.get("D:/filetest"), 1)) {
			System.out.println(entries.map(p -> p.toString() + "\n").collect(Collectors.toList()));
		}

		// 3. 展示目录中的所有项，并进行过滤，同时也能指定深度
		try (Stream<Path> entries = Files.find(Paths.get("D:/filetest"), 2, (p, a) -> !a.isDirectory())) {// 过滤掉目录
			System.out.println(entries.map(p -> p.toString() + "\n").collect(Collectors.toList()));
		}
	}
}
