package com.ling.learn0705.logger;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * 过滤器
 *
 * Chapter7/com.ling.learn0705.logger.FilterTest.java
 *
 * author lingang
 *
 * createTime 2019-11-11 16:44:21
 *
 */
public class FilterTest {
	public static void main(String[] args) {
		Logger log = Logger.getLogger("whatever");
		log.setFilter(new Filter() {

			@Override
			public boolean isLoggable(LogRecord record) { // 决定是否要打印此记录，返回true就会打印，否则不打印
				String msg = record.getMessage();
				if (msg.contains("aaa")) {
					return false;
				}
				return true;
			}
		});
		log.info("abc");
		log.info("aabc");
		log.info("aaabc");// 这句会被过滤器过滤，所以不会打印
	}
}
