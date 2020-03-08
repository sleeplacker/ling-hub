package com.ling.learn0901.classloader;

import java.io.IOException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 类加载器的一些API使用
 *
 * ChapterII09_security/com.ling.learn0901.classloader.ClassLoaderTest.java
 *
 * author lingang
 *
 * createTime 2020-03-09 01:41:57
 *
 */
public class ClassLoaderTest {
	public static void main(String[] args) throws IOException {
		// 获取当前线程的上下文类加载器
		ClassLoader curLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(curLoader);// AppClassLoader，主线程默认使用的是系统类加载器
		System.out.println(curLoader.getParent());// ExtClassLoader，父亲是扩展类加载器

		// 创建一个插件类加载器
		URLClassLoader urlLoader = new URLClassLoader(new URL[] {});
		System.out.println(urlLoader.getParent());// AppClassLoader，父亲是系统类加载器
		urlLoader.close();

		// 自定义类加载器
		MyClassLoader myLoader = new MyClassLoader();
		System.out.println(myLoader.getParent());// AppClassLoader，自定义类加载器的父亲是系统类加载器

		/* 类加载器的父亲和类加载器的父类不是一个概念 */
		MySubClassLoader mySubLoader = new MySubClassLoader();
		System.out.println(mySubLoader.getParent());// AppClassLoader，虽然MySubClassLoader继承自MyClassLoader，但是它非父亲仍然是系统类加载器

		// 设置当前线程上下文类加载器
		Thread.currentThread().setContextClassLoader(urlLoader);
		curLoader = Thread.currentThread().getContextClassLoader();
		System.out.println(curLoader);

		//获取系统类加载器，即用于加载第一个应用类的类加载器
		System.out.println(ClassLoader.getSystemClassLoader());
	}
}

// 自定义一个类加载器
class MyClassLoader extends ClassLoader {

}

// 继承自定义类加载器
class MySubClassLoader extends MyClassLoader {

}
