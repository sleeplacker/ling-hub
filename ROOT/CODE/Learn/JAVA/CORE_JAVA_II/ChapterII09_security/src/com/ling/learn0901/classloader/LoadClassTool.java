package com.ling.learn0901.classloader;

import java.net.MalformedURLException;

/**
 * 加载类的工具，被磁盘上的类调用
 *
 * ChapterII09_security/com.ling.learn0901.classloader.LoadClassTool.java
 *
 * author lingang
 *
 * createTime 2020-03-10 02:07:54
 *
 */
public class LoadClassTool {
	public static Class<?> loadClass(String className, ClassLoader loader)
			throws ClassNotFoundException, MalformedURLException {
		if (loader == null) {
			return Class.forName(className);// Class.forName使用的是系统类加载器
		} else {
			System.out.println("Using " + loader);
			return loader.loadClass(className);
		}

	}
}
