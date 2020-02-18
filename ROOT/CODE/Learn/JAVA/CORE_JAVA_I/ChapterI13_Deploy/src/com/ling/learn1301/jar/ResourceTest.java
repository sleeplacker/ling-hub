package com.ling.learn1301.jar;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

/**
 * 读取资源文件
 *
 * Chapter13/com.ling.learn1301.jar.ResourceTest.java
 *
 * author lingang
 *
 * createTime 2019-12-05 23:02:28 
 *
 */
public class ResourceTest {
	public static void main(String[] args) throws IOException {
		/* 1. 读取二进制文件 */
		/* 相对路径-在同一目录 */
		InputStream in = new ResourceTest().getClass().getResourceAsStream("createJarFileCmd.txt");// ok
		/* 相对路径-在当前目录的子目录 */
		// InputStream in = new
		// ResourceTest().getClass().getResourceAsStream("subdir1/subdir2/about.txt");//ok
		/* 相对路径-在当前目录的子目录 */
		// InputStream in = new
		// ResourceTest().getClass().getResourceAsStream("subdir1\\subdir2\\about.txt");//ok
		/* "/"开头的是绝对路径 */
		// InputStream in = new
		// ResourceTest().getClass().getResourceAsStream("/subdir1/subdir2/about.txt");//
		// 资源不存在
		byte[] buf = new byte[1024];
		int cnt = 0;
		while ((cnt = in.read(buf)) > 0) {
			System.out.println(new String(buf, 0, cnt));
		}
		in.close();

		/* 2. 读取文件URL */
		URL img1 = new ResourceTest().getClass().getResource("img1.jpg");
		System.out.println(img1);
	}
}
