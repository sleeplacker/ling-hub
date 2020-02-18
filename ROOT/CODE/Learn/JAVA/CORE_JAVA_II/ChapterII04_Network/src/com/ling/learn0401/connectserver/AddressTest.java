package com.ling.learn0401.connectserver;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * 因特网地址
 *
 * ChapterII04_Network/com.ling.learn0401.connectserver.AddressTest.java
 *
 * author lingang
 *
 * createTime 2020-02-18 21:35:20
 *
 */
public class AddressTest {
	public static void main(String[] args) throws UnknownHostException {
		// 通过域名获得IP地址，但是通过IP地址获得域名的方法没有
		InetAddress address = InetAddress.getByName("time-a.nist.gov");
		System.out.println(address.getHostAddress());
		System.out.println(Arrays.toString(address.getAddress()));

		// 通过1个域名获得多个IP地址，针对帶负载均衡服务器，当前时间2020-2-18 21:44:06暂时找不到这种服务器
		InetAddress[] addressArr = InetAddress.getAllByName("www.google.com");
		Stream.of(addressArr).forEach(addr -> System.out.println(addr.getHostAddress()));

		// 获取回环地址
		address = InetAddress.getByName("localhost");
		System.out.println(address.getHostAddress());

		// 获取本地主机的地址
		address = InetAddress.getLocalHost();
		System.out.println(address.getHostAddress());
	}
}
