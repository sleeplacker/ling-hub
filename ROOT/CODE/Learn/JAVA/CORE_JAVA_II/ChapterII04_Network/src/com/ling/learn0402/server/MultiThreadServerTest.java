package com.ling.learn0402.server;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * 可同时服务于多个客户端的服务器
 *
 * ChapterII04_Network/com.ling.learn0402.server.MultiThreadServerTest.java
 *
 * author lingang
 *
 * createTime 2020-02-19 02:30:27
 *
 */
public class MultiThreadServerTest {
	public static void main(String[] args) throws IOException {
		try (ServerSocket ss = new ServerSocket(8036)) {// 创建Socket服务器
			int index = 0;
			while (true) {
				Socket s = ss.accept();// 等待连接
				System.out.println("Spawning " + (++index));
				SocketHandler sh = new SocketHandler(s);
				Thread t = new Thread(sh);// 当有客户端连接后，创建一个新线程，以便继续accept另一个连接的到来
				t.start();

			}
		}
	}
}

class SocketHandler implements Runnable {
	private Socket socket;

	public SocketHandler(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try (InputStream in = socket.getInputStream(); OutputStream out = socket.getOutputStream();) {
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
				socket.close();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
