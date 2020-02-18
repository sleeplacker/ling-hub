package com.ling.learn0701.exceptionsintroduce;

import java.io.IOException;

/**
 * 子类中覆盖父类有抛异常的方法
 * 
 * 1. 父类方法有声明受查异常时，子类覆盖该方法时可以不声明任何异常；
 * 		子类方法声明的受查异常类型必须与父类声明异常一致或者是其子类，但是可以声明任何非受查异常类型
 * 
 * 2. 父类方法有声明非受查异常时，子类覆盖该方法时可以不声明任何异常，也可以声明任何为任何非受查异常类型
 *
 * Chapter7/com.ling.learn0701.exceptionsintroduce.ExceptionInSuperAndSubClass.java
 *
 * author lingang
 *
 * createTime 2019-10-31 01:50:55
 *
 */
public class ExceptionInSuperAndSubClass {

}

class ExceptionSuper {
	void f1() throws IOException {

	}

	void f2() throws IndexOutOfBoundsException {

	}
}

class ExceptionSub extends ExceptionSuper {
	/*
	 * 父类方法有声明受查异常时，子类覆盖该方法时可以不声明任何异常；子类方法声明的受查异常类型必须与父类声明异常一致或者是其子类，
	 * 但是可以声明任何非受查异常类型
	 */
	// void f1() { // OK
	//
	// }

	/*
	 * Exception Exception is not compatible with throws clause in
	 * ExceptionSuper.f1()
	 * 
	 * 
	 */
	// void f1() throws Exception {// Error
	//
	// }

	// void f1() throws FileNotFoundException { // OK
	//
	// }
	void f1() throws RuntimeException { // OK

	}

	/* 父类方法有声明非受查异常时，子类覆盖该方法时可以不声明任何异常，也可以声明任何为任何非受查异常类型 */
	// void f2() { // OK
	//
	// }
	// void f2() throws RuntimeException { // OK
	//
	// }
	// void f2() throws ArrayIndexOutOfBoundsException { // OK
	//
	// }
	void f2() throws Error { // OK

	}
}