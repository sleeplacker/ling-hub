package com.ling.algorithms10.elementarydatastructures;

/**
 * 栈的数组实现
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms10.elementarydatastructures.StackImpl.java
 *
 * author lingang
 *
 * createTime 2020-07-31 10:21:19
 *
 */
public class StackImpl {
	private Object[] data;// 保存栈数据的数组
	private int topIndex;// 栈指针，指向栈顶位置

	public static void main(String[] args) {
		StackImpl s = new StackImpl(5);

		// 进栈
		s.push(1);
		s.push(3);
		s.push(9);
		s.push(2);
		s.push(3);
		// s.push(4);//栈满

		System.out.println("查看栈顶元素：" + s.top());// 查看栈顶元素

		System.out.println("出栈：" + s.pop());// 出栈

		System.out.println("查看栈大小：" + s.length());// 查看栈大小

		System.out.println(s);// 查看栈中所有元素
	}

	public StackImpl(int size) {
		this.data = new Object[size];
		this.topIndex = -1;// 空栈时，栈顶指针为-1
	}

	public void push(Object o) {
		if (topIndex >= data.length - 1)
			throw new RuntimeException("stack was full!");
		data[++topIndex] = o;
	}

	public Object pop() {
		if (topIndex < 0)
			throw new RuntimeException("stack was empty!");
		return data[topIndex--];
	}

	public Object top() {
		if (topIndex < 0)
			throw new RuntimeException("stack was empty!");
		return data[topIndex];
	}

	public int length() {
		return topIndex + 1;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("StackImpl [");
		if (topIndex >= 0)
			sb.append(data[0]);
		for (int i = 1; i <= topIndex; ++i)
			sb.append(" ,").append(data[i]);
		sb.append("]");
		return sb.toString();
	}
}
