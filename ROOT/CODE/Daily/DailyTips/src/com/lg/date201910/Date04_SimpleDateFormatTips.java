package com.lg.date201910;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 使用SimpleDateFormat解析日期时需要注意的事项
 * 
 * 1.对格式错误的日期字符串进行解析时，不会报错 ，但是解析出的日期是错误的
 * 
 * 2.多线程环境使用同一个SimpleDateFormat对象时，解析结果混乱
 *
 * DailyTips/com.lg.date201910.Date04_SimpleDateFormatTips.java
 *
 * author lingang
 *
 * createTime 2019-10-04 23:05:44
 *
 */
public class Date04_SimpleDateFormatTips {
	static SimpleDateFormat staticSDF = new SimpleDateFormat("yyyyMMdd");

	public static void main(String[] args) throws ParseException {
		/**
		 * 1.对格式错误的日期字符串进行解析时，不会报错 ，但是解析出的日期是错误的
		 */
		String notDateStr = "2019-10-04";

		// 格式为yyyyMMdd，但是要格式的日期字符串不满足这种格式
		Date parseDate = new SimpleDateFormat("yyyyMMdd").parse(notDateStr); // 解析没有报错
		System.out.println(new SimpleDateFormat("yyyyMMdd").format(parseDate)); // 但输出结果为20181031，不是想要的日期

		/**
		 * 2.多线程环境使用时，解析结果混乱
		 */
		for (int i = 0; i < 100; ++i) {
			// 运行后某些线程会出现解析结果不对，或者解析抛出异常
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					try {
						System.out.println(staticSDF.format(staticSDF.parseObject("20191004")));
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
