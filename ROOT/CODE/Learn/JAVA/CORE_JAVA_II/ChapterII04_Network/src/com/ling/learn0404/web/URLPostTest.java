package com.ling.learn0404.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * 使用post方法访问web服务器
 *
 * ChapterII04_Network/com.ling.learn0404.web.URLPostTest.java
 *
 * author lingang
 *
 * createTime 2020-02-19 20:20:48
 *
 */
public class URLPostTest {
	public static void main(String[] args) throws IOException {
		// URL url = new URL("http://www.baidu.com");
		URL url = new URL("http://www.baidu.com/xx.html");// 当/输入这个URL时，会重定向到错误页面
		// URL url = new URL("https://www.zhihu.com/5234523452345");//
		// 当输入这个URL时，会出现405响应码
		// URL url = new URL("http://192.168.3.93:9104/portal/");//
		// 自己启的web服务，可以看到服务端确实收到了请求参数
		Map<String, String> params = new HashMap<String, String>();// 要提交的参数
		params.put("name", "ling");
		params.put("pwd", "123");
		CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));// 设置重定向的时候带上所有cookie信息
		String respContent = doPost(url, params, 3);// 限制只能重定向3次
		System.out.println(respContent);
	}

	private static String doPost(URL url, Map<String, String> params, int redirectCnt) throws IOException {
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		System.out.println(connection.getRequestMethod());// GET，连接刚创建的时候是GET方式
		connection.setDoOutput(true);// 可以设置开启输出流
		boolean firstParam = true;
		try (PrintWriter out = new PrintWriter(connection.getOutputStream())) {
			for (String key : params.keySet()) {
				if (firstParam)
					firstParam = false;
				else
					out.print("&");
				out.print(key);
				out.print("=");
				out.print(URLEncoder.encode(params.get(key), "UTF-8"));// 对参数值进行编码
			}
		}
		System.out.println(connection.getRequestMethod());// POST，当调用了getOutputStream后，连接变成了POST方式
		String encoding = connection.getContentEncoding();
		if (encoding == null)
			encoding = "UTF-8";

		connection.setInstanceFollowRedirects(false);// 设置不自动重定向
		int respCode = connection.getResponseCode();
		if (respCode == HttpURLConnection.HTTP_MOVED_PERM || respCode == HttpURLConnection.HTTP_MOVED_TEMP
				|| respCode == HttpURLConnection.HTTP_SEE_OTHER) {// 需要重定向的响应码
			String location = connection.getHeaderField("Location");//
			// 从返回头信息中获取要冲定下的地址
			if (location != null && redirectCnt >= 0) {
				URL base = connection.getURL();
				connection.disconnect();
				--redirectCnt;
				return doPost(new URL(base, location), params, redirectCnt);// 手动重定向
			}
		}

		StringBuffer resp = new StringBuffer();
		try (Scanner in = new Scanner(connection.getInputStream(), encoding)) {
			// 读取返回数据
			while (in.hasNext()) {
				resp.append(in.nextLine());
				resp.append("\n ");
			}
		} catch (IOException e) {// 当出现404或者405这类找不到资源的响应码时，会抛出IO异常，但是可以通过getErrorStream方法获取错误页面的内容
			try (Scanner in = new Scanner(connection.getErrorStream(), encoding)) {
				// 读取返回数据
				while (in.hasNext()) {
					resp.append(in.nextLine());
					resp.append("\n ");
					System.out.println(resp.toString());
				}
			}
			e.printStackTrace();
		}
		return resp.toString();
	}
}
