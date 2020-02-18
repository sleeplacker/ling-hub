package com.ling.learn1405.synchronize;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * 原子类测试4-当有很多线程同时更新原子类的值时，使用LongAccumulator会有更好的性能
 * 
 * LongAccumulator类中有多个变量用来存值，要想获取最终值，要计算所有变量执行一个二元操作得到结果，这个二元操作是在LongAccumulator的构造器中指定的，这里是Math的max方法
 *
 * Chapter14/com.ling.learn1405.synchronize.AtomicClassTest4.java
 *
 * author lingang
 *
 * createTime 2019-12-16 13:03:43
 *
 */
public class AtomicClassTest4 {
	private static List<Integer> data = new ArrayList<Integer>(500000);
	private static LongAccumulator largest = new LongAccumulator(Math::max, 0);
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

						// 改为使用原子方法accumulateAndGet
						largest.accumulate(data.get(index * 10000 + i));
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
