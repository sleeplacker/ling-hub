package com.ling.learn1303.serviceloader.impl;

import com.ling.learn1303.serviceloader.Cipher;

/**
 * 服务提供者2-AES加密器
 *
 * Chapter13/com.ling.learn1303.serviceloader.impl.AESCipher.java
 *
 * author lingang
 *
 * createTime 2019-12-06 20:39:23 
 *
 */
public class AESCipher implements Cipher{

	@Override
	public byte[] encrypt(byte[] source, byte[] key) {
		System.out.println("AES加密");
		return null;
	}

	@Override
	public byte[] decrypt(byte[] source, byte[] key) {
		System.out.println("AES解密");
		return null;
	}

	@Override
	public int strength() {
		return 2;
	}

}
