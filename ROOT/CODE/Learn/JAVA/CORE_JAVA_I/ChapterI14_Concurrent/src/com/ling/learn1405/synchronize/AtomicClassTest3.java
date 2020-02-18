package com.ling.learn1405.synchronize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类测试3-原子类测试2的解决方案
 *
 * Chapter14/com.ling.learn1405.synchronize.AtomicClassTest3.java
 *
 * author lingang
 *
 * createTime 2019-12-16 11:51:04
 *
 */
public class AtomicClassTest3 {
	private static List<Integer> data = new ArrayList<Integer>(500000);
	private static AtomicInteger largest = new AtomicInteger(0);
	private static boolean[] completeResult = new boolean[50];
	private static boolean[] done = new boolean[50];

	public static void main(String[] args) {
		// 初始化为1-500000
		for (int i = 0; i < 500000; ++i) {
			data.add(i + 1);
		}
		Collections.shuffle(data);// 打乱
		for (int i = 0; i < 50; ++i) { // 让50条线程同时查找最大值
			final int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < 10000; ++i) {// 每条线程查10000个数
						/* 下面的查找不是原子的，因为在Math.max方法执行过程中，largest的值可能已经被其他线程改变 */
						// int temp = Math.max(data.get(index * 1000 + i),
						// largest.intValue());
						// largest.set(temp);

						// 改为使用原子方法accumulateAndGet
						largest.accumulateAndGet(data.get(index * 10000 + i), Math::max);
					}
					completeResult[index] = true;// 每条线程执行完成后记录完成标志
				}
			}).start();
		}
		Arrays.fill(done, true);
		while (!Arrays.equals(completeResult, done)) {
			// 这里如果使用程序空转会出现卡死，所以使用睡1秒的方法
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(largest);// 不管运行多少次结果始终是50000
	}

}
