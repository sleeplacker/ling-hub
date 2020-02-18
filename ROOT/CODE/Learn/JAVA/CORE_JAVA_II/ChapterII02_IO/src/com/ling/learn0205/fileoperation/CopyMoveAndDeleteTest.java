package com.ling.learn0205.fileoperation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * 复制、移动和删除文件
 *
 * ChapterII02/com.ling.learn0205.fileoperation.CopyMoveAndDeleteTest.java
 *
 * author lingang
 *
 * createTime 2020-02-03 01:07:01
 *
 */
public class CopyMoveAndDeleteTest {
	public static void main(String[] args) throws IOException {
		/* 文件复制：如果要复制到的目录不存在，不会自动创建，而是抛错 */
		Path srcPath = Paths.get("D:/filetest/file1");
		Path destPath = Paths.get("D:/filetest/dir/file1_copy");
		// Files.copy(srcPath, destPath);// 如果目标位置已有同名文件，会抛错，除非指定第三个参数为替换
		// REPLACE_EXISTING为替换已有文件，COPY_ATTRIBUTES为复制文件属性
		Files.copy(srcPath, destPath, StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.COPY_ATTRIBUTES);
		Files.copy(Files.newInputStream(Paths.get("D:/filetest/file1")), destPath, StandardCopyOption.REPLACE_EXISTING);// 也可以将输入流复制到目标文件
		Files.copy(srcPath, Files.newOutputStream(Paths.get("D:/filetest/file1")));// 或者将源文件复制到输出流

		/* 文件移动：文件移动应该保证要么移动操作完成，要么源文件保存在原位置，所以要指定ATOMIC_MOVE参数 */
		Files.move(srcPath, destPath, StandardCopyOption.ATOMIC_MOVE);// 实践证明，ATOMIC_MOVE参数也具有REPLACE_EXISTING的功效

		Files.createFile(srcPath);// 创建移动掉的文件，以便后续操作
		/* 文件删除 */
		Files.deleteIfExists(destPath);// 为了防止删除不存在的文件，应该调用deleteIfExists方法
	}
}
