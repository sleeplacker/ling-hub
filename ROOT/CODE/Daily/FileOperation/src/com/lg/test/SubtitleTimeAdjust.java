package com.lg.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字幕时间调整
 *
 * FileOperation/com.lg.test.SubtitleTimeAdjust.java
 *
 * author lingang
 *
 * createTime 2020-03-09 23:47:42
 *
 */
public class SubtitleTimeAdjust {
	public static String FILE_NAME = "src/com/lg/test/xxx.srt";// 文件路径
	public static String CHARSET = "GBK";// 文件编码
	public static String PATTERN = "\\d{2}:\\d{2}:\\d{2},\\d{3} --> \\d{2}:\\d{2}:\\d{2},\\d{3}";// 字幕时间正则表达式
	public static DateTimeFormatter TS = DateTimeFormatter.ofPattern("HH:mm:ss,SSS");// 字幕时间格式
	public static int DELAY_MS = -7000;// 延迟毫秒数，负数表示提前
	public static Path PATH = Paths.get(FILE_NAME);

	public static void main(String[] args) throws IOException {
		Files.lines(PATH, Charset.forName(CHARSET)).forEach(line -> {
			// 00:01:00,000 --> 00:01:08,000
			Pattern pattern = Pattern.compile(PATTERN);
			Matcher matcher = pattern.matcher(line);
			if (matcher.matches()) {
				LocalTime start = LocalTime.parse(line.substring(0, 12), TS);
				start = start.plus(Duration.ofMillis(DELAY_MS));
				LocalTime end = LocalTime.parse(line.substring(17, 29), TS);
				end = end.plus(Duration.ofMillis(DELAY_MS));
				System.out.println(TS.format(start) + " --> " + TS.format(end));
			} else
				System.out.println(line);
		});
	}
}
