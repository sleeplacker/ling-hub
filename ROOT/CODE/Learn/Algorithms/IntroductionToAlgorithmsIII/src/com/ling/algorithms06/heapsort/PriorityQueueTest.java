package com.ling.algorithms06.heapsort;

import java.util.Arrays;

/**
 * 用堆实现优先队列操作，在拥有普通队列的入队，出队等基本操作的同时，
 * 
 * 堆实现的优先队列能在O(lgn)的时间内更新元素的值并保持优先队列特性(即每次出队都是最大值/最小值)
 * 
 * 时间复杂度：入队、出队以及更新值3个操作都为O(lgn)
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms06.heapsort.PriorityQueueTest.java
 *
 * author lingang
 *
 * createTime 2020-07-22 18:03:26
 *
 */
public class PriorityQueueTest {
	public static void main(String[] args) {
		Double[] data = new Double[10];// 设置队列容量为10

		// 构造对对象
		HeapObject heap = new HeapObject(data, 0);

		// 入队测试
		// { 16d, 4d, 10d, 14d, 7d, 9d, 3d, 2d, 8d, 1d };
		enQueue(heap, 16d);
		enQueue(heap, 4d);
		enQueue(heap, 10d);
		enQueue(heap, 14d);
		enQueue(heap, 7d);
		enQueue(heap, 9d);
		enQueue(heap, 3d);
		enQueue(heap, 2d);
		enQueue(heap, 8d);
		enQueue(heap, 1d);
		System.out.println("入队10个元素后：" + Arrays.toString(heap.getData()));

		// 更新指定节点的值测试-增加值
		updateKeyValue(heap, 8, 15d);
		System.out.println("更新指定节点的值测试-增加值：" + Arrays.toString(heap.getData()));
		// 更新指定节点的值测试-减小值
		updateKeyValue(heap, 1, 13d);
		System.out.println("更新指定节点的值测试-减小值：" + Arrays.toString(heap.getData()));

		// 查看队头元素
		System.out.println("查看队头元素：" + getHeadValue(heap));

		// 出队测试
		System.out.println("出队" + deQueue(heap) + " " + Arrays.toString(heap.getData()));
		System.out.println("出队" + deQueue(heap) + " " + Arrays.toString(heap.getData()));
		System.out.println("出队" + deQueue(heap) + " " + Arrays.toString(heap.getData()));
		// 再入队
		enQueue(heap, 18d);
		enQueue(heap, 5d);
		System.out.println("18，5入队" + Arrays.toString(heap.getData()));
		// 再出队
		System.out.println("出队" + deQueue(heap) + " " + Arrays.toString(heap.getData()));
		System.out.println("出队" + deQueue(heap) + " " + Arrays.toString(heap.getData()));
	}

	/**
	 * 获取队头元素
	 * 
	 * @param heap
	 * @return
	 */
	public static Double getHeadValue(HeapObject heap) {
		if (heap.getHeapSize() <= 0)
			throw new RuntimeException("Empty queue!");
		return heap.getData()[0];
	}

	/**
	 * 入队
	 * 
	 * @param heap
	 * @param value
	 */
	public static void enQueue(HeapObject heap, Double value) {
		Double[] data = heap.getData();
		int heapSize = heap.getHeapSize();
		if (heapSize >= data.length)// 队满后不能入队
			throw new RuntimeException("Queue is full!");
		data[heapSize] = Double.MIN_VALUE;// 在队尾新增一个元素，值初始化为最小值
		int index = heapSize;// 记录新元素位置
		++heapSize;// 对大小+1
		heap.setHeapSize(heapSize);
		updateKeyValue(heap, index, value);// 对新元素位置调用值更新方法
	}

	/**
	 * 出队
	 * 
	 * @param heap
	 * @return
	 */
	public static Double deQueue(HeapObject heap) {
		if (heap.getHeapSize() <= 0)// 空队无法出队
			throw new RuntimeException("Empty queue!");
		Double[] data = heap.getData();
		int heapSize = heap.getHeapSize();
		Double result = data[0];// 获取队头元素值
		data[0] = data[heapSize - 1];// 将队头位置更新为队尾值
		data[heapSize - 1] = null;// 将队尾值置空
		--heapSize;// 对大小-1
		heap.setHeapSize(heapSize);
		HeapUtil.heapify(heap, 0);// 对根节点维护堆性质
		return result;
	}

	/**
	 * 更新队列元素关键字的值
	 * 
	 * @param data
	 * @param index
	 */
	public static void updateKeyValue(HeapObject heap, int index, Double newValue) {
		if (index < 0 || heap.getHeapSize() <= index)
			throw new RuntimeException("Wrong input!");
		Double[] data = heap.getData();
		Double oldValue = data[index];
		data[index] = newValue;// 更新指定位置的值
		if (newValue == oldValue)// 新旧值相同，无需更新
			return;
		if (newValue < oldValue) {
			// 新值减小，直接对该节点维护堆性质
			HeapUtil.heapify(heap, index);
		} else {
			// 新值增大，递归第将该节点与其父节点交换，直到父节点值>=新值，或该节点已经成为新的根节点
			int tempIndex = index;
			int parentIndex = HeapUtil.parent(tempIndex + 1);
			while (tempIndex > 0 && data[parentIndex] < newValue) {
				Double temp = data[parentIndex];
				data[parentIndex] = data[tempIndex];
				data[tempIndex] = temp;
				tempIndex = parentIndex;
				parentIndex = HeapUtil.parent(tempIndex + 1);
			}
		}
	}
}
