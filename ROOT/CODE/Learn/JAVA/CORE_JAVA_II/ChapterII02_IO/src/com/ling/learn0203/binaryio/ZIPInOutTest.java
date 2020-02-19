package com.ling.learn0203.binaryio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

/**
 * ZIP文档的处理
 *
 * ChapterII02/com.ling.learn0203.binaryio.ZIPInOutTest.java
 *
 * author lingang
 *
 * createTime 2020-01-23 15:52:45
 *
 */
public class ZIPInOutTest {
	public static void main(String[] args) throws IOException {
		// 1. zip文件写出
		// 如果文件名形如justdir/，那么只会创建一个空目录；如果文件名形如subdir/file，那么压缩文件创建subdir目录；如果只指定文件名不写入内容，会创建一个空文件
		String[] files = { "file1|abc", "subdir/file2|123", "justdir/", "emptyFile" };
		ZipOutputStream zos = new ZipOutputStream(new FileOutputStream("D:/filetest/ziptest.zip"));
		for (String file : files) {
			String[] ss = file.split("\\|");
			ZipEntry ze = new ZipEntry(ss[0]);
			zos.putNextEntry(ze);
			if (ss.length > 1)
				zos.write(ss[1].getBytes());
			zos.closeEntry();
		}
		zos.close();// 这里的close是必须的，否则亲测压缩文件是被损坏的

		/* 2. zip文件读入 */
		// 1) 使用ZipInputStream类解压
		System.out.println("使用ZipInputStream类解压：");
		ZipInputStream zis = new ZipInputStream(new FileInputStream("D:/filetest/ziptest.zip"));
		ZipEntry ze;
		while ((ze = zis.getNextEntry()) != null) {
			// 打印出压缩文件中的项
			System.out.println("*" + ze + "|");
			zis.closeEntry();
		}
		zis.close();

		// 使用ZipFile类解压
		System.out.println("使用ZipFile类解压：");
		ZipFile zf = new ZipFile("D:/filetest/ziptest.zip");
		zf.stream().forEach(zipEntry -> {
			System.out.println(zipEntry);
		});
		zf.close();
	}
}
