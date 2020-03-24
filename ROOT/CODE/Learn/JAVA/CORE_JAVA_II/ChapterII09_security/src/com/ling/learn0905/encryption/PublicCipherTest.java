package com.ling.learn0905.encryption;

import java.security.Key;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * 公钥密码
 *
 * ChapterII09_security/com.ling.learn0905.encryption.PublicCipherTest.java
 *
 * author lingang
 *
 * createTime 2020-03-24 22:14:24
 *
 */
public class PublicCipherTest {
	public static void main(String[] args) throws Exception {
		// 明文
		String plain = "公开钥密码系统或非对称密码系统是实现和保障信息安全的一种重要的方式";

		// 生成密钥对
		KeyPairGenerator pairgen = KeyPairGenerator.getInstance("RSA");
		SecureRandom random = new SecureRandom();
		pairgen.initialize(2048, random);
		KeyPair keyPair = pairgen.generateKeyPair();
		PublicKey puk = keyPair.getPublic();
		PrivateKey prk = keyPair.getPrivate();
		System.out.println("公钥内容：\n\t" + "exponent : " + ((RSAPublicKey) puk).getPublicExponent() + "\n\tmodulus : "
				+ ((RSAPublicKey) puk).getModulus());
		System.out.println("私钥内容：\n\t" + "exponent : " + ((RSAPrivateKey) prk).getPrivateExponent() + "\n\tmodulus : "
				+ ((RSAPrivateKey) prk).getModulus());

		// RSA加密
		Cipher cipher = Cipher.getInstance("RSA");
		cipher.init(Cipher.ENCRYPT_MODE, puk);
		System.out.println("块大小为：" + cipher.getBlockSize());// 非分组加密，块大小为0
		byte[] result = cipher.doFinal(plain.getBytes());// 非分组加密直接调用doFinal方法就行
		System.out.println("加密后长度：" + result.length);
		System.out.println("加密结果：" + new String(result));

		// RSA解密
		cipher.init(Cipher.DECRYPT_MODE, prk);
		result = cipher.doFinal(result);// 非分组加密直接调用doFinal方法就行
		System.out.println("解密结果：" + new String(result));

		// 生成AES秘钥
		Key key = new SecretKeySpec("1234567890123456".getBytes(), "AES");
		System.out.println("\n秘钥内容：" + Arrays.toString(key.getEncoded()));

		// RAS加密AES秘钥
		cipher.init(Cipher.WRAP_MODE, puk);
		result = cipher.wrap(key);
		System.out.println("加密后的秘钥长度：" + result.length);
		System.out.println("加密后的秘钥内容：" + new String(result));

		// RSA解密AES被加密的秘钥
		cipher.init(Cipher.UNWRAP_MODE, prk);
		Key resultKey = cipher.unwrap(result, "AES", Cipher.SECRET_KEY);
		System.out.println("解密后的秘钥内容：" + Arrays.toString(resultKey.getEncoded()));
	}
}
