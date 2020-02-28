package com.ling.learn0707.charset;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * 控制台编码设置，进到bin目录分别执行下面三个命令
 *
 * 1：java com.ling.learn0707.charset.ConsoleTest 
 * 这个命令打印UTF-8版本会乱码，因为cmd控制台是GBK编码
 * 
 * 2：java -Dfile.encoding=UTF-8 com.ling.learn0707.charset.ConsoleTest
 * 这个命令打印GBK版本会乱码，因为cmd控制台已经通过file.encoding参数被设置为了UTF-8
 * 
 * 3：java -Dfile.encoding=GBK com.ling.learn0707.charset.ConsoleTest
 * 这个命令打印UTF-8版本会乱码，因为cmd控制台通过file.encoding参数设置为GBK编码
 *
 * ChapterII07_Internationalization/com.ling.learn0707.charset.ConsoleTest.java
 * 
 *
 * author lingang
 *
 * createTime 2020-02-28 02:15:27
 *
 */
public class ConsoleTest {
	public static void main(String[] args) throws UnsupportedEncodingException {

		// 获取控制台编码
		Charset platformEncoding = Charset.defaultCharset();
		System.out.println(platformEncoding);

		String str = "你們好";
		// 不带参数的getBytes方法会使用平台编码，cmd平台编码是GBK，所以打印出来不会有乱码
		System.out.println("default_charset : " + new String(str.getBytes()));

		System.out.println("utf8 : " + new String(str.getBytes("UTF-8")));

		System.out.println("gbk : " + new String(str.getBytes("GBK")));

		// 无用功，new
		// String(xxx.getBytes("XXX"),"XXX"))这种写法是无用功，和使用xxx没区别，但是有个前提条件，就是XXX编码字符集要支持xxx字面量的显示，
		// 比如ISO-8859-1不支持中文，那么new
		// String("你們好".getBytes("ISO-8859-1"),"ISO-8859-1"))得到的就是乱码，因为getBytes得到的字节数组是错误的
		// 1. 将字面量获取为UTF-8字节数组，又将UTF-8字节数组封装成UTF-8字面量，这个字面量肯定是正确的，不会乱码
		System.out.println("无用功UTF-8到UTF-8 : " + new String(str.getBytes("UTF-8"), "UTF-8"));
		// 2 将字面量获取为UTF-8字节数组，又将UTF-8字节数组封装成GBK字面量，这个字面量肯定是乱码的，所以打印出来也一定是乱码
		System.out.println("无用功UTF-8到GBK : " + new String(str.getBytes("UTF-8"), "GBK"));
		// 下面两种情况和上面一个原理
		System.out.println("无用功GBK到GBK : " + new String(str.getBytes("GBK"), "GBK"));
		System.out.println("无用功GBK到UTF-8 : " + new String(str.getBytes("GBK"), "UTF-8"));
		
		// 当编码字符集不支持字符串字符时，上面的无用功会导致打印乱码
		System.out.println("当编码字符集不支持字符串字符时 : " + new String(str.getBytes("ISO-8859-1"), "ISO-8859-1"));//???，打印乱码
	}
}
