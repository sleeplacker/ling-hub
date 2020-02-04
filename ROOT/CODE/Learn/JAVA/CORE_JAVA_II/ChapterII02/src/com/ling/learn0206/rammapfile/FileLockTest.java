package com.ling.learn0206.rammapfile;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

/**
 * 文件加锁机制
 * 
 * 1. 文件锁和线程锁不一样，多个线程锁定同一个文件会报java.nio.channels.OverlappingFileLockException，
 * 多个进程锁定同一个文件才会阻塞
 * 
 * 2. 文件加锁机制在不同操作系统中有不同表现
 *
 * ChapterII02/com.ling.learn0206.rammapfile.FileLockTest.java
 *
 * author lingang
 *
 * createTime 2020-02-05 00:36:53
 *
 */
public class FileLockTest {
	public static void main(String[] args) throws IOException, InterruptedException {
		Path path = Paths.get("D:/filetest/filelocktest");
		Files.deleteIfExists(path);
		Files.createFile(path);
		// 下面启动两条线程往同一文件写内容，只有一个能写入成功，而另一个线程会报java.nio.channels.OverlappingFileLockException
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// try {
		// fileLockTest("11111");
		// } catch (IOException e) {
		// e.printStackTrace();
		// } catch (InterruptedException e) {
		// e.printStackTrace();
		// }
		// }
		// }).start();
		// new Thread(new Runnable() {
		//
		// @Override
		// public void run() {
		// try {
		// fileLockTest("22222");
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		// }).start();

		// 应该执行该程序两次，win10系统的处理是，第二次执行时会报java.nio.file.AccessDeniedException，而且锁定期间，在windows中修改文件也会报错
		long startTime = System.currentTimeMillis();
		fileLockTest("11111");
		System.out.println((System.currentTimeMillis() - startTime) + "ms");
	}

	public static void fileLockTest(String content) throws IOException, InterruptedException {
		Path path = Paths.get("D:/filetest/filelocktest");
		FileChannel channel = FileChannel.open((path), StandardOpenOption.READ, StandardOpenOption.WRITE);
		FileLock lock = channel.lock();// 创建普通阻塞锁，文件已经被其他线程锁定时会阻塞
		// lock = channel.lock(0, 1024, false);// 指定锁定文件的范围，且其他线程不能读
		// lock = channel.lock(0, 1024, true);// 指定锁定文件的范围，且其他线程可以读
		// lock = channel.tryLock();// 创建普通非阻塞锁，文件已经被其他线程锁定时会返回null
		// lock = channel.tryLock(0, 1024, false);
		// lock = channel.tryLock(0, 1024, true);
		ByteBuffer bb = channel.map(FileChannel.MapMode.READ_WRITE, 0, 1024);
		Thread.sleep(20000);
		bb.put(content.getBytes());
		lock.close();
	}
}
