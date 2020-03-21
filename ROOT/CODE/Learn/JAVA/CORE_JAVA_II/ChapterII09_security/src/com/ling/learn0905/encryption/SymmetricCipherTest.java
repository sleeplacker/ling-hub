package com.ling.learn0905.encryption;

import java.io.ByteArrayOutputStream;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

/**
 * 对称密码的加密和解密
 *
 * ChapterII09_security/com.ling.learn0905.encryption.SymmetricCipherTest.java
 *
 * author lingang
 *
 * createTime 2020-03-21 23:22:45
 *
 */
public class SymmetricCipherTest {
	public static void main(String[] args) throws Exception {
		// 明文
		String plain = "公开钥密码系统或非对称密码系统是实现和保障信息安全的一种重要的方式";
		byte[] plainBytes = plain.getBytes();

		// 产生秘钥
		Key key = createKey();
		System.out.println("秘钥信息：" + key.getAlgorithm() + "/" + key.getFormat());

		// 加密
		System.out.println("\n加密：");
		byte[] encryptedBytes = encryptOrDecrypt(plainBytes, key, Cipher.ENCRYPT_MODE);// Cipher.ENCRYPT_MODE为加密模式
		System.out.println("加密结果：" + new String(encryptedBytes));

		// 解密
		System.out.println("\n解密：");
		byte[] decryptedBytes = encryptOrDecrypt(encryptedBytes, key, Cipher.DECRYPT_MODE);// Cipher.DECRYPT_MODE为解密模式
		System.out.println("解密结果：" + new String(decryptedBytes));

	}

	private static byte[] encryptOrDecrypt(byte[] plainBytes, Key key, int mode) throws Exception {
		try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
			// 获得密码对象-指定算法
			Cipher cipher = Cipher.getInstance("AES");
			// 获得密码对象-指定算法和提供商，JDK中是由名为SunJCE的提供商提供密码的
			cipher = Cipher.getInstance("AES", "SunJCE");
			// 初始化密码对象
			cipher.init(mode, key);
			// 获取加密块大小
			int bolckSize = cipher.getBlockSize();
			System.out.println("输入块大小：" + bolckSize);
			int outputSize = cipher.getOutputSize(bolckSize);
			System.out.println("输出块大小：" + outputSize);

			// 已处理明文内容的位置
			int handledIndex = 0;
			while (handledIndex <= plainBytes.length - bolckSize) {
				// 分块读入明文数据
				byte[] inBytes = Arrays.copyOfRange(plainBytes, handledIndex, handledIndex + bolckSize);
				// 对数据块进行转换
				byte[] outBytes = cipher.update(inBytes, 0, bolckSize);
				// 将转换结果写出到字节流
				baos.write(outBytes);
				// 移动已处理位置
				handledIndex += bolckSize;
			}
			// 转换输入的最后一个数据块
			byte[] lastOutByte;
			if (handledIndex != plainBytes.length)// 如果前面还有剩下的不满一个输入块的数据，那需要在doFinal方法中传入并转换这一块数据
				lastOutByte = cipher.doFinal(Arrays.copyOfRange(plainBytes, handledIndex, plainBytes.length));
			else// 如果前面刚好将所有输入数据都转换完，则直接调用doFinal方法
				lastOutByte = cipher.doFinal();
			baos.write(lastOutByte);
			return baos.toByteArray();
		}

	}

	// 生成对称加密秘钥
	private static Key createKey() throws NoSuchAlgorithmException {
		KeyGenerator keygen = KeyGenerator.getInstance("AES");
		// 初始化时只能使用SecureRandom，而不能用java.util.Random，因为SecureRandom随机性级别更高
		SecureRandom random = new SecureRandom();
		keygen.init(random);
		Key key = keygen.generateKey();
		return key;
	}
}
