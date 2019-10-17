package com.ling.learn0503.arraylist;

import java.util.ArrayList;
import java.util.Date;

/**
 * ArrayList类
 *
 *1. ArrayList设定容量时，只是借用了空间，而没有真正的占用空间，因为可以通过trimToSize方法归还借用的空间
 *
 *2. 列表可以扩大和消减类别容量，当确定列表不会再增大的情况下，应该调用trimToSize方法释放列表没存放元素的空间
 *
 *3. 不指定泛型的列表可以放入各种对象类型，遍历列表的时候应该清楚列表中的对象类型
 *
 *4. 虽然可以在列表中间插入或删除元素，但是这种操作会移动插入或删除位置后面的所有元素，所以如果列表中的数据量较大时，
 *		不应该出现频繁的插入删除操作，如果必须这样，应该考虑使用链表来存储数据
 *
 * Chapter5/com.ling.learn0503.arraylist.ArrayListTest.java
 *
 * author lingang
 *
 * createTime 2019-10-18 00:23:09
 *
 */
public class ArrayListTest {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static void main(String[] args) {
		System.out.println("1. ArrayList设定容量时，只是借用了空间，而没有真正的占用空间，因为可以通过trimToSize方法归还借用的空间");
		int[] iArr = new int[100]; //数组申请的空间是真正占用的空间，可以随意往其中放入元素
		System.out.println(iArr[0]);// 0

		ArrayList<Integer> list = new ArrayList<>(100);
//		System.out.println(list.get(0));// java.lang.IndexOutOfBoundsException: Index: 0, Size: 0，虽然容量是100，但是没有元素，不能访问
		
		System.out.println("\n2. 可以扩大和消减类别容量");
		list.ensureCapacity(150); // 改变列表的容量为150，但是列表长度仍为0
		
		list.add(0);// 向列表加入一个元素，列表长度变为1
		
		list.trimToSize();// 将列表容量消减到当前长度，虚拟机会回收消减掉的内存空间，此时列表容量为1，长度为1
		
		System.out.println("\n3. 不指定泛型的列表可以放入各种对象类型");
		ArrayList oList = new ArrayList(); // 不指定泛型，默认是Object列表，可以加入各种对象
		oList.add(Integer.valueOf(5));
		oList.add(new Date());
		System.out.println(oList);
	}
}
