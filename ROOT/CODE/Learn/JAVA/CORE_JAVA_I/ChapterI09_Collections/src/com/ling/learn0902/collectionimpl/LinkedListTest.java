package com.ling.learn0902.collectionimpl;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

/**
 * 链表操作，java中的链表都是双向链表(即每个元素都有指向上一个元素和指向下一个元素的指针)
 * 
 * 1. 链表可以通过ListIterator双向遍历元素以及新增删除元素，
 * ListIterator.add方法只依赖迭代器位置，而ListIterator.remove操作依赖迭代器的状态(即迭代方向不同，删除元素的规则也不同)
 * 
 * 2. 不允许多个迭代器并发修改链表，即一个迭代器修改了链表结构，则其他迭代器都将失效，需求定义新的迭代器才能进行遍历
 * 
 * 3. 虽然链表中也有get(i)方法，但是这个方法效率很低，
 * 因为链表查找元素都会从头开始一个一个的往下查(一点小优化：当查找位置>链表长度的一半时会从尾部往前查找)
 *
 * Chapter09/com.ling.learn0902.collectionimpl.LinkedListTest.java
 *
 * author lingang
 *
 * createTime 2019-11-21 20:06:32
 *
 */
public class LinkedListTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		List<String> list = new LinkedList<String>();
		list.add("Aa");
		list.add("Bb");
		list.add("Cc");
		System.out.println("1." + list);
		/* 1. ListIterator可以从两个方向遍历链表，且可以在任何位置新增和删除元素 */
		ListIterator<String> iter = list.listIterator();// 迭代器位置：|Aa, Bb, Cc
		iter.add("Dd");// 在链表最前新增一个元素。迭代器位置：Dd, |Aa, Bb, Cc
		System.out.println("2." + list);
		System.out.println(iter.next());// 输出Aa，新增元素完成以后，迭代器位置在刚才新增元素的后面。迭代器位置：Dd,
										// Aa, |Bb, Cc
		iter.next();// 越过后面一个元素Bb，迭代器移到Bb后面。迭代器位置：Dd, Aa, Bb, |Cc
		iter.add("Ee");// 在Bb后面新增Ee，现在迭代器在Ee后面。迭代器位置：Dd, Aa, Bb, Ee, |Cc
		System.out.println("3." + list);
		iter.previous();// 迭代器移到上一个位置，即Ee前面。迭代器位置：Dd, Aa, Bb, |Ee, Cc
		iter.remove();// 若remove之前调用的是previous方法，则删除迭代器后面的元素。迭代器位置：Dd, Aa, Bb,
						// |Cc
		System.out.println("4." + list);
		iter.next();// 迭代器位置：Dd, Aa, Bb, Cc|
		iter.remove();// 若remove之前调用的是next方法，则删除迭代器前面的元素。迭代器位置：Dd, Aa, Bb|
		System.out.println("5." + list);

		/* 2. 不允许多个迭代器并发修改链表 */
		ListIterator<String> it1 = list.listIterator();
		ListIterator<String> it2 = list.listIterator();
		it1.next();
		it1.remove();// 改变了链表结构
		ListIterator<String> it3 = list.listIterator();
		/* 当一个迭代器改变链表结构，那其他迭代器都将失效，无法进行遍历操作，需要定义新的迭代器才能进行遍历操作 */
		// it2.next();//java.util.ConcurrentModificationException
		// iter.next();//java.util.ConcurrentModificationException
		it3.next();// 新的迭代器可以遍历
		System.out.println(list);
	}
}
