package com.ling.learn1405.synchronize;

import java.util.Arrays;
import java.util.Vector;

/**
 * 客户端加锁
 *
 * Chapter14/com.ling.learn1405.synchronize.ClientLockingTest.java
 *
 * author lingang
 *
 * createTime 2019-12-11 22:34:29
 *
 */
public class ClientLockingTest {
	private static Vector<Integer> vs = new Vector<>();
	private static boolean[] completeResult = new boolean[50];
	private static boolean[] done = new boolean[50];

	public static void main(String[] args) {
		vs.add(0);
		final ClientLockingTest obj = new ClientLockingTest();
		for (int i = 0; i < 25; ++i) {
			final int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					// obj.inc1000();// 不加锁
					obj.inc1000_2();// 加客户端锁
					completeResult[index] = true;// 每条线程累加完1000次后记录完成标志
				}
			}).start();
		}
		for (int i = 0; i < 25; ++i) {
			final int index = i;
			new Thread(new Runnable() {

				@Override
				public void run() {
					synchronized (vs) {// 必须使用synchronized(vs)
						for (int i = 0; i < 1000; ++i) {
							Integer temp = vs.get(0);
							++temp;
							vs.set(0, temp);
						}
					}
					completeResult[25 + index] = true;
				}
			}).start();
		}

		Arrays.fill(done, true);
		while (!Arrays.equals(completeResult, done)) {
			// 如果50条线程的累加任务没执行完，则程序空转
		}
		// 至此50条线程累加任务完成，count变量的值应该为50000
		System.out.println(vs.get(0));// 调用inc1000()是得到的值不是50000，说明不加锁会出现线程安全问题；调用inc1000_2()时得到50000，说明需要加客户端锁
	}

	public void inc1000() {
		/*
		 * 虽然这个方法中修改的Vector对象是线程安全的，即get方法和set方法是同步的，但是这整个方法却不是线程安全的，
		 * 因为当get方法执行完后，会释放锁，很有可能其他线程会抢到这个锁然后给这个元素进行累加，然后回到这个线程的时候，累加的就是另一个数，
		 * 这就造成了不一致性
		 */
		Integer temp = vs.get(0);
		for (int i = 0; i < 1000; ++i) {
			++temp;
		}
		vs.set(0, temp);
	}

	public void inc1000_2() {
		/*
		 * 这里不能用synchronized (this) ，应该用synchronized (vs)
		 * 因为如果用this，表示使用的是当前类的内部锁，如果在其他线程使用了其他锁来修改vs，那么也会出现多条线程同时修改vs的情况，
		 * 而如果使用vs的内部锁，由于所有Vector的修改方法都使用的内部锁，所以同一时刻只有一条线程在修改vs(
		 * 当然这是建立在Vector所有修改方法都使用内部锁的前提下，如果Vector的修改方法使用了不同的锁，那么同步块也会失效)
		 */
		synchronized (vs) {
			Integer temp = vs.get(0);
			for (int i = 0; i < 1000; ++i) {
				++temp;
			}
			vs.set(0, temp);
		}
	}
}
