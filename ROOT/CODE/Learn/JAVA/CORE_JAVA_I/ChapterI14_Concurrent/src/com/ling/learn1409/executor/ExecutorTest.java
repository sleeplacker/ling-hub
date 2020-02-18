package com.ling.learn1409.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 执行器-newCachedThreadPool
 *
 * Chapter14/com.ling.learn1409.executor.ExecutorTest.java
 *
 * author lingang
 *
 * createTime 2019-12-22 23:47:08
 *
 */
public class ExecutorTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/* 新建一个线程池，newCachedThreadPool线程池在没有空闲线程时会创建新线程，每个线程空闲60秒后被销毁 */
		ExecutorService pool = Executors.newCachedThreadPool();
		/* submit方法会提交一个Callable接口类型的任务，并返回Future对象 */
		Future<Integer> futer1 = pool.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// 任务1，执行3秒
				Thread.sleep(3000);
				return 2;
			}

		});
		Future<Integer> futer2 = pool.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// 任务1，执行5秒
				Thread.sleep(5000);
				return 2;
			}

		});
		System.out.println("任务1：" + futer1.get());
		System.out.println("任务2：" + futer2.get());

		/* 当所有任务都执行完成后要关闭线程池，否则会一致阻塞，当调用shutdown方法时，会执行所有已经提交的任务，而不会再接受新的任务 */
		pool.shutdown();
	}
}
