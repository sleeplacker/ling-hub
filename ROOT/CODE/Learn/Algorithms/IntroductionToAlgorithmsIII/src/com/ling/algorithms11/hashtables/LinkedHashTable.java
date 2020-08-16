package com.ling.algorithms11.hashtables;

import java.util.Random;
import java.util.stream.Stream;

import com.ling.algorithms10.elementarydatastructures.LinkedListImpl;

/**
 * 链表法解决冲突的哈希表
 * 
 * 本例假设元素只包含关键字，且为非负整数，哈希函数选择除法散列法，桶链表采用前面自己写的链表实现
 *
 * IntroductionToAlgorithmsIII/com.ling.algorithms11.hashtables.LinkedHashTable.java
 *
 * author lingang
 *
 * createTime 2020-08-16 11:52:10
 *
 */
public class LinkedHashTable {
	private LinkedListImpl<Integer>[] data;// 散列表数组
	private static int size = 101;// 散列表的槽的数目，最好是素数，否则容易受攻击

	public static void main(String[] args) {
		Random rand = new Random();
		// 生成随机数组
		Integer[] data = Stream.<Integer>generate(() -> rand.nextInt(2000)).limit(1000).toArray(Integer[]::new);
		LinkedHashTable lht = new LinkedHashTable();
		// 插入元素
		for (Integer e : data)
			lht.insert(e);
		// 遍历哈希表
		System.out.println(lht);
		// 删除元素
		System.out.println(lht.delete(101));
		// 查找元素
		System.out.println(lht.search(102));
		// 再次遍历哈希表
		System.out.println(lht);
	}

	public LinkedHashTable() {
		this.data = new LinkedListImpl[size];
	}

	/**
	 * 将元素插入散列表
	 * 
	 * @param e
	 */
	public void insert(Integer e) {
		Integer hashCode = getHashCode(e);
		LinkedListImpl<Integer> list = data[hashCode];
		if (list == null)
			list = new LinkedListImpl<>();
		data[hashCode] = list;
		list.insertTail(e);
	}

	/**
	 * 从散列表删除元素
	 * 
	 * @param e
	 * @return
	 */
	public boolean delete(Integer e) {
		LinkedListImpl<Integer> list = data[getHashCode(e)];
		if (list == null)
			return false;
		return list.delete(e);
	}

	/**
	 * 从散列表中查找元素
	 * 
	 * @param e
	 */
	public boolean search(Integer e) {
		LinkedListImpl<Integer> list = data[getHashCode(e)];
		if (list == null)
			return false;
		return list.search(e) == null ? false : true;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer("***** datas < *****\n");
		for (int i = 0; i < this.data.length; ++i)
			sb.append("At Index ").append(i).append(" : ").append(this.data[i]).append("\n");
		sb.append("***** datas > *****\n");
		return sb.toString();
	}

	/**
	 * 使用简单的除法散列法
	 * 
	 * @param e
	 * @return
	 */
	public Integer getHashCode(Integer e) {
		return e % data.length;
	}

}
