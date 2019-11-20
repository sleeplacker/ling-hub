package com.ling.learn0806.constraintandlimit;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.function.IntFunction;

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

	/* 下面的方式才可以得到泛型类型变量的Class对象 */
	@SuppressWarnings("serial")
	public static <T> List<T> getCL(Class<T> ct) throws InstantiationException, IllegalAccessException {
		return new ArrayList<T>() {
			{
				add(ct.newInstance());
			}
		};
	}

	/* 6. 不能构造泛型数组 */
	// public static <T extends Comparable> T[] minmax(T[] ts, int length) {
	// T[] ts = new T[length]; // 编译错误
	// return mm;
	// }
	/* 可以使用下面的方式生成泛型数组 */
	public static <T extends Comparable> T[] minmax(IntFunction<T[]> cons, int length) {
		T[] ts = cons.apply(length);
		return ts;
	}

	/* 老式方法是使用反射机制生成泛型数组 */
	public static <T extends Comparable> T[] minmaxold(T... tt) {
		T[] ts = (T[]) Array.newInstance(tt.getClass().getComponentType().getClass(), tt.length);
		return ts;
	}

}

class Pair<T> {
	private T first;
	private T second;

	/* 7. 泛型类的静态域或静态方法中类型变量无需 */
	// private static T t;// 编译错误
	// public static void fun(T t) {}// 编译错误

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

/* 8. 不能抛出或捕获泛型类型的实例 */
/* 泛型类不能扩展Throwable */
// class problem<T> extends Exception{}//编译错误
// class problem<T> extends Throwable{}//编译错误
class Temp {
	public static <T extends Throwable> void temp() throws T { // 可以在异常声明中使用类型变量
		/* catch字句中不能使用类型变量 */
		// try {
		// } catch (T e) {// 编译错误
		// }
	}
}

/* 9. 消除对受查异常的检查 */
class Temp1 {
	/* 定义一个会抛出受查异常的方法 */
	public static <T extends Exception> void throwRunEx(Throwable t) throws T {
		throw (T) t;
	}

	public static void throwEx(Throwable t) throws Throwable {
		throw t;
	}

	public static void call() {
		Temp1.throwRunEx(new FileNotFoundException());// 没有提示要捕获异常
		Temp1.<RuntimeException>throwRunEx(new FileNotFoundException());// 没有提示要捕获异常，RuntimeException类型参数骗了编译器异常类型为运行时异常
		// Temp1.<FileNotFoundException>throwRunEx(new
		// FileNotFoundException());//提示要捕获异常
		// throwEx(new FileNotFoundException());// 提示要捕获异常
	}
}

/* 10. 泛型擦除后的冲突 */
class GenericConflictTest<T> {
	/*
	 * 下面的方法会有编译错误，因为equals(T)会被擦除为equals(Object)，然而该类已经从Object类中继承了该方法，所以方法名冲突，
	 * 此时应该换个方法名
	 */
	// public boolean equals(T obj) {
	// return false;
	// }

}

class A implements Comparable<String> {

	@Override
	public int compareTo(String o) {
		return 0;
	}

}

/*
 * 编译错误，因为Comparable接口被实现了两次，而且是使用不同的类型参数，那么由于擦除规则的存在，编译器会生成两个桥方法，一个是
 * public int compareTo(Object o){return compareTo(String s);}，一个是
 * public int compareTo(Object o){return compareTo(Date d);}，这就发生了冲突
 */
//class B extends A implements Comparable<Date> {
//
//}
