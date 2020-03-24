package com.ling.learn0905.encryption;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;

/**
 * RSA签名和验签
 *
 * ChapterII09_security/com.ling.learn0905.encryption.SignAndVerifyTest.java
 *
 * author lingang
 *
 * createTime 2020-03-25 01:44:12
 *
 */
public class SignAndVerifyTest {
	public static void main(String[] args) throws Exception {
		// 明文
		String plain = "公开钥密码系统或非对称密码系统是实现和保障信息安全的一种重要的方式";

		// 生成RSA公私钥对
		KeyPairGenerator kpgen = KeyPairGenerator.getInstance("RSA");
		KeyPair kp = kpgen.generateKeyPair();
		PublicKey puk = kp.getPublic();
		PrivateKey prk = kp.getPrivate();

		// 签名
		Signature signature = Signature.getInstance("SHA256WithRSA");
		signature.initSign(prk);
		signature.update(plain.getBytes());
		byte[] signBytes = signature.sign();
		System.out.println("签名结果：" + new String(signBytes));

		// 验签
		signature.initVerify(puk);
		signature.update(plain.getBytes());
		System.out.println("验签结果：" + signature.verify(signBytes));

		// 将原明文进行篡改后再验签
		plain = "公开钥密码系统或非对称密码系统是实现和保障信息安全的一种重要的方式1";
		signature.update(plain.getBytes());
		System.out.println("验签结果：" + signature.verify(signBytes));

	}
}
