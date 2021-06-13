package com.lg.date202106;

// Sun jdk：java -Xms5m -Xmx8m -XX:+HeapDumpOnOutOfMemoryError  -XX:HeapDumpPath=C:\Users\1\Desktop\ com.lg.test.DumpTest
// IBM jdk：java -Xms5m -Xmx8m  com.lg.test.DumpTest
/**
 * 产生 dump 文件
 *
 * DailyTips/com.lg.date202106.Date12_DumpTest.java
 *
 * author ling
 *
 * createTime 2021-06-13 06:44:14 
 *
 */
public class Date12_DumpTest {
	public static void main(String[] args) {
		byte[] bs = new byte[10 * 1024 * 1024];
		System.out.println(bs.toString());
	}

}
