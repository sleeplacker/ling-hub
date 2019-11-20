package com.ling.learn0705.logger;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * JDK自带的日志功能
 * 
 * 1. 级别高至低：SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST
 * 
 * 2. 如果修改了日志记录器的级别，那么需要修改日志处理器的级别，日志处理器不会处理更低级的日志
 * 
 * 3. 可以使用不同的log方法来打印各种操作， 比如entering方法标识进入方法， exiting标识离开方法， throwing表示抛出异常等。
 * 这些不同的操作会打印出不同的标识来区分这些操作， 例如throwing方法打印的日志一定是以THROW开始的
 * 
 * 
 *
 * Chapter7/com.ling.learn0705.logger.LoggerTest.java
 *
 * author lingang
 *
 * createTime 2019-11-09 00:16:49
 *
 */
public class LoggerTest {
	private static final Logger logger = Logger.getLogger("whatever");

	public static void main(String[] args) {
		Logger.getGlobal().info("XX");// 使用全局日志记录器
		Logger log = Logger.getLogger("com.ling.learn0705.logger");
		log.info("info");
		log.warning("warning");
		log.fine("fine"); // 这里不会打印到控制台，因为默认是INFO级别，所以FINE级别的log不会打印

		// 打印方法中各种操作的日志
		read("fileName", "pattern");

		// 打印抛出异常的日志
		log.throwing("MyClass", "MyMethod", new IOException());

		// 使用本地化
		Logger logLoc = Logger.getLogger("hahaha", "com.ling.learn0705.logger.msg");
		logLoc.info("Name");
	}

	// 自定义类名和方法名来打印
	public static int read(String file, String pattern) {
		logger.setLevel(Level.FINEST);
		logger.entering("MyClass", "read", new Object[] { file, pattern }); // 进入方法
		System.out.println("do something in method read");
		logger.exiting("MyClass", "read", 1);// 离开方法
		return 1;
	}
}
