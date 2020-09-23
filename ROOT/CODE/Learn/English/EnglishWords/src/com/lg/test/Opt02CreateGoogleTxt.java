package com.lg.test;

import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 给单词前后加上句号，以便于划词使用
 *
 * EnglishWords/com.lg.test.Opt02CreateGoogleTxt.java
 *
 * author ling
 *
 * createTime 2020-09-23 10:57:21
 *
 */
public class Opt02CreateGoogleTxt {
	public static void main(String[] args) throws Exception {
		StringBuffer sb = new StringBuffer();
		Files.lines(Paths.get("Words/NewWords")).forEach(line -> {
			if (line != null && !line.trim().isEmpty() && !"".equals(line.trim())) {
				String[] params = line.split("\t");
				sb.append(".\t").append(params[0]).append(".").append(params[1]).append(".\n");
			}
		});
		System.out.println(sb.toString());
	}
}
