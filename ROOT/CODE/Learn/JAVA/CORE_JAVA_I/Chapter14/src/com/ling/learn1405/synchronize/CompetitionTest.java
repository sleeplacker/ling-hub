package com.ling.learn1405.synchronize;

import java.util.Arrays;

/**
 * 多条线程同时访问同一变量会出现的问题
 *
 * Chapter14/com.ling.learn1405.synchronize.CompetitionTest.java
 *
 * author lingang
 *
 * createTime 2019-12-10 22:38:21
 *
 */
public class CompetitionTest {
	private static int count = 0;
	private static boolean[] completeResult = new boolean[50];
	private static boolean[] done = new boolean[50];

	public static void main(String[] args) {
		for (int i = 0; i < 50; ++i) { // 让50条线程同时启动
			final int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					for (int i = 0; i < 1000; ++i) {// 对count变量累加1000次
						++count;
					}
					completeResult[index] = true;// 每条线程累加完1000次后记录完成标志
				}
			}).start();
		}
		Arrays.fill(done, true);
		while (!Arrays.equals(completeResult, done)) {
			// 如果50条线程的累加任务没执行完，则程序空转
		}
		// 至此50条线程累加任务完成，count变量的值应该为50000
		/*
		 * 42453，不是50000，如果电脑配置较高，可能会得到50000，这时加大run方法中for循环的次数也会出现输出不是预想值的情况
		 * 
		 * 出现这个问题的原因：累加操作不是原子操作，而是分了很多步骤，比如：
		 * 
		 * 1) 将count变量加载到寄存器
		 * 
		 * 2) 累加count
		 * 
		 * 3) 将累加结果写会count变量
		 * 
		 * 如果多条线程同时在做这个操作，那可能出现当count变量值为n时，有两条线程x和y相继执行了步骤1)，然后x和y再执行后面的步骤2)
		 * 和步骤3)，由于线程x和y加载到寄存器中的值都是n，所以他们做完累加操作后写回count变量的值都是n+1，
		 * 那么这两次累加操作其实只让count增加了1，所以导致count的最终结果小于50000
		 * 
		 */
		System.out.println(count);
	}
}
