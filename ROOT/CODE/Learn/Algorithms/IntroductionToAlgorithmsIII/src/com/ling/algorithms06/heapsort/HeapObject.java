package com.ling.algorithms06.heapsort;

/**
 * 堆对象
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms06.heapsort.HeapObject.java
 *
 * author lingang
 *
 * createTime 2020-07-22 18:06:35
 *
 */
public class HeapObject {
	private Double[] data;// 堆元素存储数组
	private int heapSize;// 堆大小
	private boolean maxHeapFlag = true;// 堆类型：true-大顶堆，false-小顶堆

	public HeapObject(Double[] data, int heapSize) {
		this.data = data;
		this.heapSize = heapSize;
	}

	public HeapObject(Double[] data, int heapSize, boolean maxHeapFlag) {
		this(data, heapSize);
		this.maxHeapFlag = maxHeapFlag;
	}

	public Double[] getData() {
		return data;
	}

	public void setData(Double[] data) {
		this.data = data;
	}

	public int getHeapSize() {
		return heapSize;
	}

	public void setHeapSize(int heapSize) {
		this.heapSize = heapSize;
	}

	public boolean isMaxHeapFlag() {
		return maxHeapFlag;
	}

	public void setMaxHeapFlag(boolean maxHeapFlag) {
		this.maxHeapFlag = maxHeapFlag;
	}

}
