package com.ling.algorithms10.elementarydatastructures;

/**
 * 队列(循环队列)的数组实现
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms10.elementarydatastructures.QueueImpl.java
 *
 * author lingang
 *
 * createTime 2020-07-31 10:29:47
 *
 */
public class QueueImpl {
	private Object[] data;// 保存队列数据的数组
	private int headIndex;// 队头指针，指向队头元素
	private int tailIndex;// 队尾指针，指向队尾元素
	// 队大小，因为当队头和队尾指针相同时，可能是队空，也可能是队满，所以需要一个队大小字段
	private int len;

	public static void main(String[] args) {
		QueueImpl q = new QueueImpl(5);

		// 入队
		q.enQueue(1);
		q.enQueue(3);
		q.enQueue(9);
		q.enQueue(2);
		q.enQueue(4);
		// q.enQueue(7);//队满

		System.out.println("查看队头元素：" + q.getHead());// 查看队头元素

		System.out.println("出队：" + q.deQueue());// 出队

		System.out.println("查看队大小：" + q.length());// 查看队大小

		System.out.println(q);// 查看栈中所有元素

	}

	public QueueImpl(int size) {
		if (size <= 0)
			throw new RuntimeException("size must be positive!");
		this.data = new Object[size];
		// 头指针和尾指针相同时表示队空
		this.headIndex = 0;
		this.tailIndex = 0;
		this.len = 0;
	}

	public void enQueue(Object o) {
		if (this.len == data.length) {// 队满
			throw new RuntimeException("queue was full!");
		}
		++this.len;
		data[tailIndex] = o;// 将新元素加到队尾
		tailIndex = (tailIndex + 1) % data.length;// 队尾指针往后移动1个位置
	}

	public Object getHead() {
		if (this.len == 0) // 队空
			throw new RuntimeException("queue was empty!");
		return data[headIndex];
	}

	public Object deQueue() {
		if (this.len == 0) // 队空
			throw new RuntimeException("queue was empty!");
		Object reuslt = data[headIndex];// 先保存队头元素
		headIndex = (headIndex + 1) % data.length;// 队头指针往后移动1个位置
		--this.len;
		return reuslt;
	}

	public int length() {
		return this.len;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("StackImpl [");
		if (tailIndex > headIndex)
			for (int i = headIndex; i < headIndex; ++i)
				sb.append(data[i]).append(", ");
		if (tailIndex < headIndex && this.len > 0) {
			for (int i = headIndex; i < data.length; ++i)
				sb.append(data[i]).append(", ");
			for (int i = 0; i < tailIndex; ++i)
				sb.append(data[i]).append(", ");
		}
		sb.delete(sb.length() - 2, sb.length());
		sb.append("]");
		return sb.toString().startsWith(", ") ? sb.toString().substring(2, sb.toString().length()) : sb.toString();
	}

}
