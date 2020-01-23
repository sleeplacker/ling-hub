package com.ling.learn0203.binaryio;

import java.io.DataInput;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 二进制读写
 *
 * ChapterII02/com.ling.learn0203.binaryio.BinartInOutTest.java
 *
 * author lingang
 *
 * createTime 2020-01-23 11:29:52
 *
 */
public class BinartInOutTest {
	public static void main(String[] args) throws IOException {
		/* 1. 二进制写出 */
		// 待写入数据
		String name = "lin";
		int age = 17;
		double score = 99.9;

		// 二进制方式写入
		DataOutput dout = new DataOutputStream(new FileOutputStream("D:/filetest/binarydata.txt"));
		dout.writeChars(name);
		dout.writeInt(age);
		dout.writeDouble(score);
		// 文本方式写入
		PrintWriter tout = new PrintWriter("D:/filetest/textdata.txt");
		tout.print(name);
		tout.print(age);
		tout.print(score);
		tout.flush();

		/* 2. 二进制读入 */
		DataInput din = new DataInputStream(new FileInputStream("D:/filetest/binarydata.txt"));
		// 读入时必输按照写入顺序对应的类型来读取，否则会读到没有意义的信息
		System.out.println(din.readChar());
		System.out.println(din.readChar());
		System.out.println(din.readChar());
		System.out.println(din.readInt());
		System.out.println(din.readDouble());
	}
}
