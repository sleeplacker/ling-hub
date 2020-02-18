package com.ling.learn0205.fileoperation;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * 使用目录流
 *
 * ChapterII02/com.ling.learn0205.fileoperation.DirectoryStreamTest.java
 *
 * author lingang
 *
 * createTime 2020-02-04 00:48:15
 *
 */
public class DirectoryStreamTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		// 1_1. 使用DirectoryStream遍历目录，但不能遍历子目录
		System.out.println("遍历当前目录，不包含子目录：");
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(Paths.get("D:/filetest"))) {
			for (Path p : entries) {
				System.out.println(p);
			}
		}
		// 1_2. 使用DirectoryStream遍历目录，但不能遍历子目录
		System.out.println("遍历当前目录下的.txt文件，不包含子目录：");
		try (DirectoryStream<Path> entries = Files.newDirectoryStream(Paths.get("D:/filetest"), "*.txt")) {
			for (Path p : entries) {
				System.out.println(p);
			}
		}

		// 2_1. 使用Files.walkFileTree方法灵活遍历目录
		System.out.println("使用Files.walkFileTree方法灵活遍历目录：");
		Files.walkFileTree(Paths.get("D:/filetest"), new FileVisitor<Path>() {

			// 在一个目录处理前执行的操作
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				// 处理目录前打印目录，并继续处理
				System.out.println(dir);
				return FileVisitResult.CONTINUE;
			}

			// 遇到一个文件或目录时执行的操作
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				System.out.println("\t" + file);
				return FileVisitResult.CONTINUE;
			}

			// 在试图访问一个文件或目录发生错误时执行的操作
			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.SKIP_SUBTREE;// 访问失败时跳过该项
			}

			// 在一个目录被处理后执行的操作
			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.CONTINUE;// 处理目录后继续
			}
		});
		// 2_2. 使用Files.walkFileTree方法删除目录树
		System.out.println("使用Files.walkFileTree方法删除目录树：");

		// 先创建目录
		Files.createDirectories(Paths.get("D:/filetest/dir_delete_test/dir1/dir1_1"));
		Files.createFile(Paths.get("D:/filetest/dir_delete_test/dir1/dir1_1/filed11"));
		Files.createDirectories(Paths.get("D:/filetest/dir_delete_test/dir2/dir2_1/dir2_1_1"));
		Files.createFile(Paths.get("D:/filetest/dir_delete_test/dir2/dir2_1/dir2_1_1/filed211"));

		Thread.sleep(3000);// 睡3秒，看删除过程

		// 直接删除目录树会报：java.nio.file.DirectoryNotEmptyException
		// Files.delete(Paths.get("D:/filetest/dir_delete_test"));

		Files.walkFileTree(Paths.get("D:/filetest/dir_delete_test"), new FileVisitor<Path>() {

			// 在一个目录处理前执行的操作
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.CONTINUE;
			}

			// 遇到一个文件或目录时执行的操作
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				Files.delete(file);
				return FileVisitResult.CONTINUE;
			}

			// 在试图访问一个文件或目录发生错误时执行的操作
			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				// TODO Auto-generated method stub
				return FileVisitResult.SKIP_SUBTREE;// 访问失败时跳过该项
			}

			// 在一个目录被处理后执行的操作
			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				// TODO Auto-generated method stub
				// 处理目录后删除目录并继续
				Files.delete(dir);
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
