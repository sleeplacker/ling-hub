package com.ling.learn0404.web;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

/**
 * URI和URL
 * 
 * URI比RUL更宽泛，URI包含但不限于URL和URN，URL的特殊之处在于它能够定位到数据，而URI不必
 * 上面说的应该是java中对URL和URI的解释，因为URL类有openStream方法可以获取数据，其实不限定java范围的话，URL和URI的区别还有争议
 *
 * ChapterII04_Network/com.ling.learn0404.web.URIAndURLTest.java
 *
 * author lingang
 *
 * createTime 2020-02-19 12:55:31
 *
 */
public class URIAndURLTest {
	public static void main(String[] args) throws IOException, URISyntaxException {
		// 使用URL可以获取数据
		URL url = new URL("http://www.baidu.com");// 这里的http不能少，否则不是正确的URL
		InputStream in = url.openStream();
		try (Scanner scan = new Scanner(in)) {
			while (scan.hasNext()) {
				System.out.println(scan.nextLine());
			}
		}

		// URI不能定位数据，只用作解析，有些类似Path类，java中对URI的格式几乎没有什么限制，只要是字符串就行
		URI uri = new URI("aaa");
		System.out.println(uri);
		uri = new URI("666");
		System.out.println(uri);
		uri = new URI("lingang@csii.com.cn");
		System.out.println(uri);

		// URI的各个组成部分
		uri = new URI("http://www.baidu.com/resources/static/img/food.png");
		System.out.println("Scheme=" + uri.getScheme());
		System.out.println("SchemeSpecificPart=" + uri.getSchemeSpecificPart());
		System.out.println("RawSchemeSpecificPar=" + uri.getRawSchemeSpecificPart());
		System.out.println("Authority=" + uri.getAuthority());
		System.out.println("UserInfo=" + uri.getUserInfo());
		System.out.println("Host=" + uri.getHost());
		System.out.println("Port=" + uri.getPort());
		System.out.println("Path=" + uri.getPath());
		System.out.println("Query=" + uri.getQuery());
		System.out.println("Fragment=" + uri.getFragment());// 等等

		// URI的组合和相对
		URI subUri = new URI("inner/innerer");
		System.out.println(uri.resolve(subUri));// 组合
		URI relativeUri = new URI("../../jsp/index.jsp");
		System.out.println(uri.resolve(relativeUri));// 回退并组合
		URI uri0 = new URI("http://www.baidu.com/resources");
		System.out.println(uri0.relativize(uri));// 相对，父路径.子路径
	}
}
