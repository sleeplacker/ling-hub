package com.lg.test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 批量修改文件hsah值，方法是在文件尾加一个字节
 *
 * FileOperation/com.lg.test.ChangeHash.java
 *
 * author lingang
 *
 * createTime 2020-10-25 05:14:43
 *
 */
public class ChangeHash {
	private static final String DIR_PATH = "M:/ssoo";

	public static void main(String[] args) throws Exception {

		Files.walkFileTree(Paths.get(DIR_PATH), new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				Long start = System.currentTimeMillis();
				Files.write(file, "A".getBytes(), StandardOpenOption.APPEND);// 指定为追加操作会在文件尾追加内容
				System.out.println(file.toString() + " DONE in " + (System.currentTimeMillis() - start) + "ms");
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return FileVisitResult.SKIP_SUBTREE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}
		});

	}
}
