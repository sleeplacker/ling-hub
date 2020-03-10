package com.ling.learn0901.customclassloader;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Base64;

/**
 * 测试自定义类加载器
 * 
 * 将Base64Class.java编译得到的Base64Class.class放到当前package，然后执行本程序观察使用的类加载器
 * 然后将Base64Class.java注释掉，再clean，再执行本程序观察使用的类加载器
 *
 * ChapterII09_security/com.ling.learn0901.customclassloader.Base64ClassLoaderTest.java
 *
 * author lingang
 *
 * createTime 2020-03-10 23:04:23
 *
 */
public class Base64ClassLoaderTest {
	public static void main(String[] args) throws IOException, ClassNotFoundException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		// 将class文件用base64编码为字符串
		byte[] classBytes = Files.readAllBytes(Paths.get("src/com/ling/learn0901/customclassloader/Base64Class.class"));
		String base64Class = Base64.getEncoder().encodeToString(classBytes);
		System.out.println("Base64编码后的类：" + base64Class);
		// 将Base64类字符串写到文件
		Path path = Paths.get("src/com/ling/learn0901/customclassloader/Base64Class.b64");
		if (!Files.exists(path))
			Files.createFile(path);
		Files.write(path, base64Class.getBytes(), StandardOpenOption.WRITE);

		// 加载并运行base64类
		Base64ClassLoader loader = new Base64ClassLoader();
		Class<?> cl = loader.loadClass("com.ling.learn0901.customclassloader.Base64Class");
		System.out.println("加载使用的类加载器：" + cl.getClassLoader());
		cl.getDeclaredMethod("hello").invoke(null);
	}
}
