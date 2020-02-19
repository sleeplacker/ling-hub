package com.ling.learn0404.web;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 * 使用URLConnection
 * 
 * URL类只能获取输入流，如果想从web资源获得更多信息，应该使用URLConnection
 * 
 * URLConnection除了本例中列举的方法，还有很多其他有用方法，见218页
 *
 * ChapterII04_Network/com.ling.learn0404.web.URLConnectionTest.java
 *
 * author lingang
 *
 * createTime 2020-02-19 15:30:26
 *
 */
public class URLConnectionTest {
	public static void main(String[] args) throws IOException {
		URL url = new URL("http://www.baidu.com");
		URLConnection connection = url.openConnection();
		System.out.println(connection.getDoInput());// 默认打开输入流
		System.out.println(connection.getDoOutput());// 默认关闭输出流
		connection.setDoOutput(true);// 可以设置开启输出流

		// 添加密码认证
		String userAndPassword = "ling:123456";
		Base64.Encoder encoder = Base64.getEncoder();
		String encodedData = encoder.encodeToString(userAndPassword.getBytes(StandardCharsets.UTF_8));
		connection.setRequestProperty("Authorization", "Basic " + encodedData);

		connection.connect();// 与服务器建立链接，此后就可以读取头信息

		// 获取并打印头信息
		System.out.println("************headers<************");
		Map<String, List<String>> headers = connection.getHeaderFields();
		for (Entry<String, List<String>> entry : headers.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		System.out.println("************headers>************");
		// 直接调用方法获取常用头信息

		System.out.println("************header method<************");
		System.out.println("getContentType = " + connection.getContentType());
		System.out.println("getContentLength = " + connection.getContentLength());
		System.out.println("getContentEncoding = " + connection.getContentEncoding());
		System.out.println("getDate = " + connection.getDate());
		System.out.println("getExpiration = " + connection.getExpiration());
		System.out.println("getLastModified = " + connection.getLastModified());
		System.out.println("************header method>************");
		
		String encoding = connection.getContentEncoding();
		if(encoding==null)
			encoding = "UTF-8";//默认编码取UTF-8
		try(Scanner in = new Scanner(connection.getInputStream(),encoding)){//使用头信息指定的编码格式来读取输入流
			while (in.hasNext()) {
				System.out.println(in.nextLine());
			}
		}
	}
}
