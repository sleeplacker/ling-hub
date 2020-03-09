//package com.ling.learn0901.outer;
//
//import com.ling.learn0901.classloader.LoadClassTool;
//
///**
// * 这个类被打包后就要注释掉
// *
// * ChapterII09_security/com.ling.learn0901.outer.OuterClass.java
// *
// * author lingang
// *
// * createTime 2020-03-10 01:57:45
// *
// */
//public class OuterClass {
//	// 借助系统类中的工具来加载类
//	public static Class<?> loadClassBySystem(ClassLoader loader) throws ClassNotFoundException {
//		Class<?> cl = LoadClassTool.loadClass("com.ling.learn0901.outer.ToBeCall", loader);
//		System.out.println("使用系统类中的工具加载类成功：" + cl);
//		return cl;
//	}
//}
