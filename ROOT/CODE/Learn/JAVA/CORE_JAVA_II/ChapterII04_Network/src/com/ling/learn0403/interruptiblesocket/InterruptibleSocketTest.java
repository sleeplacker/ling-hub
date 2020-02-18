package com.ling.learn0403.interruptiblesocket;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.channels.SocketChannel;
import java.util.Scanner;

/**
 * 可中断的套接字
 * 
 * 测试前先运行MultiThreadServerTest.java
 *
 * ChapterII04_Network/com.ling.learn0403.interruptiblesocket.InterruptibleSocketTest.java
 *
 * author lingang
 *
 * createTime 2020-02-19 03:30:11
 *
 */
public class InterruptibleSocketTest {
	public static void main(String[] args) throws IOException {
		interruptibleSocketTest();// 可中断socket，单独运行这一行时，程序不会阻塞
		normalSocketTest();// 普通socket连接，单独运行这一行时，程序会阻塞
	}

	private static void normalSocketTest() throws UnknownHostException, IOException {
		try (Socket s = new Socket("localhost", 8036);
				InputStream in = s.getInputStream();
				Scanner scan = new Scanner(in)) {
			while (scan.hasNext()) {// 普通Socket的输入流或输出流操作过程中会阻塞线程
				System.out.println(scan.nextLine());
				Thread.currentThread().interrupt();
			}
		}
	}

	private static void interruptibleSocketTest() throws IOException {
		SocketChannel channel = SocketChannel.open(new InetSocketAddress("localhost", 8036));
		try (Scanner in = new Scanner(channel, "UTF-8");) {
			while (in.hasNext()) {//由于通道没有与之关联的流，所以不会导致线程阻塞
				System.out.println(in.nextLine());
				Thread.currentThread().interrupt();
			}
		}
	}
}
