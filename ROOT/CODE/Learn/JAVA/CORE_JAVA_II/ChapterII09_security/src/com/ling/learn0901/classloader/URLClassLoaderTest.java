package com.ling.learn0901.classloader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 通过URL加载类
 * 
 * 进入项目根目录/bin，执行：jar cvf outer.jar com/ling/learn0901/outer/*.class
 * 将outer.jar放到D:/corejava下 注释com.ling.learn0901.outer包中的两个类并clean后再执行此类
 *
 * ChapterII09_security/com.ling.learn0901.classloader.URLClassLoaderTest.java
 *
 * author lingang
 *
 * createTime 2020-03-10 01:47:46
 *
 */
public class URLClassLoaderTest {
	public static void main(String[] args) throws ClassNotFoundException, NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException, IOException {
		URL url = new URL("file:///D:/corejava/outer.jar");
		URLClassLoader urlLoader = new URLClassLoader(new URL[] { url });
		// 这样加载了ToBeCall，后面还是找不到
		Class<?> cl2 = urlLoader.loadClass("com.ling.learn0901.outer.ToBeCall");
		System.out.println(cl2);
		Class<?> cl = urlLoader.loadClass("com.ling.learn0901.outer.OuterClass");
		System.out.println(cl.getClassLoader());
		Method loadTool = cl.getDeclaredMethod("loadClassBySystem", ClassLoader.class);
		@SuppressWarnings("unused")
		ClassLoader loader = null;
		// 加载失败，因为loader送null，LoadClassTool会使用Class.forName加载类，使用的是系统类加载器，无法加载磁盘上的class
		// Class<?> destClass = (Class<?>) loadTool.invoke(null, loader);
		// 加载成功，因为指定了classloader为可加载磁盘上class的URLClassLoader
		Class<?> destClass = (Class<?>) loadTool.invoke(null, urlLoader);
		destClass.getDeclaredMethod("call").invoke(null);
		System.out.println("当前线程类加载器：" + Thread.currentThread().getContextClassLoader());
		// 已经被其他类加载器加载的类，再用系统类加载器来加载也是加载不到的
		Thread.currentThread().getContextClassLoader().loadClass("com.ling.learn0901.outer.ToBeCall");// java.lang.ClassNotFoundException
		urlLoader.close();
	}
}
