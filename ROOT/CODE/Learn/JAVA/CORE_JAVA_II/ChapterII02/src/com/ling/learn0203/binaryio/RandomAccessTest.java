package com.ling.learn0203.binaryio;

import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * 文件随机访问
 *
 * ChapterII02/com.ling.learn0203.binaryio.RandomAccessTest.java
 *
 * author lingang
 *
 * createTime 2020-01-23 12:01:14
 *
 */
public class RandomAccessTest {
	public static void main(String[] args) throws IOException {
		/* RandomAccessFile实现了DataInput和DataOuput接口，所以可以方便地调用二进制读/写方法 */
		// 随机写
		RandomAccessFile raf = new RandomAccessFile("D:/filetest/random_access.txt", "rw");// r-可读，w-可写
		raf.writeChars("一三四");// 二进制写字符其实就是按UTF-16编码方式写出，所以将二进制文件以UTF-16格式打开就能看到人能识别的字符(例如拷贝到eclipse指定UTF-16编码方式打开)
		raf.seek(raf.getFilePointer() - 2 * 2);// 将随机访问指针前移2位(因为指针指示的是字节位置，二一个字符占2字节，所以要乘以2)，即在"一"后面
		raf.writeChars("二");// 在该位置写"二"，这样会覆盖原来在这个位置上的"三"

		// 随机读
		raf.seek(0);// 将指针移到文件头
		System.out.println(raf.readChar());// 读字符
		System.out.println(raf.readChar());
		System.out.println(raf.readChar());

		// 获取文件长度，单位为字节
		System.out.println(raf.length());// 3个字符，长度6字节
		raf.close();
	}
}
