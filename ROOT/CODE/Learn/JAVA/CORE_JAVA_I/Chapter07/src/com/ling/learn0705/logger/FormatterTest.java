package com.ling.learn0705.logger;

import java.util.logging.Formatter;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 格式化器
 *
 * Chapter7/com.ling.learn0705.logger.FormatterTest.java
 *
 * author lingang
 *
 * createTime 2019-11-11 16:50:22
 *
 */
public class FormatterTest {
	public static void main(String[] args) {
		Logger log = Logger.getLogger("whatever");
		log.addHandler(new Handler() {

			@Override
			public void publish(LogRecord record) {
				// TODO Auto-generated method stub
				Formatter formatter = new Formatter() { // Formatter用于格式化输出

					@Override
					public String format(LogRecord record) { // 实现format方法来对日志进行格式化
						return "#" + record.getMessage() + "#";// 对原始日志进行修饰
					}
				};
				System.out.println(formatter.format(record));
			}

			@Override
			public void flush() {
			}

			@Override
			public void close() throws SecurityException {
			}
		});
		log.setUseParentHandlers(false);
		log.info("XXX");// 打印#XXX#
	}
}
