package com.ling.learn0805.genericandjvm;

import java.util.ArrayList;

/**
 * 编译器翻译泛型
 * 
 * 1. 编译器翻译泛型表达式：编译器在必要时会插入强制类型转换的字节码，然后让虚拟机执行，见注释1.
 * 
 * 2. 编译器翻译泛型方法：编译器在必要时会生成桥方法来保持多态，见注释2.
 * 
 * 3. 虚拟机能正确处理在一个类中有两个方法签名相同的方法的情况，见注释3.
 * 
 * 
 *
 * Chapter8/com.ling.learn0805.genericandjvm.GenericAndCompiler.java
 *
 * author lingang
 *
 * createTime 2019-11-13 00:53:44
 *
 */
public class GenericAndCompiler {
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		ArrayList<String> strs = new ArrayList<String>() {
			{
				add("aa");
			}
		};
		/*
		 * 1. 由于JVM会擦除ArrayList.get方法的返回类型参数，返回Object类型，
		 * 所以只能由编译器来对get方法的返回类型进行强制类型转换；编译器会自动插入String的强制类型转换字节码，
		 * 所以下面这句代码被编译器翻译为两条虚拟机指令： 1. 调用ArrayList.get方法 2.
		 * 将返回的Object类型强制转换为String类型
		 */
		String item0 = strs.get(0);
		System.out.println(item0);

		/*
		 * 2. 泛型擦除和多态的冲突，考虑下面的情况：
		 * 
		 * 变量a被声明为A<String>类型，而A<String>类型的泛型会被虚拟机擦除变为A，A中的被擦除泛型后的方法为fun1(Object
		 * )， 所以B中也有继承自A的fun1(Object)方法，然而B中继承自A的方法实际为fun1(String)，由于a实际上是B的对象，
		 * 所以为了对fun1的调用具有多态性，变量a应该调用B的fun1(String)方法。但是a是被声明为A的，所以只能调B的fun1(
		 * Object)方法，那怎样才能调到B的fun1(String)方法呢，只能让编译器来做手脚，这里编译器会在B中自动生产一个桥方法：
		 * public void fun1(Object o) {fun1((String)o);}，这样就能调到B的fun1(string)方法了
		 */
		A<String> a;
		a = new B();
		a.fun1("bb");

		/*
		 * 3. 泛型可能导致在编译器层面出现两个方法名和参数列表一样，
		 * 返回类型不一样的方法，这两个方法写在源码中是不合法的，但是虚拟机能正确处理这种情况。
		 * 出现这种情况的过程：A中的fun2方法的泛型被擦除为Object fun2()，所以B会从A中继承得到Object
		 * fun2()方法；而B中本身也有String fun2()方法，这导致B中有两个方法签名一样的方法
		 */
		a.fun2();
	}
}

class A<T> {// 泛型父类
	public void fun1(T t) {
		System.out.println("call A.func1");
	}

	public T fun2() {
		return null;
	}
}

class B extends A<String> {// 继承泛型父类的子类

	@Override
	public void fun1(String t) { // 重写父类方法
		System.out.println("call B.func1");
	}

	@Override
	public String fun2() {// 重写父类方法
		return null;
	}

}
