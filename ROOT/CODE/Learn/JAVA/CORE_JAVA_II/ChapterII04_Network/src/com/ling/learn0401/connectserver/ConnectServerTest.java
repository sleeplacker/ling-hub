package com.ling.learn0401.connectserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 * 连接到服务器
 *
 * ChapterII04_Network/com.ling.learn0401.connectserver.ConnectServerTest.java
 *
 * author lingang
 *
 * createTime 2020-02-18 20:36:27
 *
 */
public class ConnectServerTest {
	public static void main(String[] args) throws UnknownHostException, IOException {
		// 连接方式一：不设置超时
		long start = System.currentTimeMillis();
		try (Socket s = new Socket("time-a.nist.gov", 13)) {
			System.out.println("建立连接耗时：" + (System.currentTimeMillis() - start) + "ms");
			start = System.currentTimeMillis();
			Scanner in = new Scanner(s.getInputStream(), "UTF-8");
			while (in.hasNext()) {
				String line = in.nextLine();
				System.out.println("|" + line);
			}
			System.out.println("服务器响应耗时：" + (System.currentTimeMillis() - start) + "ms");
			in.close();
		}
		// 连接方式二：使用setSoTimeout方法设置超时，如果超过这个时间，不会再等待服务端返回，但是测试的时候没有抛错
		start = System.currentTimeMillis();
		Socket s = null;
		try {
			s = new Socket("time-a.nist.gov", 13);
			System.out.println("建立连接耗时：" + (System.currentTimeMillis() - start) + "ms");
			start = System.currentTimeMillis();
			s.setSoTimeout(100);// 100ms超时
			Scanner in = new Scanner(s.getInputStream(), "UTF-8");
			while (in.hasNext()) {
				String line = in.nextLine();
				System.out.println("|" + line);
			}
			System.out.println("服务器响应耗时：" + (System.currentTimeMillis() - start) + "ms");
			in.close();
		} catch (Exception e) {
			// 建立连接时如果服务器连不上会等21秒左右
			System.out.println("建立连接超时耗时：" + (System.currentTimeMillis() - start) + "ms");
			// throw e;
		}

		// 连接方式三：建立连接时也设置超时，那会在超时时间到达时抛错：java.net.SocketTimeoutException:
		// connect timed out
		s = new Socket();
		s.connect(new InetSocketAddress("192.168.8.8", 80), 5000);
		s.close();
	}
}
