package com.ling.algorithms10.elementarydatastructures;

/**
 * (双向)链表的实现
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms10.elementarydatastructures.LinkedListImpl.java
 *
 * author lingang
 *
 * createTime 2020-08-04 22:56:25
 *
 */
public class LinkedListImpl<E> {
	// 使用哨兵的好处：简化操作，不需要判读节点空指针的情况
	private Node<E> head;// 链表头-哨兵，不存储数据
	private Node<E> tail;// 链表尾-哨兵，不存储数据

	public static void main(String[] args) {
		LinkedListImpl<Integer> linkedList = new LinkedListImpl<>();
		System.out.println(linkedList);

		// 插入链表
		linkedList.insertFirst(1);
		System.out.println(linkedList);
		linkedList.insertTail(3);
		System.out.println(linkedList);
		linkedList.insertTail(9);
		System.out.println(linkedList);
		linkedList.insertTail(2);
		System.out.println(linkedList);
		linkedList.insertTail(5);
		System.out.println(linkedList);
		linkedList.insertFirst(4);
		System.out.println(linkedList);// 遍历链表

		// 获取链表头/尾元素
		System.out.println(linkedList.getFirst());
		System.out.println(linkedList.getTail());

		// 通过关键字查找链表结点
		Node<Integer> v9 = linkedList.search(9);
		// 通过查找到的链接节点查看前驱和后继元素
		System.out.println(v9.pre.item);
		System.out.println(v9.next.item);

		// 删除元素
		linkedList.delete(2);
		System.out.println(linkedList);

	}

	public LinkedListImpl() {
		this.head = new Node<E>(null, null, null);
		this.tail = new Node<E>(null, null, head);
		this.head.next = this.tail;
	}

	public void insertFirst(E e) {
		Node newNode = new Node<E>(e, this.head.next, this.head);
		this.head.next.pre = newNode;
		this.head.next = newNode;
	}

	public void insertTail(E e) {
		Node newNode = new Node<E>(e, this.tail, this.tail.pre);
		this.tail.pre.next = newNode;
		this.tail.pre = newNode;
	}

	public E getFirst() {
		return this.head.next.item;
	}

	public E getTail() {
		return this.tail.pre.item;
	}

	public Node search(E e) {
		for (Node n = this.head; n.next != tail; n = n.next)
			if (e.equals(n.item))
				return n;
		return null;
	}

	public boolean delete(E e) {
		Node n = search(e);
		if (n != null) {
			n.pre.next = n.next;
			n.next.pre = n.pre;
			return true;
		}
		return false;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("LinkedListImpl [");
		for (Node n = this.head.next; n != tail; n = n.next)
			sb.append(n.item).append(", ");
		sb.append("]");
		if (sb.charAt(sb.length() - 3) == ',')
			sb.delete(sb.length() - 3, sb.length() - 1);
		return sb.toString();
	}

	/**
	 * 链表节点类
	 *
	 * IntroductionToAlgorithmsIII/com.ling.algorithms10.elementarydatastructures.LinkedListImpl.java
	 *
	 * author lingang
	 *
	 * createTime 2020-08-04 23:54:25
	 *
	 */
	private static class Node<E> {
		E item;
		Node<E> next;
		Node<E> pre;

		public Node(E item, Node<E> next, Node<E> pre) {
			this.item = item;
			this.next = next;
			this.pre = pre;
		}

	}
}
