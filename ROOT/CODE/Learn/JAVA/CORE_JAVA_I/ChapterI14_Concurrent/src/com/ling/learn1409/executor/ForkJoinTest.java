package com.ling.learn1409.executor;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.Predicate;

/**
 * 任务分解与合并
 * 
 * 对于一个复杂的任务，可以拆分为多个小任务并行执行，然后将所有小任务的结果合并
 *
 * Chapter14/com.ling.learn1409.executor.ForkJoinTest.java
 *
 * author lingang
 *
 * createTime 2019-12-23 14:11:27
 *
 */
public class ForkJoinTest {

	public static void main(String[] args) {
		ForkJoinPool pool = new ForkJoinPool();
		SubTask task = new SubTask(1, 10000, x -> (x % 7 == 0));
		pool.invoke(task);
		System.out.println(task.join());
	}
}

@SuppressWarnings("serial")
// 查找7的倍数的个数
class SubTask extends RecursiveTask<Integer> {
	private Integer from;
	private Integer to;
	private final static int THRESHOLD = 1000;// 阀值-即每个任务中元素的个数
	private Predicate<Integer> filter;// 查找7的倍数

	public SubTask(Integer from, Integer to, Predicate<Integer> filter) {
		super();
		this.from = from;
		this.to = to;
		this.filter = filter;
	}

	@Override
	protected Integer compute() {
		if (to - from <= THRESHOLD) {
			int cnt = 0;
			for (int i = from; i < to; ++i) {
				if (filter.test(i))
					++cnt;
			}
			System.out.println(from + " to " + to + " : " + cnt);
			return cnt;
		} else {
			int mid = (to - from) / 2 +from;
			SubTask first = new SubTask(from, mid, filter);
			SubTask second = new SubTask(mid, to, filter);
			invokeAll(first, second);// 执行子任务
			return first.join() + second.join();
		}
	}

}
