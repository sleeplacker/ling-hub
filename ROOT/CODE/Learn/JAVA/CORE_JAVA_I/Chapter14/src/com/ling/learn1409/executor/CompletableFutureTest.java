package com.ling.learn1409.executor;

import java.util.Arrays;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * 可完成future
 * 
 * 当一个任务有多个步骤，可以使用可完成future来指定每个步骤完成后有哪些步骤可以执行，这些步骤可以是同步或者异步的
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
		CompletableFuture<String> cf = CompletableFuture.supplyAsync(CompletableFutureTest::generateStr);// 步骤0：生成字符串
		CompletableFuture<String> ucf = cf.thenApplyAsync(CompletableFutureTest::doUpper);// 步骤1：将字符串转换为大写-需要等待字符串生成完成，即cf
		CompletableFuture<String> ocf = ucf.thenApplyAsync(CompletableFutureTest::doOrder);// 步骤2：将字符串进行排序-需要等待字符串转大写完成，即ocf
		CompletableFuture<Integer> ccf = cf.thenApplyAsync(CompletableFutureTest::doCount);// 步骤3：计算字符串长度-只需等待字符串生成完成，即cf
		System.out.println("任务分配完成");
		System.out.println(ucf.get());
		System.out.println(ocf.get());
		System.out.println(ccf.get());
		System.out.println("任务执行完成");
	}

	public static String generateStr() {
		System.out.println("0开始生成字符串");
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("0生成字符串完成");
		return "LinZeYi";
	}

	public static String doUpper(String str) {
		System.out.println("1开始转大写");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("1转大写完成");
		return str.toUpperCase();
	}

	public static String doOrder(String str) {
		System.out.println("2开始排序");
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		char[] carr = str.toCharArray();
		Arrays.sort(carr);
		System.out.println("2排序完成");
		return new String(carr);
	}

	public static Integer doCount(String str) {
		System.out.println("3开始计算长度");
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("3计算长度完成");
		return str.length();
	}
}
