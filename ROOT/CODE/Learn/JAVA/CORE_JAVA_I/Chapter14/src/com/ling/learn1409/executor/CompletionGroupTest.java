package com.ling.learn1409.executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 任务完成组
 *
 * Chapter14/com.ling.learn1409.executor.CompletionGroupTest.java
 *
 * author lingang
 *
 * createTime 2019-12-23 01:00:31
 *
 */
public class CompletionGroupTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		ExecutorService pool = Executors.newCachedThreadPool();
		ExecutorCompletionService<Integer> completionGroup = new ExecutorCompletionService<>(pool);
		/* 启动4个任务，其中任务4会花很长时间 */
		completionGroup.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Thread.sleep(3000);
				return 1;
			}
		});
		completionGroup.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Thread.sleep(6000);
				return 2;
			}
		});
		completionGroup.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Thread.sleep(5000);
				return 3;
			}
		});
		completionGroup.submit(new Callable<Integer>() {

			@Override
			public Integer call() throws Exception {
				Thread.sleep(50000000);
				return 4;
			}
		});

		// take方法会从已完成的任务中移除一个，并获得该任务执行的结果，但是这样只能得到一个任务的结果
		int result = completionGroup.take().get();
		System.out.println("任务" + result + "执行完成");

		// 如果要查看所有任务的执行结果，需要在一个循环中不断读取完成列表
		while (true) {
			Thread.sleep(1000);
			result = completionGroup.take().get();
			System.out.println("任务" + result + "执行完成");
		}

	}
}
