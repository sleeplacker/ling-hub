package com.ling.learn0604.innerclass;

import java.util.ArrayList;

/**
 * 匿名内部类
 * 
 * 1. 由于构造器的名字与类名相同，而匿名类没有类名，所以匿名类不能有构造器，构造器参数会传递给父类构造器。 如果是实现接口的匿名类，则不能有任何构造参数
 * 
 * 2. 匿名内部类可以继承自类，可以在匿名类中重写父类方法
 * 
 * 3. 匿名内部类可以实现自接口，实现接口方法
 * 
 * 4. 容器对象的双括号初始化-外层括号就是匿名子类，内层括号是对象构造块，可以在构造块中向容器中加入元素
 *
 * Chapter6/com.ling.learn0604.innerclass.AnonymousInnerClassTest.java
 *
 * author lingang
 *
 * createTime 2019-10-29 00:48:15
 *
 */
public class AnonymousInnerClassTest {
	@SuppressWarnings("serial")
	public static void main(String[] args) {
		call1(new SuperClass()); // 直接传入类对象
		/* 匿名内部类可以继承自类，可以在匿名类中重写父类方法 */
		call1(new SuperClass() {
			@Override
			void fun1() {
				System.out.println("call override fun1 in innerclass");
			}
		});

		/* 匿名内部类可以实现自接口，实现接口方法 */
		call2(new Interfaces() {
			@Override
			public void fun2() {
				System.out.println("call fun2 define in innerclass");
			}
		});

		/* 双括号初始化-外层括号就是匿名子类，内层括号是对象构造块 */
		ArrayList<String> friends = new ArrayList<String>() {
			{
				add("ling");
				add("lzy");
				add("penglin");
				add("txq");
			}
		};
		invite(friends);

		/* 对象构造块例子回忆 */
		System.out.println(new BlockTest().getIndex());

	}

	public static void call1(SuperClass sc) {
		sc.fun1();
	}

	public static void call2(Interfaces sc) {
		sc.fun2();
	}

	public static void invite(ArrayList<String> friends) {
		System.out.println(friends);
	}
}

class SuperClass {
	void fun1() {
		System.out.println("call SuperClass.func1");
	}
}

interface Interfaces {
	void fun2();
}

class BlockTest {
	private int index = 0;
	{ // 对象构造块
		add();
		add();
	}

	void add() {
		++index;
	}

	public int getIndex() {
		return index;
	}

}
