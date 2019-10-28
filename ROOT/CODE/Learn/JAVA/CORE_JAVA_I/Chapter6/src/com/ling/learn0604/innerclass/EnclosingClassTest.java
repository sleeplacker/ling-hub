package com.ling.learn0604.innerclass;

/**
 * 在静态方法中获取包含它的类的类型
 * 
 * 1. new Object(){}代表这里定义的是匿名类，所以这里调getEnclosingClass()得到的就是当前类
 *
 * Chapter6/com.ling.learn0604.innerclass.EnclosingClassTest.java
 *
 * author lingang
 *
 * createTime 2019-10-29 02:11:08
 *
 */
public class EnclosingClassTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		// 下面写法会报空指针异常，因为Object类是已经存在的类，它getEnclosingClass()是空的
		Class cl1 = new Object().getClass().getEnclosingClass(); // null
		// System.out.println(cl1.getName());

		/* new Object(){}代表这里定义的是匿名类，所以这里调getEnclosingClass()得到的就是当前类 */
		Class cl2 = new Object() {
		}.getClass().getEnclosingClass();
		System.out.println(cl2.getName());
	}
}
