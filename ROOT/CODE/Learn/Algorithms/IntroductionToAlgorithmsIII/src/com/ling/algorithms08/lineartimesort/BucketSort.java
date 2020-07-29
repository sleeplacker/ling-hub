package com.ling.algorithms08.lineartimesort;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Random;
import java.util.stream.Stream;

import com.ling.algorithms07.quicksort.QuickSort;

/**
 * 桶排序
 * 
 * 使用条件：待排序元素随机分布
 * 
 * 思路：看下面写的桶排序方法的注释
 * 
 * 时间复杂度：最坏情况为θ(n²)，这时所有元素都在桶的同一个位置的链表中；平均情况为θ(n)
 * 
 * 桶排序是稳定排序算法
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms08.lineartimesort.BucketSort.java
 *
 * author lingang
 *
 * createTime 2020-07-29 23:20:37
 *
 */
public class BucketSort {

	public static void main(String[] args) {
		// 生成随机数组-取值范围：0-99
		Random r = new Random();
		Integer[] data = Stream.<Integer>generate(() -> {
			return r.nextInt(1000);
		}).limit(50).toArray(Integer[]::new);

		System.out.println(Arrays.toString(data));
		// 正序
		bucketSort(data, 100, 1000, true);
		System.out.println(Arrays.toString(data));
		// 倒序
		bucketSort(data, 100, 1000, false);
		System.out.println(Arrays.toString(data));
	}

	/**
	 * 通排序
	 * 
	 * @param data
	 * @param bucketSize-桶大小
	 * @param maxValue-最大值
	 * @param ascFlag
	 */
	public static void bucketSort(Integer[] data, Integer bucketSize, Integer maxValue, boolean ascFlag) {
		if (data.length <= 1)
			return;
		LinkedList<Integer>[] bucket = new LinkedList[bucketSize];// 创建桶
		for (Integer e : data)// 将所有待排序元素插入到桶中正确位置的链表中
			insertBucketList(bucket, e, maxValue);
		int index = 0;
		for (LinkedList<Integer> list : bucket) {
			if (list != null) {// 桶中的空元素不处理
				sortBucketList(list);// 对桶中每个链表进行排序
				for (Integer e : list)// 按桶编号顺序将链表元素按需放到排序结果数组
					if (ascFlag)
						data[index++] = e;
					else
						data[data.length - 1 - index++] = e;
			}
		}
	}

	/**
	 * 将元素放入桶中
	 * 
	 * @param bucket
	 * @param e
	 */
	public static void insertBucketList(LinkedList[] bucket, Integer e, Integer maxValue) {
		Integer bucketIndex = e / (maxValue / bucket.length);// 计算要放入桶的位置：元素值/(最大值/桶大小)
		LinkedList<Integer> list = bucket[bucketIndex];
		if (list == null) {// 如果链表为空，则创建链表
			list = new LinkedList<>();
			bucket[bucketIndex] = list;
		}
		list.add(e);// 将元素插入链表
	}

	/**
	 * 对桶中每个链表进行排序
	 * 
	 * @param list
	 */
	public static void sortBucketList(LinkedList<Integer> list) {
		if (list == null)
			return;
		Double[] data = list.stream().map(Double::valueOf).toArray(Double[]::new);
		QuickSort.quickSort(data, true, false);// 使用前面写的快速排序算法进行排序
		ListIterator<Integer> i = list.listIterator();
		for (Double e : data) {
			i.next();
			i.set(e.intValue());
		}
	}
}
