package com.ling.learn0901.collectionpreview;

public class SealedTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*
		 * java.lang.SecurityException: sealing violation: can't seal package
		 * com.ling.learn0901.collectionpreview: already loaded
		 */
		IteratorTest it = new IteratorTest();// 密封包，不能访问相同包名中的类
	}
}
