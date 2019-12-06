package com.ling.learn1303.serviceloader;

/**
 * 服务接口
 *
 * Chapter13/com.ling.learn1303.serviceloader.Cipher.java
 *
 * author lingang
 *
 * createTime 2019-12-06 20:34:43
 *
 */
public interface Cipher {
	byte[] encrypt(byte[] source, byte[] key);

	byte[] decrypt(byte[] source, byte[] key);

	int strength();
}
