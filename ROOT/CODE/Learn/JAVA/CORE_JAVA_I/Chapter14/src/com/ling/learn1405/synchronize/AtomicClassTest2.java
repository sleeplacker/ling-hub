package com.ling.learn1405.synchronize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 原子类测试2
 * 
 * 有问题的例子，解决方案见AtomicClassTest3.java
 *
 * Chapter14/com.ling.learn1405.synchronize.AtomicClassTest2.java
 *
 * author lingang
 *
 * createTime 2019-12-16 11:16:52
 *
 */
public class AtomicClassTest2 {
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
						int temp = Math.max(data.get(index * 10000 + i), largest.intValue());
						largest.set(temp);
					}
					completeResult[index] = true;// 每条线程累加完1000次后记录完成标志
				}
			}).start();
		}
		Arrays.fill(done, true);
		while (!Arrays.equals(completeResult, done)) {
			// 如果50条线程的累加任务没执行完，则程序空转
		}
		System.out.println(largest);// 499997，不是500000(如果是500000，多运行几次也会得到不是500000的数，如果还是500000，则要加大数字再试)，说明对最大值largest的更新不是原子操作
	}

}
