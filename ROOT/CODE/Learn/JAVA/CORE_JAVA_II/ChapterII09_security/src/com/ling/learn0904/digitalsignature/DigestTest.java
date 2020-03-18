package com.ling.learn0904.digitalsignature;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * 生成指纹，使用SHA-256摘要算法，并将结果以两位大写十六进制数表示一个字节
 *
 * ChapterII09_security/com.ling.learn0904.digitalsignature.DigestTest.java
 *
 * author lingang
 *
 * createTime 2020-03-19 02:24:02
 *
 */
public class DigestTest {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		MessageDigest alg = MessageDigest.getInstance("SHA-256");// 指定摘要算法
		byte[] input = "abc1234".getBytes();
		byte[] hash = alg.digest(input);
		String result = "";
		for (byte b : hash) {
			int h = b & 0xff;// 由于是补码表示，所以可能有8位十六进制数
			if (h < 16)
				result += "0";
			result += Integer.toHexString(h).toUpperCase() + " ";
		}
		System.out.println(result);
	}
}
