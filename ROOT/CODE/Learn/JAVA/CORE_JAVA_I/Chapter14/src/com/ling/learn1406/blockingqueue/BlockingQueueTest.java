package com.ling.learn1406.blockingqueue;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 阻塞队列
 * 
 * Chapter14/com.ling.learn1406.blockingqueue.BlockingQueueTest.java
 *
 * author lingang
 *
 * createTime 2019-12-17 23:19:41
 *
 */
public class BlockingQueueTest {
	private static int CAPACITY = 10;// 阻塞队列容量
	private static int THREAD_AMOUNT = 100;// 线程最大数量
	private static BlockingQueue<String> BLOCK_QUEUE = new ArrayBlockingQueue<>(CAPACITY);
	private static ThreadLocal<SimpleDateFormat> time20 = ThreadLocal
			.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS"));

	public static void main(String[] args) {
		new Thread(new Runnable() {
			// 填装队列-可以看到前10个数据填装的很快，后面就会等待有线程取出数据后才能继续放入数据
			@Override
			public void run() {
				for (int i = 0; i < THREAD_AMOUNT; ++i) {
					try {
						BLOCK_QUEUE.put("" + (i + 1));// 如果队列满，put操作会阻塞
						System.out.println("put the " + i + " element at : " + time20.get().format(new Date()));
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();

		// 启动100条线程处理队列中的数据
		// for (int i = 0; i <= THREAD_AMOUNT; ++i){ //如果改为<=，那么最后一条线程会从空队列中取数据，这是队列会阻塞
		for (int i = 0; i < THREAD_AMOUNT; ++i) {
			try {
				Thread.sleep(200);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
			new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						String thread = BLOCK_QUEUE.take();// 如果队列为空，则take操作会阻塞
						System.out.println("run thread " + thread);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		}
	}
}
