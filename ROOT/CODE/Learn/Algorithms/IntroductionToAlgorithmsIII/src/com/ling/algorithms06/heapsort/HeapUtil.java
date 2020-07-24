package com.ling.algorithms06.heapsort;

/**
 * 堆的基本操作
 * 
 * 1) 计算父节点位置
 * 
 * 2) 计算左孩子位置
 * 
 * 3) 计算右孩子位置
 * 
 * 4) 维护堆性质
 * 
 * 5) 建堆
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms06.heapsort.HeapUtil.java
 *
 * author lingang
 *
 * createTime 2020-07-22 16:38:56
 *
 */
public class HeapUtil {

	/**
	 * 计算一个节点的父节点在数组中的位置
	 * 
	 * @param index-从1开始
	 * @return
	 */
	public static int parent(int index) {
		if (index <= 1)
			return -1;
		return index / 2 - 1;// 数组访问从0开始，最终结果-1
	}

	/**
	 * 计算一个节点的左孩子节点在数组中的位置
	 * 
	 * @param heap
	 * @param index-从1开始
	 * @return
	 */
	public static int leftChild(HeapObject heap, int index) {
		if (2 * index > heap.getHeapSize())
			return -1;
		return 2 * index - 1;// 数组访问从0开始，最终结果-1
	}

	/**
	 * 计算一个节点的右孩子节点在数组中的位置
	 * 
	 * @param heap
	 * @param index-从1开始
	 * @return
	 */
	public static int rightChild(HeapObject heap, int index) {
		if (2 * index + 1 > heap.getHeapSize())
			return -1;
		return 2 * index;// 数组访问从0开始，最终结果-1
	}

	/**
	 * 建堆
	 * 
	 * 从最后一个非根节点开始，维护该节点的堆性质，直到处理完根节点
	 * 
	 * @param data
	 * @param maxHeapFlag-大顶堆标识
	 */
	public static void buildHeap(Double[] data, boolean maxHeapFlag) {
		if (data.length <= 1)// 只包含1个节点的树已经是堆，直接返回
			return;
		HeapObject heap = new HeapObject(data, data.length, maxHeapFlag);
		for (int i = data.length / 2 - 1; i >= 0; --i)// 从最后一个非叶节点到根节点做对性质维护操作
			heapify(heap, i);
	}

	/**
	 * 维护堆的性质
	 * 
	 * 假设以index位置的左、右孩子为根节点的树都已经是堆
	 * 
	 * 将index往下调整，使得以index位置为根节点的树满足堆特性 *
	 * 
	 * @param data
	 * @param index
	 */
	public static void heapify(HeapObject heap, int index) {
		Double[] data = heap.getData();
		int heapSize = heap.getHeapSize();
		boolean maxHeapFlag = heap.isMaxHeapFlag();
		if (index >= heapSize / 2)// 如果处理的已经是叶节点，叶节点都是满足堆性质的，直接返回
			return;
		int leftIndex = HeapUtil.leftChild(heap, index + 1);// 计算左孩子位置
		int rightIndex = HeapUtil.rightChild(heap, index + 1);// 计算右孩子位置，因为一个节点可能只有左孩子，所以可能返回-1
		// 比较得出当前节点、左孩子右孩子中最大/最小的一个
		int largestIndex = index;
		if (leftIndex <= heapSize - 1 && (maxHeapFlag ? data[leftIndex] > data[index] : data[leftIndex] < data[index]))
			largestIndex = leftIndex;
		if (rightIndex != -1 && rightIndex <= heapSize - 1
				&& (maxHeapFlag ? data[rightIndex] > data[largestIndex] : data[rightIndex] < data[largestIndex]))
			largestIndex = rightIndex;
		if (largestIndex != index) {// 如果最大的不是当前节点，则将当前节点和最大节点交换位置
			Double temp = data[largestIndex];
			data[largestIndex] = data[index];
			data[index] = temp;
			heapify(heap, largestIndex);// 交互位置后继续对当前节点做堆性质检查
		}
	}
}
