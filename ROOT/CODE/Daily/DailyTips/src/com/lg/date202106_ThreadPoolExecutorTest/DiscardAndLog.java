package com.lg.date202106_ThreadPoolExecutorTest;

import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * 拒绝策略：直接丢弃且打印log
 *
 * DailyTips/com.lg.date202106.Date13_ThreadPoolExecutorTest.java
 *
 * author ling
 *
 * createTime 2021-06-13 07:08:11
 *
 */
 public class DiscardAndLog implements RejectedExecutionHandler {

	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.err.println("线程池满了，丢弃工作");
	}

}