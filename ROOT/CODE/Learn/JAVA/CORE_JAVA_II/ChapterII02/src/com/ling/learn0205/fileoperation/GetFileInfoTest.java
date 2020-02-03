package com.ling.learn0205.fileoperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.PosixFileAttributes;
import java.nio.file.attribute.UserPrincipal;

/**
 * 获取文件信息
 *
 * ChapterII02/com.ling.learn0205.fileoperation.GetFileInfoTest.java
 *
 * author lingang
 *
 * createTime 2020-02-04 00:05:18
 *
 */
public class GetFileInfoTest {
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("D:/jad.exe");
		System.out.println(Files.exists(path));// 是否存在
		System.out.println(Files.isHidden(path));// 是否是隐藏文件
		System.out.println(Files.isReadable(path));// 是否可读
		System.out.println(Files.isWritable(path));// 是否可写
		System.out.println(Files.isExecutable(path));// 是否可执行
		System.out.println(Files.isRegularFile(path));// 是否常规文件
		System.out.println(Files.isDirectory(path));// 是否是目录
		System.out.println(Files.isSymbolicLink(path));// 是否是符号链接
		System.out.println(Files.size(path));// 返回文件长度(字节数)

		// 返回文件拥有者对象
		UserPrincipal up = Files.getOwner(path);
		System.out.println(up.getClass().getSimpleName());// Windows10是返回的User类对象
		System.out.println(up);

		// 返回文件属性对象
		BasicFileAttributes bfa = Files.readAttributes(path, BasicFileAttributes.class);
		System.out.println(bfa.isDirectory());
		System.out.println(bfa.isOther());
		System.out.println(bfa.isRegularFile());
		System.out.println(bfa.isSymbolicLink());
		System.out.println(bfa.size());
		System.out.println(bfa.creationTime());
		System.out.println(bfa.lastAccessTime());
		System.out.println(bfa.lastModifiedTime());

		// 如果文件系统兼容POSIX，可以使用下面方法，Win10系统会报：java.lang.UnsupportedOperationException
		PosixFileAttributes pfa = Files.readAttributes(path, PosixFileAttributes.class);
		System.out.println(pfa);
	}
}
