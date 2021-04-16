package com.lg.test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.TreeMap;

/**
 * 遍历文件夹
 *
 * FileOperation/com.lg.test.WalkFileTreeTest.java
 *
 * author lingang
 *
 * createTime 2020-08-13 02:02:45 
 *
 */
public class WalkFileTreeTest {
	private static final String DIR_PATH = "C:/Users/lingang/Desktop/JMMMMM";
	private static TreeMap<String, String> map = new TreeMap<>();

	public static void main(String[] args) throws IOException {

//		if (true) {
//			String fileName = "添加TCP取样器操作.png";
//			System.out.println("![" + fileName.replace(".png", "") + "](../static/image/Jmeter/" + fileName + ")");
//			return;
//		}

		Files.walkFileTree(Paths.get(DIR_PATH), new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				BasicFileAttributes bfa = Files.readAttributes(file, BasicFileAttributes.class);
				map.put(bfa.creationTime().toString(), file.getFileName().toString());
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return FileVisitResult.SKIP_SUBTREE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.CONTINUE;
			}
		});

		for (String time : map.keySet()) {
			String fileName = map.get(time);
			System.out.println("![" + fileName.replace(".png", "") + "](../static/image/Jmeter/" + fileName + ")");
			System.out.println();
			System.out.println();
		}
	}
}
