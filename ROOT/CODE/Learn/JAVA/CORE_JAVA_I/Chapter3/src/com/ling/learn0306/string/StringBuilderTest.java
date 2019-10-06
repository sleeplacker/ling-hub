package com.ling.learn0306.string;

/**
 * 字符串构建器的使用和比较
 *
 *1.StringBuilder可以很方便的在字符串中间插入字串，而不用新建多余的字符串对象 
 *
 *2.StringBuilder不是线程安全的，每次使用最好new一个新对象；StringBuffer是线程安全的，但是速度不如StringBuilder
 *
 * Chapter3/com.ling.learn0306.string.StringBuilderTest.java
 *
 * author lingang
 *
 * createTime 2019-10-06 22:19:25
 *
 */
public class StringBuilderTest {
	private static StringBuffer buffer = new StringBuffer();
	private static StringBuilder builder = new StringBuilder();
	public static void main(String[] args) {
		/* 1.StringBuilder可以很方便的在字符串中间插入字串，而不用新建多余的字符串对象 */
		String str = "12345";
		StringBuilder sbd = new StringBuilder(str);
		sbd.insert(2, "|");
		System.out.println(sbd.toString());
		
		/* 2.StringBuilder不是线程安全的，每次使用最好new一个新对象；StringBuffer是线程安全的，但是速度不如StringBuilder快*/
		multiThreadStringBuilderTest(); // 100条线程做StringBuilder的append操作
		multiThreadStringBufferTest(); // 100条线程做StringBuffer的append操作
		/* 从输出结果可以看到，100条线程做StringBuilder的append操作时，有时会出现乱码，所以不是线程安全*/
	}

	private static void multiThreadStringBuilderTest() {
		// StringBuilder测试
		for(int i=0;i<100;++i){
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					builder.append("林");
				}
			}).start();
		}
		try {
			Thread.sleep(1000); // 等待100条线程操作完成
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(builder.toString());
	}
	
	private static void multiThreadStringBufferTest() {
		// StringBuffer测试
		for(int i=0;i<100;++i){
			new Thread(new Runnable() {
				@Override
				public void run() {
					// TODO Auto-generated method stub
					buffer.append("林");
				}
			}).start();
		}
		try {
			Thread.sleep(1000); // 等待100条线程操作完成
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(buffer.toString());
	}	
}
