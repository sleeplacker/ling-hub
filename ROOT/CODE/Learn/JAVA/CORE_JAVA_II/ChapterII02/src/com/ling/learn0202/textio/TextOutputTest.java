package com.ling.learn0202.textio;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Date;

/**
 * 文本输出
 *
 * ChapterII02/com.ling.learn0202.textio.TextOutputTest.java
 *
 * author lingang
 *
 * createTime 2020-01-22 10:42:51
 *
 */
public class TextOutputTest {
	public static void main(String[] args) throws FileNotFoundException {
		/**
		 * PrintWriter-打印写出器带有缓冲区，所以写完数据后要调用flush方法冲刷缓冲区
		 */
		// 写法一，不带自动冲刷参数，默认禁止自动冲刷
		PrintWriter out1 = new PrintWriter(System.out);
		out1.print("写法一：不带自动冲刷参数，默认禁止自动冲刷\n");
		out1.print("写字符串：hhh\n");
		out1.print("写对象(会调用对象的toString方法并写出)：");
		out1.println(new Date());// println方法会写出行结束符
		out1.flush();// 不带自动冲刷参数时，必须调用flush方法冲刷缓冲区才能将数据送到目的地，此处的目的地就是控制台

		// 写法二，指定第二个参数为true表示
		PrintWriter out2 = new PrintWriter(System.out, true);
		out2.print("写法二，指定第二个参数2为true表示");
		out2.println("end");// 设置自动冲刷参数为true时，无需调用flush方法，但是最后要使用println方法打印出行结束符才能冲刷缓冲区
		System.out.println(System.getProperty("line.separator") + "|");// System.getProperty("line.separator")方法可以获得当前系统的行结束符

		// 写法三，直接指定文件路径并写入，这个构造器不能设置自动冲刷参数，必须调用flush方法
		PrintWriter out3 = new PrintWriter("D:/filetest/abc.txt");
		out3.print("AAA\n");
		out3.println("BBB CCC");
		out3.flush();
	}
}
