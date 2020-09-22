package com.lg.test;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 加入生词
 *
 * FileOperation/com.lg.test.Opt01GetEnglishWords.java
 *
 * author ling
 *
 * createTime 2020-09-13 03:40:19
 *
 */
public class Opt01GetEnglishWords {
	public static void main(String[] args) throws Exception {
		// 新单词列表
		Map<String, Integer> inputWords = new HashMap<>();
		Files.lines(Paths.get("Words/InputWords")).forEach(line -> {
			if (line != null && !line.trim().isEmpty() && !"".equals(line.trim())) {
				String[] params = line.split("\t");
				inputWords.put(params[0], Integer.parseInt(params[1]));
			}
		});
		System.out.println(inputWords.size());
		// 旧生词列表
		Map<String, Integer> oldNewWords = new HashMap<>();
		Files.lines(Paths.get("Words/NewWords")).forEach(line -> {
			if (line != null && !line.trim().isEmpty() && !"".equals(line.trim())) {
				String[] params = line.split("\t");
				oldNewWords.put(params[0], Integer.parseInt(params[1]));
			}
		});
		System.out.println(oldNewWords.size());
		for (String key : inputWords.keySet())
			if (oldNewWords.containsKey(key))
				oldNewWords.put(key, oldNewWords.get(key) + inputWords.get(key));
			else
				oldNewWords.put(key, inputWords.get(key));
		System.out.println(oldNewWords.size());
		Set<Map.Entry<String, Integer>> set = oldNewWords.entrySet();
		Map.Entry<String, Integer>[] es = set.stream().<Map.Entry<String, Integer>>toArray(Map.Entry[]::new);
		Arrays.sort(es, (e1, e2) -> {
			if (e1 == null && e2 == null)
				return 0;
			if (e1 == null)
				return 1;
			if (e2 == null)
				return -1;
			if (e1.getValue() > e2.getValue())
				return -1;
			else if (e1.getValue() < e2.getValue())
				return 1;
			return 0;
		});
		for (Map.Entry<String, Integer> en : es)
			System.out.println(en.getKey() + "\t" + en.getValue());
	}
}
