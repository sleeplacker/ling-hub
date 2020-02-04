package com.ling.learn0206.rammapfile;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.zip.CRC32;

/**
 * 内存映射文件
 * 
 * 测试各种方式计算CRC校验和的速度
 * 
 * 由块到慢依次是： 1. 内存映射文件(104ms)， 2. 带缓冲区的输入流(381ms)， 3. 普通输入流(54775ms)， 4. 随机访问文件(59596ms)
 *
 * ChapterII02/com.ling.learn0206.rammapfile.RomMapFileTest.java
 *
 * author lingang
 *
 * createTime 2020-02-04 23:56:03
 *
 */
public class RomMapFileTest {
	public static void main(String[] args) throws IOException {
		Path file = Paths.get("D:/Java/jdk1.8.0_40/src.zip");
		long beginTime = System.currentTimeMillis();
		System.out.println("1. 使用普通输入流");
		System.out.println("CRC校验和为：" + checksumInputStream(file));
		System.out.println("耗时：" + (System.currentTimeMillis() - beginTime) + "ms");// 58613ms

		beginTime = System.currentTimeMillis();
		System.out.println("2. 使用带缓冲区输入流");
		System.out.println("CRC校验和为：" + checksumBufferedInputStream(file));
		System.out.println("耗时：" + (System.currentTimeMillis() - beginTime) + "ms");// 381ms

		beginTime = System.currentTimeMillis();
		System.out.println("3. 随机访问文件");
		System.out.println("CRC校验和为：" + checksumRandomAccessFile(file));
		System.out.println("耗时：" + (System.currentTimeMillis() - beginTime) + "ms");// 56702ms
		beginTime = System.currentTimeMillis();

		System.out.println("4. 内存映射文件");
		System.out.println("CRC校验和为：" + checksumRamMapFile(file));
		System.out.println("耗时：" + (System.currentTimeMillis() - beginTime) + "ms");// 104ms

	}

	// 通过InputStream计算CRC校验和
	public static long checksumInputStream(Path file) throws IOException {
		try (InputStream in = Files.newInputStream(file)) {
			CRC32 crc = new CRC32();
			int b;
			while ((b = in.read()) != -1) {
				crc.update(b);
			}
			return crc.getValue();
		}
	}

	// 通过BufferedInputStream计算CRC校验和
	public static long checksumBufferedInputStream(Path file) throws IOException {
		try (BufferedInputStream in = new BufferedInputStream(Files.newInputStream(file))) {
			CRC32 crc = new CRC32();
			int b;
			while ((b = in.read()) != -1) {
				crc.update(b);
			}
			return crc.getValue();
		}
	}

	// 通过RandomAccessFile计算CRC校验和
	public static long checksumRandomAccessFile(Path file) throws IOException {
		try (RandomAccessFile in = new RandomAccessFile(file.toFile(), "r")) {
			CRC32 crc = new CRC32();
			long size = in.length();
			for (long l = 0; l < size; ++l) {
				in.seek(l);
				crc.update(in.read());
			}
			return crc.getValue();
		}
	}

	// 通过FileChannel计算CRC校验和
	public static long checksumRamMapFile(Path file) throws IOException {
		try (FileChannel channel = FileChannel.open(file)) {// 打开通道
			CRC32 crc = new CRC32();
			long length = channel.size();// 获得文件长度
			MappedByteBuffer buffer = channel.map(FileChannel.MapMode.READ_ONLY, 0, length);// 将文件映射到内存中
			for (int i = 0; i < length; ++i) {
				crc.update(buffer.get());// 逐个字节计算校验和
			}
			return crc.getValue();
		}
	}
}
