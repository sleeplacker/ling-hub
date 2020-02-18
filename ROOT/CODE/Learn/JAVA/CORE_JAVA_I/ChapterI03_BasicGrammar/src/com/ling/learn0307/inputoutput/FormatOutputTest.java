package com.ling.learn0307.inputoutput;

import java.util.Formattable;
import java.util.Formatter;

/**
 * 格式化输出
 *
 * Chapter3/com.ling.learn0307.inputoutput.FormatOutputTest.java
 *
 * author lingang
 *
 * createTime 2019-10-07 23:48:44
 *
 */
public class FormatOutputTest {

	// 没实现Formattable接口的类
	static class NoramalClassTest{

		@Override
		public String toString() {
			return "call NoramalClassTest.toString";
		}

	}
	
//	没实现Formattable接口的类
	static class FormattableClassTest implements Formattable {
		
		@Override
		public void formatTo(Formatter formatter, int flags, int width, int precision) {
			System.out.println("call Formattable.formatTo");
		}
		
		@Override
		public String toString() {
			return "call Formattable.toString";
		}
		
	}
	public static void main(String[] args) {

		/*
		 * 1.System.out.print方法 和
		 * System.out.println方法打印和参数数据类型能表示的最数值的长度(例如浮点型数据的有效位数)
		 */
		double maxDoubleValue = Double.MAX_VALUE;// 数据类型最大值-这里主要看有效位位数
		double maxLenthValue = 10.0 / 3;
		System.out.println(maxDoubleValue);
		System.out.println(maxLenthValue); // System.out.println方法格式化规则：打印和该数据类型能表示的最数值的长度

		/* 2. System.out.printf方法类似C语言中的格式化打印方法 */
		System.out.printf("%s is %d years old!", "lingang", 28); // 和C语言格式化输出语法类似，s和d为转换符，总共有14种，见58页

		System.out.printf("\n%,.2f", 10000000.0 / 3); // ,标志将小数点前的数字每3位用逗号隔开，总共有10种标志，见59页

		/* 3. %s可以格式化任意对象，对于实现了Formattable接口的类的对象，会调用其formatTo方法
		 * 否则调用toString方法
		 */
		System.out.printf("\n%s",new NoramalClassTest());
		System.out.printf("\n%s",new FormattableClassTest());
		
		/* 4. 可以用n$多次格式化同一个参数，n从1开始，而不是0开始*/
		System.out.printf("\n%1$s = %2$.2f or = %2$.3f", "1/3", 1.0/3);
		
		/* 5. String.format 方法可以将格式化后的字符串保存到String变量，而不必打印 */
		String formatMsg = String.format("\n%1$s = %2$.2f or = %2$.3f", "1/3", 1.0/3);
		System.out.println(formatMsg);
		
		
	}

}
