package com.ling.learn0904.viewandwrapper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 集合视图
 *
 * Chapter09/com.ling.learn0904.viewandwrapper.VeiwsTest.java
 *
 * author lingang
 *
 * createTime 2019-11-25 20:32:47
 *
 */
public class VeiwsTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/* 获得单元素集 */
		Set<String> sd = Collections.singleton("A");
		// sd.add("B");//java.lang.UnsupportedOperationException
		System.out.println(sd);

		/* 集的类型可以推导得出 */
		Set<Date> deepThoughts = Collections.emptySet();

		List<Integer> iList = new ArrayList<>();
		iList.add(0);
		iList.add(1);
		iList.add(2);
		iList.add(3);
		iList.add(4);
		iList.add(5);
		iList.add(6);
		/* 子集 */
		System.out.println(iList);
		List<Integer> subIList = iList.subList(2, 4);// 第3-4个，包含左边界，不包含右边界
		System.out.println(subIList);
		subIList.clear();// 删除子集
		System.out.println(iList);

		/* 不可修改结构，但可以修改内容 */
		List<Integer> iList2 = Arrays.asList(1, 2, 3, 4, 5);
		// iList2.add(8);//java.lang.UnsupportedOperationException
		// iList2.remove(1);//java.lang.UnsupportedOperationException
		iList2.set(1, 9);// 可以修改内容
		System.out.println(iList2);

		/* 不可修改视图-结构和内容都不能修改 */
		List unmList = Collections.unmodifiableList(iList);
		// unmList.set(0, 9);
		// unmList.add(7);
		// unmList.remove(1);//内容也不能修改
		System.out.println(unmList);
		
		/*同步视图，HashMap为非同步容器，可以调用Collections.synchronizedMap方法获得HashMap的同步视图*/
		Map<String,Object> syncMap = Collections.synchronizedMap(new HashMap<>());//syncMap为线程安全的
		
		/*受查视图*/
		List<String> sList = new ArrayList<>();
		List rawList = sList;// 将泛型列表赋值给原始类型列表，这个对象
		rawList.add("AA");//加入String对象
		rawList.add(1);//加入Integer对象，运行正常，将该元素赋值给String对象才会报错
		
		List<String> checkedList = Collections.checkedList(sList, String.class);
		List rawList2 = checkedList;
		rawList2.add(1);//运行时就会报错，因为checkedList视图会检查类型参数
		
	}

}
