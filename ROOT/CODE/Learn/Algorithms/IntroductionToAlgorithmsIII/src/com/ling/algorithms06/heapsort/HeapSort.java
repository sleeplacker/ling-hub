package com.ling.algorithms06.heapsort;

import java.util.Arrays;

/**
 * 本章的例子都是针对最大堆，或称大顶堆，小顶堆只需改变一下比较条件
 * 
 * 堆排序，步骤如下：
 * 
 * 1) 用待排序数组建堆
 * 
 * 2) 从根节点开始，递归进行：堆首尾交换、堆大小-1、对新根节点维护堆性质
 * 
 * 时间复杂度：O(nlgn)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms06.heapsort.HeapSort.java
 *
 * author lingang
 *
 * createTime 2020-07-22 11:47:38
 *
 */
public class HeapSort {
	public static void main(String[] args) {

		// 维护堆性质测试
		Double[] data = { 16d, 4d, 10d, 14d, 7d, 9d, 3d, 2d, 8d, 1d };
		System.out.println("维护堆性质测试");
		System.out.println(Arrays.toString(data));
		HeapUtil.heapify(new HeapObject(data, data.length), 1);
		System.out.println(Arrays.toString(data));

		// 建堆测试
		System.out.println("建堆测试");
		Double[] data2 = { 4d, 1d, 3d, 2d, 16d, 9d, 10d, 14d, 8d, 7d };
		System.out.println(Arrays.toString(data2));
		HeapUtil.buildHeap(data2);
		System.out.println(Arrays.toString(data2));

		// 堆排序测试
		System.out.println("堆排序测试");
		Double[] data3 = { 4d, 1d, 3d, 2d, 16d, 9d, 10d, 14d, 8d, 7d };
		System.out.println(Arrays.toString(data3));
		heapSort(data3);
		System.out.println(Arrays.toString(data3));
	}

	/**
	 * 堆排序-最大堆排出正序，最小堆排出倒序
	 * 
	 * @param data
	 */
	public static void heapSort(Double[] data) {
		HeapUtil.buildHeap(data);// 首先用待排序数组建堆
		int heapSize = data.length;// 组成堆的元素个数
		while (heapSize > 1) {
			// 交换堆中根节点和最后一个叶节点的位置
			Double temp = data[0];
			data[0] = data[heapSize - 1];
			data[heapSize - 1] = temp;
			// 将堆的大小减1，堆后面的元素已经是有序的
			--heapSize;
			// 对新的根节点维护堆性质
			HeapUtil.heapify(new HeapObject(data, heapSize), 0);
		}

	}
}
