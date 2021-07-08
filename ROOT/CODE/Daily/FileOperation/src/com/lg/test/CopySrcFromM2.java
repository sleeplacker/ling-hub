package com.lg.test;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

/**
 * 从仓库复制出依赖包对应的源文件
 *
 * RedisPractices/clients.redission.CopySrcFromM2.java
 *
 * author lingang
 *
 * createTime 2021-07-08 16:47:28
 *
 */
public class CopySrcFromM2 {
	// 依赖包所在目录
	private static String DEPENDENCY_PATH = "/home/lingang/redis-servers/redission/target/dependency/";
	// 仓库所在目录
	private static String REPOSITORY_PATH = "/home/lingang/.m2/";

	public static void main(String[] args) throws Exception {
		List<String> list = new ArrayList<String>();
		Files.walkFileTree(Paths.get(DEPENDENCY_PATH), new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
//						System.out.println(file.getFileName());
				list.add(file.getFileName().toString());
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return FileVisitResult.SKIP_SUBTREE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}
		});
		
		System.out.println("数量："+list.size());

		Files.walkFileTree(Paths.get(REPOSITORY_PATH), new FileVisitor<Path>() {

			@Override
			public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
				String name = file.getFileName().toString();
				if (list.contains(name)) {
					String absn = file.toAbsolutePath().toString();
					String srcn = absn.substring(0, absn.length() - 4) + "-sources.jar";
					System.out.println(srcn + ": " + Files.exists(Paths.get(srcn)));
					Files.copy(Paths.get(srcn), Paths.get("/home/lingang/redis-servers/redission/target/dependency/src/"
							+ Paths.get(srcn).getFileName()));
				}

				return FileVisitResult.CONTINUE;
			}

			@Override
			public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
				return FileVisitResult.SKIP_SUBTREE;
			}

			@Override
			public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
				return FileVisitResult.CONTINUE;
			}
		});
	}
}
