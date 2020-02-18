package com.ling.learn0706.debugtips;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.FieldPosition;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 调试技巧
 *
 * Chapter7/com.ling.learn0706.debugtips.DebuggingTips.java
 *
 * author lingang
 *
 * createTime 2019-11-11 17:37:51
 *
 */
public class DebuggingTips {
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		/* 日志代理-使用匿名内部类的方式扩展一个类，并重写方法，在方法前后加入日志记录信息 */
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd") {

			@Override
			public StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition pos) {
				System.out.println("正在格式化日期：" + date);
				return super.format(date, toAppendTo, pos);
			}
		};

		System.out.println(sdf.format(new Date())); // 这里除了打印格式化后的日期外，还会打印正在格式化日期：...

		/* 打印当前堆栈轨迹 */
		Thread.dumpStack();

		/* 通过Throwable.printStackTrace方法将堆栈轨迹的流重定向到字符串中 */
		StringWriter out = new StringWriter();
		new Throwable().printStackTrace(new PrintWriter(out));
		String description = out.toString();
		System.out.println(description);
	}
}
