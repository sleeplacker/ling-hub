package com.ling.learn1303.serviceloader;

import java.util.ServiceLoader;

/**
 * 服务加载器，步骤如下：
 * 
 * 1. 定义接口如：Cipher
 * 
 * 2. 定义1个或多个实现类DESCipher,AESCipher...
 * 
 * 3. 定义文件：src/META-INF/services/com.ling.learn1303.serviceloader.Cipher，
 * 文件位置为src/META-INF/services/，文件名必须是接口的全限定名
 * 
 * 4. 定义ServiceLoader服务加载器
 * 
 * 5. 遍历服务加载器取出需要的服务
 *
 * Chapter13/com.ling.learn1303.serviceloader.ServiceLoaderTest.java
 *
 * author lingang
 *
 * createTime 2019-12-06 20:30:51
 *
 */
public class ServiceLoaderTest {
	public static void main(String[] args) {
		ServiceLoader<Cipher> cipherLoader = ServiceLoader.<Cipher>load(Cipher.class);
		for (Cipher cipher : cipherLoader) {
			System.out.println(cipher);
		}
	}
}
