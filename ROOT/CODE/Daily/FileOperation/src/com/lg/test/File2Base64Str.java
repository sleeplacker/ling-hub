package com.lg.test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Base64;

/**
 * 文件转base64字符串
 *
 * FileOperation/com.lg.test.File2Base64Str.java
 *
 * author lingang
 *
 * createTime 2020-08-06 00:09:34
 *
 */
public class File2Base64Str {
	public static void main(String[] args) throws IOException {
		Files.walkFileTree(Paths.get("file2base64"), new FileVisitor<Path>() {

			// 在一个目录处理前执行的操作
			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				// 处理目录前打印目录，并继续处理
				return FileVisitResult.CONTINUE;
			}

			// 遇到一个文件或目录时执行的操作
			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				// TODO Auto-generated method stub
				System.out.println("FileName: " + file);
				System.out.println(Base64.getEncoder().encodeToString(Files.readAllBytes(file)));
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

	}
}
