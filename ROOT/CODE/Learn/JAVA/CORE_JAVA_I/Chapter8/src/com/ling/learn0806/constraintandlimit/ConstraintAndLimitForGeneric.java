package com.ling.learn0806.constraintandlimit;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * 泛型使用的约束和局限性
 *
 * Chapter8/com.ling.learn0806.constraintandlimit.ConstraintAndLimitForGeneric.java
 *
 * author lingang
 *
 * createTime 2019-11-14 19:10:05
 *
 */
public class ConstraintAndLimitForGeneric {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*
		 * 1. 基本类型不能作为泛型类型参数；原因是泛型会被擦除为Object类型，而基本类型不能存储在Object类型中，
		 * 这也说明基本类型不会自动装箱为泛型类型
		 */
		// ArrayList<int> iList = new ArrayList<>(); // 编译错误
		List<String> strs = new ArrayList<>();

		/*
		 * 2. 运行时类型检查只适用于非泛型类型，因为类型参数被擦除了
		 */
		// if(strs instanceof List<String>){} //编译错误，使用List<T>也会出现相同错误
		List<Integer> ints = new ArrayList<>();
		System.out.println(strs.getClass() == ints.getClass());// true，因为List<String>和List<Integer>被擦除后都属于List.class

		/*
		 * 3. 不能创建泛型类型的数组，原因是数组会记住元素类型：例如Pair<String>[]类型的数组sp会被擦除为Pair[]，
		 * 然后该数组会记住元素类型为Pair，如果此时往数组中存入其他类型如sp[0]="aa"会报错，
		 * 因为数组记住的类型是Pair类型而不是String类型，但是如果是sp[0]=new
		 * Pair<Integer>，就会通过数组检查，因为Pair<Integer>也被擦除为Pair了，
		 * 所以泛型的擦除会使数组的元素检查机制无效， 所以初始化数组时不能使用泛型类型。不过可以声明变量为泛型类型数组，
		 * 只是不能通过泛型类型初始化数组
		 */
		// Pair<String>[] sp = new Pair<String>[10];// 编译错误：Cannot create a
		// generic array of Pair<String>
		Pair<String>[] sp1 = new Pair[10];// 可以使用原始类型来初始化数组
		Pair<String>[] sp2 = (Pair<String>[]) new Pair<?>[10];// 可以声明通配类型，再进行类型转换
		sp1[0] = new Pair<String>("left", "right");
		// sp1[1] = new Pair<Integer>(0,9);//编译错误，//
		// 由于声明为Pair<String>类型，所以编译器会检查类型
		List<Pair<String>> pList = new ArrayList<Pair<String>>();// 可以使用ArrayList创建泛型
																	// 类型的列表

	}

	/*
	 * 4. 第3点说明不能创建泛型类型元素的数组，而方法中可变个数参数列表其实也是数组，但是对方法中可变个数参数列表比较宽松，允许参数类型为泛型类型，
	 * 当然这样做虽然方便，但是就会有第3点中描述的问题，这里数组的元素类型检查机制会失效
	 */
	public static <T> void addAll(Collection<T> coll, T... ts) {// 编译通过
		coll.addAll(Arrays.asList(ts));
	}

	/* 5. 不能实例化类型变量 */
	public static <T> void newT() {
		// Object o = new T();//编译错误
		// Object[] os = new T[10];//编译错误
		// Class cl = T.class;//编译错误
	}

	/*下面的方式才可以得到泛型类型变量的Class对象*/
	@SuppressWarnings("serial")
	public static <T> List<T> getCL(Class<T> ct) throws InstantiationException, IllegalAccessException {
		return new ArrayList<T>() {
			{
				add(ct.newInstance());
			}
		};
	}

}

class Pair<T> {
	private T first;
	private T second;

	/**
	 * @param first
	 * @param second
	 */
	public Pair(T first, T second) {
		this.first = first;
		this.second = second;
	}

	@Override
	public String toString() {
		return "Pair [first=" + first + ", second=" + second + "]";
	}

}
