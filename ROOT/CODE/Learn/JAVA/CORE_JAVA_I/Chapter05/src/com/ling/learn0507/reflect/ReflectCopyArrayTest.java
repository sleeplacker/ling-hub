package com.ling.learn0507.reflect;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 使用反射机制拷贝数组，可改变拷贝后的数组大小
 * 
 * 为什么要用反射机制来拷贝数组：因为如果不使用反射机制来知道运行时数组元素的类型，那拷贝方法的返回的类型只能被初始化为Object[]类型，这样就不能将返回类型转换为其他对象数组类型，如果要返回T[]这样的数组类型，
 * 则初始化返回数组的时候就要用T[]类型初始化，而不是Object[]，所以需要使用反射机制来知道运行时数组元素的类型
 *
 * Chapter5/com.ling.learn0507.reflect.ReflectCopyArrayTest.java
 *
 * author lingang
 *
 * createTime 2019-10-21 12:36:39
 *
 */
public class ReflectCopyArrayTest {
	public static void main(String[] args) {
		String[] sarr = { "a", "b", "c" };
		int[] iarr = { 1, 4, 7 };
		/*
		 * 下面一行运行会出： java.lang.ClassCastException: [Ljava.lang.Object; cannot be
		 * cast to [Ljava.lang.String
		 * 
		 * 因为badCopyOf中返回的类型初始化的时候是Object[]类型，不能转换为其他对象数组类型，如果要返回T[]这样的数组类型，
		 * 则初始化返回数组的时候就要用T[]类型初始化，而不是Object[]
		 */
		// String[] newSArrBad = (String[]) badCopyOf(sarr, 2);
		String[] newSArr = (String[]) goodCopyOf(sarr, 4);
		int[] newIArr = (int[]) goodCopyOf(iarr, 2);
		System.out.println(Arrays.toString(newSArr));
		System.out.println(Arrays.toString(newIArr));
	}

	@SuppressWarnings("unused")
	private static Object[] badCopyOf(Object[] srcArray, int newLength) {
		if (srcArray == null)
			return null;
		Object[] newArray = new Object[newLength];
		for (int i = 0; i < srcArray.length && i < newLength; ++i) {
			newArray[i] = srcArray[i];
		}
		return newArray;
	}

	private static Object goodCopyOf(Object srcArray, int newLength) { // 考虑到基本数据类型数组，参数和返回类型都必须是Object类型，而不能是Object[]类型
		if (srcArray == null)
			return null;
		Class cl = srcArray.getClass();
		Object newArray = Array.newInstance(cl.getComponentType(), newLength);
		for (int i = 0; i < Array.getLength(srcArray) && i < newLength; ++i) {
			Array.set(newArray, i, Array.get(srcArray, i));
		}
		return newArray;
	}
}
