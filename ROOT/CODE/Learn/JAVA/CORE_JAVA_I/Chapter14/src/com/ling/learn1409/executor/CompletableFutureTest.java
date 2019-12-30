package com.ling.learn1409.executor;

import java.math.BigDecimal;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 可完成future
 *
 * Chapter14/com.ling.learn1409.executor.CompletableFutureTest.java
 *
 * author lingang
 *
 * createTime 2019-12-23 22:42:23
 *
 */
public class CompletableFutureTest {
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(() ->
		// 1);
		CompletableFuture<Integer> cf = CompletableFuture.supplyAsync(CompletableFutureTest::doMainStep);
		CompletableFuture<String> cs = cf.thenApply(CompletableFutureTest::doStep2);
		// cs.thenApply(CompletableFutureTest::doStep3);
		cf.thenApply(CompletableFutureTest::doStep1);
		System.out.println("任务分配完成");
		System.out.println(cf.get());
		System.out.println("任务执行完成");
	}

	public static Integer doMainStep() {
		System.out.println("开始主任务");
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("完成主任务");
		return 1;
	}

	public static String doStep1(Integer str) {
		System.out.println("开始步骤一");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("完成步骤一");
		return null;
	}

	public static String doStep2(Integer str) {
		System.out.println("开始步骤二");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("完成步骤二");
		return null;
	}

	public static BigDecimal doStep3(String str) {
		System.out.println("开始步骤三");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("完成步骤三");
		return null;
	}
}
