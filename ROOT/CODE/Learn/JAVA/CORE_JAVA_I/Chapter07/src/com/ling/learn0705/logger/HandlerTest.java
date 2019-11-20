package com.ling.learn0705.logger;

import java.io.IOException;
import java.util.Arrays;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * 日志处理器
 * 
 * 1. 日志记录器会将日志发送到自己的处理器和父处理器，父处理器是将日志打印到控制台，假如我们设置了自己的处理器也是将日志打印到控制台，日志会被打印两次，解决办法是设置useParentHandlers属性为false
 * 
 * 2. FileHandler会将日志输出到文件
 * 
 * 3. 可以扩展Handler类或StreamHandler类来实现更加个性化的日志处理器
 *
 * Chapter7/com.ling.learn0705.logger.HandlerTest.java
 *
 * author lingang
 *
 * createTime 2019-11-10 01:23:38
 *
 */
public class HandlerTest {
	public static void main(String[] args) throws SecurityException, IOException {
		Logger log = Logger.getLogger("Whatever");
		log.setLevel(Level.FINE);
		// log.getHandlers();
		System.out.println(Arrays.toString(log.getHandlers()));
		log.fine("before set new handler");
		Handler handler = new ConsoleHandler(); // 使用自己的日志处理器
		handler.setLevel(Level.FINE);
		log.addHandler(handler);
		System.out.println(Arrays.toString(log.getHandlers()));
		log.fine("after set new handler");// 如果jdk的日志处理器级别设置为fine或者更低级，则这句话会打印两次，因为日志记录器会将日志发送到处理器和父处理器，解决办法是设置useParentHandlers属性为false
		log.setUseParentHandlers(false);
		log.fine("now, just print one time");

		// 将日志输出到文件中
		Handler fileHandler = new FileHandler();
		log.addHandler(fileHandler);
		log.info("send to condole and file");// 这句会在console打印，也会输出到用户主目录/javan.log中，n为文件名的唯一编号
	}
}
