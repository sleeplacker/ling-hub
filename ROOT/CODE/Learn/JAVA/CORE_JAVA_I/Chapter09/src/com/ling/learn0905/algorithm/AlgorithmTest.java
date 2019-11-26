package com.ling.learn0905.algorithm;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 算法
 *
 * Chapter09/com.ling.learn0905.algorithm.AlgorithmTest.java
 *
 * author lingang
 *
 * createTime 2019-11-26 21:53:14
 *
 */
public class AlgorithmTest {
	public static void main(String[] args) {
		/* 1. 列表排序：首先将列表传入数组，数组排好序再将元素复制回列表 */
		System.out.println("排序算法");
		List<String> strs = Arrays.asList("D", "B", "A", "C", "F", "G", "E");
		System.out.println("初始化顺序：" + strs);
		/* Collections.sort方法接收List类型的列表参数，进行排序 */
		Collections.sort(strs);
		System.out.println("排序后顺序：" + strs);
		Collections.shuffle(strs);// 打乱顺序
		System.out.println("再打乱顺序：" + strs);
		/* 任何属于List类型的对象本身也有排序方法，实际上Collections.sort方法调用的就是列表的sort方法 */
		strs.sort(Comparator.comparing(String::valueOf));
		System.out.println("用列表排序：" + strs);
		/* 可以使用Comparator.reverseOrder方法获得一个可以逆向排序的比较器 */
		strs.sort(Comparator.reverseOrder());
		System.out.println("逆向排序后：" + strs);

		/* 2. 查找：Collections.binarySearch方法使用二分查找算法查找目标对象 */
		System.out.println("查找算法");
		System.out.println("在"+strs+"中正序查找E的位置："+Collections.binarySearch(strs, "E"));// 输出：-8，查找失败，因为当前list是倒叙的
		System.out.println("在"+strs+"中逆序查找E的位置："+Collections.binarySearch(strs, "E", Comparator.reverseOrder()));// 输出：2，查找成功

		/* 3. 其他算法 */
		System.out.println("其他算法");
		List<String> newList = new ArrayList<String>();
		newList.addAll(strs);
		newList.add("D");
		newList.add("C");
		System.out.println("列表初始值：" + newList);
		/* 1) 获取最大最小值 */
		System.out.println("获取最小值：" + Collections.min(newList));
		System.out.println("获取最大值：" + Collections.max(newList));
		System.out.println("获取最小值(使用逆序比较器)：" + Collections.min(newList, Comparator.reverseOrder()));
		System.out.println("获取最大值(使用逆序比较器)：" + Collections.max(newList, Comparator.reverseOrder()));
		/* 2) 列表复制-将第二个列表参数的元素复制到第一个列表参数对应位置进行替换 */
		Collections.copy(newList, Arrays.asList("g", "f", "e"));
		System.out.println("复制后列表：" + newList);

		/* 3)查找子列表 */
		System.out.println(
				"[D,C]在" + newList + "中的第一个位置：" + Collections.indexOfSubList(newList, Arrays.asList("D", "C")));// 3
		System.out.println(
				"[D,C]在" + newList + "中的最后一个位置：" + Collections.lastIndexOfSubList(newList, Arrays.asList("D", "C")));// 7
		System.out.println(
				"[C,D]在" + newList + "中的最后一个位置：" + Collections.indexOfSubList(newList, Arrays.asList("C", "D")));// -1，找不到子列表

		/* 4) 交换列表中两个元素的位置 */
		Collections.swap(newList, 5, 6);
		System.out.println("交换位置后：" + newList);

		/* 5)逆置列表中元素的位置 */
		Collections.reverse(newList);
		System.out.println("逆置顺序后：" + newList);

		/* 6) 循环移动元素位置，尾部元素移动到头部 */
		Collections.rotate(newList, 2);
		System.out.println("元素循环移动2个位置后：" + newList);

		/* 7) 统计元素出现的次数 */
		System.out.println("A出现的次数：" + Collections.frequency(newList, "A"));
		System.out.println("D出现的次数：" + Collections.frequency(newList, "D"));

		/* 8)检查两个列表是否无交集 */
		System.out.println(newList + "与[X,Y,Z]是否无交集：" + Collections.disjoint(newList, Arrays.asList("X", "Y", "Z")));// true
																														// 无交集
		System.out.println(newList + "与[A,B,C]是否无交集：" + Collections.disjoint(newList, Arrays.asList("A", "B", "C")));// false

		/* 9)删除满足条件的元素 */
		newList.removeIf(e -> e.equals(e.toLowerCase()));// 删除小写字母
		System.out.println("删除小写字母后：" + newList);

		/* 10)处理满足条件的元素 */
		newList.replaceAll(e -> {
			if (e.compareTo("C") < 0)
				return e.toLowerCase();
			else
				return e;
		});
		System.out.println("将小于C的元素转为小写：" + newList);

		/* 11) 替换算法 */
		Collections.replaceAll(newList, "D", "d");
		System.out.println("d替换D后：" + newList);
		
		/*12)批操作-从列表1中删除列表2中出现的所有元素*/
		System.out.print("从"+newList+"中删除[d,a,d,y]中出现的元素后：");
		newList.removeAll(Arrays.asList("d","a","d","y"));
		System.out.println(newList);
		
		/*13)批操作-从列表1中删除列表2中未出现的所有元素，实际上得到了列表1和列表2的交集*/
		System.out.print("从"+newList+"中删除[B,B,C]中未出现的元素后：");
		newList.retainAll(Arrays.asList("B","B","C"));
		System.out.println(newList);

		/* 14) 列表填充 */
		newList = new ArrayList<>(Arrays.asList("A","B","C"));
		Collections.fill(newList, "*");
		System.out.println("[A,B,C]使用*填充列表后：" + newList);

	}
}
