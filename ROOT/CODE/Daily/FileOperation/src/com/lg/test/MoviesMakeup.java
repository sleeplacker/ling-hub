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
	/*
	 * ?!\\d表示后面不能出现数字
	 */
	public static Pattern PATTERN = Pattern
			.compile(".*?([A-Z]{3,4}|\\d{6})[-]?([S]?\\d{3}(?!\\d)).*(\\.AVI|\\.MP4|\\.MKV|\\.WMV|\\.RMVB|\\.RM)(.*)");
	public static String SRC_DIR = "M:\\SrcDir";// TODO 源文件夹
	public static String DEST_DIR = "M:\\DestDir";// TODO 目标文件夹
	public static String NOT_MATCH = "M:\\NotMatchDir";// TODO 不匹配的文件移动到该文件夹
	public static int NEET_SIZE = 200 * 1024 * 1024;// 文件不小于200M

	public static void main(String[] args) throws IOException {
		Path srcDir = Paths.get(SRC_DIR);
		Path destDir = Paths.get(DEST_DIR);
		Path notMatchDir = Paths.get(NOT_MATCH);
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
						String newFileName = matcher.group(1) + "-" + matcher.group(2) + ".mp4"
								+ matcher.group(4).toLowerCase();// 组装新文件名，并将后缀名统一改为.mp4
						System.out.println(
								newFileName + ": " + String.format("%.2f", 1.0 * fileSize / (1024 * 1024 * 1024)) + "GB"
										+ " From " + file.getFileName());
						Path destFile = destDir.resolve(newFileName);
						while (Files.exists(destFile)) {// 如果文件重复，则修改文件名
							System.err.println("Repeat file : " + newFileName);
							newFileName = newFileName + "_R";
							destFile = destFile.getParent().resolve(newFileName);
						}
						Files.move(file, destFile, StandardCopyOption.ATOMIC_MOVE);// 移动文件
					} else {
						System.err.println("Format_error：" + file.getFileName());// 文件够大，但是文件名不匹配正则表达式
						if (!Files.exists(notMatchDir))
							Files.createDirectories(notMatchDir);
						Files.move(file, notMatchDir.resolve(file.getFileName()), StandardCopyOption.ATOMIC_MOVE);// 移动文件
					}

				} catch (IOException e) {
					e.printStackTrace();
				}
			});
		}
	}
}
