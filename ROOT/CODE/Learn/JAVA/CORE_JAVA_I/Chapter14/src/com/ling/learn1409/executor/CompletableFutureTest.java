package com.ling.learn1409.executor;

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
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(() -> "begin");
		cf.thenApply(CompletableFutureTest::doStep2);
		cf.thenApply(CompletableFutureTest::doStep1);

		System.out.println(cf.get());
	}

	public static String doStep1(String str) {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("完成步骤一");
		return null;
	}

	public static String doStep2(String str) {
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("完成步骤二");
		return null;
	}

	public static void doStep3(String str) {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("完成步骤三");
	}
}
