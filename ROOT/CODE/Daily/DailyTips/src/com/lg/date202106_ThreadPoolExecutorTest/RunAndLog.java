package com.lg.date202106_ThreadPoolExecutorTest;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;

/**
 * 拒绝策略：打印log并在主线程运行
 *
 * DailyTips/com.lg.date202106_ThreadPoolExecutorTest.RunAndLog.java
 *
 * author ling
 *
 * createTime 2021-06-13 07:23:44 
 *
 */
public class RunAndLog extends CallerRunsPolicy {
	@Override
	public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
		System.err.println("线程池满了，工作在主线程运行");
		super.rejectedExecution(r, executor);
	}
}