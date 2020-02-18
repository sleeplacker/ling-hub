package com.ling.learn1409.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 执行器-newFixedThreadPool
 * 
 * 这个例子和ExecutorTest.java中的例子的执行结果有差别，因为ExecutorTest.java中的newCachedThreadPool总会在需要时创建新的线程，而newFixedThreadPool的线程池大小是固定的，这里是1，所以两条线程会串行执行，需要8秒，而ExecutorTest.java中只需花较长时间的那个任务的时间，即5秒
 *
 * Chapter14/com.ling.learn1409.executor.ExecutorTest.java
 *
 * author lingang
 *
 * createTime 2019-12-22 23:47:08
 *
 */
public class ExecutorTest2 {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		/*
		 * 新建一个线程池，newFixedThreadPool线程池中可以有指定数量的线程，当任务数超过线程池大小，
		 * 则多出的任务到放到等待队列等待其他任务执行完再执行
		 */
		ExecutorService pool = Executors.newFixedThreadPool(1);
		/* submit方法会提交一个Callable接口类型的任务，并返回Future对象 */
		Future<Integer> futer1 = pool.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// 任务1，执行3秒
				Thread.sleep(3000);
				System.out.println("任务1：执行完毕");
				return 2;
			}

		});
		Future<Integer> futer2 = pool.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				// 任务1，执行5秒
				Thread.sleep(5000);
				System.out.println("任务2：执行完毕");
				return 2;
			}

		});

		pool.shutdown();
	}
}
