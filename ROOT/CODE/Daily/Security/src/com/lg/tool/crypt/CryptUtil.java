package com.lg.tool.crypt;

import java.io.FileInputStream;
import java.security.KeyFactory;
import java.security.KeyStore;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

import javax.crypto.Cipher;

/**
 * 证书工具类
 * 
 * 实现功能包括：
 * 
 * 1. 获取秘钥库KeyStore对象
 * 
 * 2. 从KeyStore对象获取私钥
 * 
 * 3. 从文件读取证书(也可以从KeyStore对象读取证书)
 * 
 * 4. 从证书读取公钥(也可以从KeyStore对象读取公钥)
 * 
 * 5. 私钥签名
 * 
 * 6. 证书验签
 * 
 *
 * Security/com.lg.tool.certificate.CertificateUtil.java
 *
 * author lingang
 *
 * createTime 2020-08-21 01:06:03
 *
 */
public class CryptUtil {
	public static final String KEY_ALGORITHM = "RSA";

	public static void main(String[] args) throws Exception {
		/* 签名/验签 < */
		// 客户端-获取秘钥库
		KeyStore ks = getKeyStore("D:/openssl/certs/client.p12", "123456");
		// 客户端-获取私钥
		PrivateKey privateKey = getPrivateKey(ks, "1", "123456");// PKCS12秘钥库中别名1为私钥
		// 客户端-对数据签名
		String plain = "Hello , CA";
		byte[] signData = sign(plain.getBytes(), privateKey);
		String signStr = Base64.getEncoder().encodeToString(signData);
		System.out.println("客户端明文：" + plain);
		System.out.println("客户端签名：" + signStr);

		// 服务器-加载客户端证书
		X509Certificate cert = getCertificate("D:/openssl/certs/cert.crt");
		System.out.println("签名算法：" + cert.getSigAlgName());
		System.out.println("服务器接收明文：" + plain);
		System.out.println("服务器验签结果：" + verify(plain.getBytes(), Base64.getDecoder().decode(signStr), cert));
		plain = "Hello , CA!";// 如果明文被篡改，则验签会失败
		System.out.println("服务器接收明文：" + plain);
		System.out.println("服务器验签结果：" + verify(plain.getBytes(), Base64.getDecoder().decode(signStr), cert));
		/* 签名/验签 > */

		/* 加密/解密 < */
		// 可以从证书中获取公钥
		System.out.println("证书中的公钥：" + getPublicKey(cert));
		// 客户端-获取私钥
		plain = "Hello , RSA";// 加密明文
		PublicKey pubk = getPublicKey(getCertificate("D:/openssl/certs/cert.crt"));
		byte[] pubkb = pubk.getEncoded();
		byte[] enc = encryptByPublicKey(plain.getBytes(), pubkb);
		// 解密结果
		System.out.println(
				"私钥解密结果：" + new String(decryptByPrivateKey(enc, getPrivateKey(ks, "1", "123456").getEncoded())));
		/* 加密/解密 > */
	}

	/**
	 * 获取秘钥库
	 * 
	 * @param keyStorePath
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static KeyStore getKeyStore(String keyStorePath, String password) throws Exception {
		KeyStore ks = KeyStore.getInstance("PKCS12");// 使用PKCS12类型的秘钥库
		try (FileInputStream fis = new FileInputStream(keyStorePath)) {
			ks.load(fis, password.toCharArray());
			return ks;
		}
	}

	/**
	 * 使用私钥对数据签名
	 * 
	 * @param data-要发送的数据
	 * @param privateKey-用于签名的私钥
	 * @return
	 * @throws Exception
	 */
	public static byte[] sign(byte[] data, PrivateKey privateKey) throws Exception {
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initSign(privateKey);
		signature.update(data);
		return signature.sign();
	}

	/**
	 * 使用证书验证签名
	 * 
	 * @param data-接收到的数据
	 * @param sign-接收到的签名
	 * @param cert-证书
	 * @return
	 * @throws Exception
	 */
	public static boolean verify(byte[] data, byte[] sign, X509Certificate cert) throws Exception {
		Signature signature = Signature.getInstance(cert.getSigAlgName());// 可以从X509Certificate对象中获取签名方法
		signature.initVerify(cert);
		signature.update(data);
		return signature.verify(sign);
	}

	/**
	 * 从文件读取证书
	 * 
	 * @param certPath
	 * @return
	 * @throws Exception
	 */
	public static X509Certificate getCertificate(String certPath) throws Exception {
		CertificateFactory cf = CertificateFactory.getInstance("X.509");
		try (FileInputStream fis = new FileInputStream(certPath)) {
			return (X509Certificate) cf.generateCertificate(fis);
		}
	}

	/**
	 * 从证书中获取公钥
	 * 
	 * @param cert
	 * @return
	 */
	public static PublicKey getPublicKey(X509Certificate cert) {
		return cert.getPublicKey();
	}

	/**
	 * 从秘钥库中获取私钥
	 * 
	 * @param ks
	 * @param alias
	 * @param password
	 * @return
	 * @throws Exception
	 */
	public static PrivateKey getPrivateKey(KeyStore ks, String alias, String password) throws Exception {
		return (PrivateKey) ks.getKey(alias, password.toCharArray());
	}

	/**
	 * RSA私钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}

	/**
	 * RSA公钥解密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptByPublicKey(byte[] data, byte[] key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.DECRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * RSA公钥加密
	 * 
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPublicKey(byte[] data, byte[] key) throws Exception {
		X509EncodedKeySpec keySpec = new X509EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PublicKey publicKey = keyFactory.generatePublic(keySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, publicKey);
		return cipher.doFinal(data);
	}

	/**
	 * RSA私钥加密
	 * @param data
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] encryptByPrivateKey(byte[] data, byte[] key) throws Exception {
		PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(key);
		KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
		PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
		Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
		cipher.init(Cipher.ENCRYPT_MODE, privateKey);
		return cipher.doFinal(data);
	}
}
