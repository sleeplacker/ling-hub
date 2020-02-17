package com.lg.test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * Copy all that movies to a directory and rename
 *
 * FileOperation/com.lg.test.MoviesMakeup.java
 *
 * author lingang
 *
 * createTime 2020-02-17 22:33:51
 *
 */
public class MoviesMakeup {
	public static Pattern PATTERN = Pattern
			.compile(".*?([A-Z]{3,4}|[0-9]{6})[-]?([S]?[0-9]{3}).*(\\.AVI|\\.MP4|\\.MKV|\\.WMV|\\.RMVB|\\.RM)(.*)");
	public static String SRC_DIR = "K:\\电影";// 原文件夹
	public static String DEST_DIR = "K:\\SSSSSSSSSSSSSOO";// 目的文件夹
	public static int NEET_SIZE = 200 * 1024 * 1024;// 200M

	public static void main(String[] args) throws IOException {
		Path srcDir = Paths.get(SRC_DIR);
		Path destDir = Paths.get(DEST_DIR);
		if (!Files.exists(destDir))
			Files.createDirectories(destDir);
		try (Stream<Path> files = Files.walk(srcDir)) {
			files.forEach(file -> {
				try {
					String fileName = file.getFileName().toString();
					if (!Files.isRegularFile(file))// 过滤掉目录
						return;
					long fileSize = Files.size(file);
					if (Files.size(file) < NEET_SIZE)// 过滤掉较小文件
						return;
					fileName = fileName.toUpperCase();
					Matcher matcher = PATTERN.matcher(fileName);
					if (matcher.matches()) {// 检查是否匹配
						String newFileName = matcher.group(1) + "-" + matcher.group(2) + matcher.group(3).toLowerCase()
								+ matcher.group(4).toLowerCase();// 组装新文件名
						System.out.println(newFileName + ": "
								+ String.format("%.2f", 1.0 * fileSize / (1024 * 1024 * 1024)) + "GB");

						Files.move(file, destDir.resolve(newFileName), StandardCopyOption.ATOMIC_MOVE);// 移动文件
					} else {
						System.err.println("Format_error：" + file.getFileName());// 文件够大，但是文件名不匹配正则表达式
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
