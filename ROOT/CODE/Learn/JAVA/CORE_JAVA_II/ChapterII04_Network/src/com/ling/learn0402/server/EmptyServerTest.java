package com.ling.learn0402.server;

import java.io.IOException;
import java.net.ServerSocket;

/**
 * 只监听端口，无法传输数据的服务器
 *
 * ChapterII04_Network/com.ling.learn0402.server.EmptyServerTest.java
 *
 * author lingang
 *
 * createTime 2020-02-19 02:16:00
 *
 */
public class EmptyServerTest {
	public static void main(String[] args) throws InterruptedException, IOException {
		try (ServerSocket ss = new ServerSocket(8036)) {// 监听8036端口，telnet操作会连接成功，但是不能传输数据)
			Thread.sleep(50000);// 要想保持监听端口，必须让线程运行着，而上面的操作不会让线程阻塞，所以让线程睡眠一段时间
		}
	}
}
