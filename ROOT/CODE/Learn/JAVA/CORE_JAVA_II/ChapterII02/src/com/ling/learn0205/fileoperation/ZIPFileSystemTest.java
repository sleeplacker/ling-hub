package com.ling.learn0205.fileoperation;

import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * ZIP文件系统
 *
 * ChapterII02/com.ling.learn0205.fileoperation.ZIPFileSystemTest.java
 *
 * author lingang
 *
 * createTime 2020-02-04 01:48:35
 *
 */
public class ZIPFileSystemTest {
	public static void main(String[] args) throws IOException {
		FileSystem fs = FileSystems.newFileSystem(Paths.get("D:/filetest/ziptest.zip"), null);

		// 遍历ZIP文件和目录
		Files.walkFileTree(fs.getPath("/ziptest/"), new SimpleFileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				System.out.println(dir);
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				System.out.println(file);
				return FileVisitResult.CONTINUE;
			}

		});

		// 从压缩文件中复制出文件
		Files.copy(fs.getPath("/ziptest/subdir/file2.txt"), Paths.get("D:/filetest/copy_from_zip"));
	}
}
