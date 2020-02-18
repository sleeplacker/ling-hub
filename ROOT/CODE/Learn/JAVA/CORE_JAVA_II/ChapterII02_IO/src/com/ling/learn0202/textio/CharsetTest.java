package com.ling.learn0202.textio;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * 编码
 *
 * ChapterII02/com.ling.learn0202.textio.CharsetTest.java
 *
 * author lingang
 *
 * createTime 2020-01-22 16:41:58
 *
 */
public class CharsetTest {
	public static void main(String[] args) throws IOException {
		/**
		 * 1. 编码：字符 -> 字节序列
		 */
		String str = "冠状";
		PrintWriter print1 = new PrintWriter("D:/filetest/text_uft8.txt", "UTF-8");
		print1.print("消灭" + str);
		print1.flush();
		PrintWriter print2 = new PrintWriter("D:/filetest/text_uft16.txt", "UTF-16");
		print2.print("消灭" + str);
		print2.flush();
		PrintWriter print3 = new PrintWriter("D:/filetest/text_gbk.txt", "GBK");
		print3.print("消灭" + str);
		print3.flush();

		System.out.print("UTF-8编码结果：");
		InputStream read1 = new FileInputStream("D:/filetest/text_uft8.txt");
		int temp;
		while ((temp = read1.read()) != -1) {
			System.out.print(Integer.toHexString(temp) + " ");
			/**
			 * e6 b6 88 e7 81 ad e5 86 a0 e7 8a b6， UTF-8编码中每个中文占3个字节，当然有些生僻字例外
			 */
		}

		System.out.print("\nUTF-16编码结果：");
		InputStream read2 = new FileInputStream("D:/filetest/text_uft16.txt");
		while ((temp = read2.read()) != -1) {
			System.out.print(Integer.toHexString(temp) + " ");
			/**
			 * fe ff 6d 88 70 6d 51 a0 72 b6，
			 * 因为UTF-16编码是双字节，所以高低位字节的顺序就有两种情况，feff表示低位字节在前，相反fffe表示高位字节在前
			 */
		}

		System.out.print("\nGBK编码结果：");
		InputStream read3 = new FileInputStream("D:/filetest/text_gbk.txt");
		while ((temp = read3.read()) != -1) {
			System.out.print(Integer.toHexString(temp) + " ");
			/**
			 * cf fb c3 f0 b9 da d7 b4， GBK编码中每个中文占两个字节，有些繁体字和生僻字例外
			 */
		}

		/**
		 * 2. java字面量和字符串变量转换为字节流-指定不同的编码，转换的字节流不一样
		 */
		System.out.println();
		byte[] bs_default = ("消灭" + str).getBytes();
		System.out.print("字面量调getBytes方法-不指定编码方式：");
		for (int b : bs_default) {
			b = b & 0xff;
			System.out.print(Integer.toHexString(b) + " ");
		}

		System.out.println();
		System.out.print("字面量调getBytes方法-指定为UTF-8编码：");
		byte[] bs_utf8 = ("消灭" + str).getBytes("UTF-8");
		for (int b : bs_utf8) {
			b = b & 0xff;
			System.out.print(Integer.toHexString(b) + " ");
		}

		System.out.println();
		System.out.print("字面量调getBytes方法-指定为UTF-16编码：");
		byte[] bs_utf16 = ("消灭" + str).getBytes("UTF-16");
		for (int b : bs_utf16) {
			b = b & 0xff;
			System.out.print(Integer.toHexString(b) + " ");
		}

		System.out.println();
		System.out.print("字面量调getBytes方法-指定为GBK编码：");
		byte[] bs_gbk = ("消灭" + str).getBytes("GBK");
		for (int b : bs_gbk) {
			b = b & 0xff;
			System.out.print(Integer.toHexString(b) + " ");
		}

		/**
		 * 从打印结果可以看出默认编码方式和UTF-8编码方式获取的字节流是一致的，因为当调用String.getBytes()方法时，会使用Charset.defaultCharset()方法返回的编码，而该方法返回的默认编码正是UTF-8
		 */
		System.out.println("\n" + Charset.defaultCharset());

		/**
		 * 3. 转换文件编码格式：需要先将文件读入 -> 转换为字符串货字符变量 -> 用上面的方法指定转换编码来转换为不同的字节流并写入文件
		 */
		// 1) 将GBK格式文本文件读入字符串变量
		String content = new String(Files.readAllBytes(Paths.get(URI.create("file:/D:/filetest/text_gbk.txt"))), "GBK");
		System.out.println("打印读入的字串变量：" + content);
		// 2) 将字符串变量按照UTF-8编码方式转换为字节流
		byte[] bytes_utf8 = content.getBytes("UTF-8");
		// 3) 将UTF-8字节流写入新文件
		FileOutputStream fos = new FileOutputStream("D:/filetest/text_gbk_to_utf8.txt");
		fos.write(bytes_utf8);
		fos.flush();
		fos.close();
		// 现在查看D:/filetest/text_gbk_to_utf8.txt文件的编码就变为UTF-8了

	}
}
