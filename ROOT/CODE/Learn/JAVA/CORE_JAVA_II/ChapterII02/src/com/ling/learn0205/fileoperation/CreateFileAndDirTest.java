package com.ling.learn0205.fileoperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * 创建文件和目录
 *
 * ChapterII02/com.ling.learn0205.fileoperation.CreateFileAndDirTest.java
 *
 * author lingang
 *
 * createTime 2020-02-03 00:47:07
 *
 */
public class CreateFileAndDirTest {
	public static void main(String[] args) throws IOException {
		/* 创建目录 */
		// createDirectory方法只能创建一层目录，如果企图创建多层目录则抛错：java.nio.file.NoSuchFileException；
		// 且如果目录已存在，createDirectory方法会抛错：java.nio.file.FileAlreadyExistsException
		// Files.createDirectory(Paths.get("D:/filetest/dir"));
		// createDirectories可以创建多层目录，且目录存在时也不会抛错
		Files.createDirectories(Paths.get("D:/filetest/dir1/dir1_1"));

		/* 创建文件：如果文件已存在，则创建会报错 */
		Path newFilePath = Paths.get("D:/filetest/file1");
		synchronized (newFilePath) {// 判断文件是否存在和创建文件是原子性的
			if (!Files.exists(newFilePath))
				Files.createFile(newFilePath);
		}

	}
}
