package com.ling.learn0501.superandsubclass;

/**
 * final关键字
 * 
 * 1. final关键字修饰的类不能被继承
 * 
 * 2. final类只是将类中所有方法变成final方法，但是类中的域没被变成final域
 * 
 * 3. 父类中的final方法不能被重写
 *
 * Chapter5/com.ling.learn0501.superandsubclass.FinalKeywordTest.java
 *
 * author lingang
 *
 * createTime 2019-10-16 01:28:10
 *
 */
// public class FinalKeywordTest extends FClass{ // final关键字修饰的类不能被继承
public class FinalKeywordTest extends FinalMethodClass {
	public static void main(String[] args) {
		FClass fc = new FClass();
		System.out.println(fc.name);
		fc.name = "lzy"; // final类只是将类中所有方法变成final方法，但是类中的域没被变成final域
		System.out.println(fc.name);
	}

	// void f() { // final方法不能被重写
	//
	// }
}

final class FClass {
	String name = "ling";

	void f() {

	}
}

class FinalMethodClass {
	final void f() {

	}
}
