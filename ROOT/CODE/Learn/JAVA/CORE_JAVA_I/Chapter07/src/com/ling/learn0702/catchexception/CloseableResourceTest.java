package com.ling.learn0702.catchexception;

import java.io.FileInputStream;
import java.io.InputStream;

/**
 * 带资源的try语句：在try()中打开资源，且该资源实现了AutoCloseable接口，那么try块退出时，会自动调用资源的close方法；且如果try块中抛出异常，而close方法也抛出异常，那么close方法抛出的异常会被抑制(这些异常将自动捕获，并由addSuppressed方法增加到原来的异常，如果对这些异常感兴趣，可以调用getSuppressed方法，它会得到从close方法抛出的被抑制的异常列表)
 *
 * Chapter7/com.ling.learn0702.catchexception.CloseableResourceTest.java
 *
 * author lingang
 *
 * createTime 2019-11-04 01:38:34
 *
 */
public class CloseableResourceTest {
	public static void main(String[] args) {
		try (InputStream is = new FileInputStream("D:/jad.exe")) { // 在try()中打开资源，且该资源实现了AutoCloseable接口，那么try块退出时，会自动调用资源的close方法；且如果try块中抛出异常，而close方法也抛出异常，那么close方法抛出的异常会被抑制
			is.read();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
