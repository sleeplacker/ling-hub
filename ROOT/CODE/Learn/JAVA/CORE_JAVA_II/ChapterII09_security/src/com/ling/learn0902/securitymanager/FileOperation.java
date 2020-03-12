package com.ling.learn0902.securitymanager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 文件操作
 *
 * ChapterII09_security/com.ling.learn0902.securitymanager.FileOperation.java
 *
 * author lingang
 *
 * createTime 2020-03-12 20:52:00
 *
 */
public class FileOperation {
	public static String readFile() throws IOException {
		return new String(Files.readAllBytes(Paths.get("src/com/ling/learn0902/securitymanager/TextFile.txt")));
	}

	public static void wirteFile(String content) throws IOException {
		Files.write(Paths.get("src/com/ling/learn0902/securitymanager/TextFile.txt"), content.getBytes(), StandardOpenOption.APPEND);
	}
}
