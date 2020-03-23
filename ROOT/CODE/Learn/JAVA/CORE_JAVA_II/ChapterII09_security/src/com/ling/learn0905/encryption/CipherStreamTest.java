package com.ling.learn0905.encryption;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.CipherOutputStream;
import javax.crypto.spec.SecretKeySpec;

/**
 * 密码流，可以简化加解密过程，无需调用update和doFinal方法
 *
 * ChapterII09_security/com.ling.learn0905.encryption.CipherStreamTest.java
 *
 * author lingang
 *
 * createTime 2020-03-24 01:38:36
 *
 */
public class CipherStreamTest {
	public static void main(String[] args) throws Exception {
		// 明文
		String plain = "公开钥密码系统或非对称密码系统是实现和保障信息安全的一种重要的方式";
		ByteArrayInputStream bin = new ByteArrayInputStream(plain.getBytes());

		// 秘钥
		Key key = new SecretKeySpec("1234567890123456".getBytes(), "AES");

		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");// 貌似密码流只能使用NoPadding
		cipher.init(Cipher.ENCRYPT_MODE, key);

		// 密码输出流-既可用于加密也可用于解密
		ByteArrayOutputStream bao = new ByteArrayOutputStream();
		CipherOutputStream cout = new CipherOutputStream(bao, cipher);
		byte[] block = new byte[cipher.getBlockSize()];
		int len = bin.read(block);
		while (len != -1) {
			cout.write(block, 0, len);
			len = bin.read(block);
		}
		cout.flush();

		System.out.println(bao.toByteArray().length);
		String encryptedContent = new String(bao.toByteArray());
		System.out.println("加密结果 : " + encryptedContent);

		// 密码输人流-既可用于加密也可用于解密
		cipher.init(Cipher.DECRYPT_MODE, key);
		ByteArrayInputStream bin2 = new ByteArrayInputStream(bao.toByteArray());
		ByteArrayOutputStream bao2 = new ByteArrayOutputStream();
		CipherInputStream cin = new CipherInputStream(bin2, cipher);
		block = new byte[cipher.getBlockSize()];
		len = cin.read(block);
		while (len != -1) {
			bao2.write(block, 0, len);
			len = cin.read(block);
		}
		System.out.println("解密结果 : " + new String(bao2.toByteArray()));

		cout.close();
		cin.close();
	}

}
