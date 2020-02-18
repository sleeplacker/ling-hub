package com.ling.learn0402.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 单线程服务器
 * 
 * accept方法会阻塞线程，当有客户端请求时，线程继续执行，线程执行完成后，服务停止，连接关闭
 *
 * ChapterII04_Network/com.ling.learn0402.server.SingleThreadServerTest.java
 *
 * author lingang
 *
 * createTime 2020-02-19 02:23:57
 *
 */
public class SingleThreadServerTest {
	public static void main(String[] args) throws IOException {
		try (ServerSocket ss = new ServerSocket(8036)) {// 创建Socket服务器
			try (Socket s = ss.accept()) {// 等待客户端连接
				InputStream in = s.getInputStream();
				OutputStream out = s.getOutputStream();
				try (Scanner scan = new Scanner(in, "UTF-8")) {// 接收客户端输入
					PrintWriter writer = new PrintWriter(out, true);
					writer.write("Hello,input word and you will get uppercase version, input BYE to stop\r\n");
					writer.flush();
					while (scan.hasNext()) {// 该循环结束后服务器将会停止
						String content = scan.nextLine().toUpperCase();
						if ("BYE".equals(content))
							break;
						System.out.println(content);
						writer.write("Echo: " + content + "\r\n");
						writer.flush();
					}
				}
			}
		}
	}
}
