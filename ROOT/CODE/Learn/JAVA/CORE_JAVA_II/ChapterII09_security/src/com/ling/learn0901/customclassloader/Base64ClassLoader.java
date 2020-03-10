package com.ling.learn0901.customclassloader;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Base64;

/**
 * 自定义类加载器，实现对Base64编码的类文件加载
 * 
 * 自定义类加载器需要做到的几点： 1) 为来自本地文件系统或者其他来源的类加载器加载其字节码 2)
 * 调用ClassLoader超类的defineClass方法，向虚拟机提供字节码
 *
 * ChapterII09_security/com.ling.learn0901.customclassloader.Base64ClassLoader.java
 *
 * author lingang
 *
 * createTime 2020-03-10 22:37:22
 *
 */
public class Base64ClassLoader extends ClassLoader {

	@Override
	protected Class<?> findClass(String name) throws ClassNotFoundException {
		Class<?> cl = null;
		try {
			byte[] classBytes = loadClassByte(name);
			cl = defineClass(name, classBytes, 0, classBytes.length);
			if (cl == null)
				throw new ClassNotFoundException();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return cl;
	}

	private byte[] loadClassByte(String name) throws IOException {
		byte[] bytes = Files.readAllBytes(Paths.get("src/" + name.replace(".", "/") + ".b64"));
		return Base64.getDecoder().decode(bytes);
	}

}
