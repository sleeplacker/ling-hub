package com.ling.learn1303.serviceloader.impl;

import com.ling.learn1303.serviceloader.Cipher;

/**
 * 服务提供者1-DES加密器
 *
 * Chapter13/com.ling.learn1303.serviceloader.impl.DesCipher.java
 *
 * author lingang
 *
 * createTime 2019-12-06 20:38:05 
 *
 */
public class DESCipher implements Cipher{

	@Override
	public byte[] encrypt(byte[] source, byte[] key) {
		System.out.println("DES加密");
		return null;
	}

	@Override
	public byte[] decrypt(byte[] source, byte[] key) {
		System.out.println("DES解密");
		return null;
	}

	@Override
	public int strength() {
		return 1;
	}

}
