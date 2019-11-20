package com.ling.learn0601.interfaces;

/**
 * 处理接口默认方法冲突
 * 
 * 1. 当一个类继承的父类和实现的接口中有相同名称和参数列表相同的方法，且接口中的方法有默认实现，此时只会使用父类中的方法，而忽略接口中的默认方法
 *
 * 2. 当实现的多个接口中存在两个同名且参数列表一样的方法，且不写自己的实现，则需要指定使用哪个接口的方法；也可以重写自己的实现，不必选择接口
 *
 * 3. 当实现的接口中有同名且参数列表相同的方法，但是只有其中有一个有默认实现，这时不会继承这个有默认实现的方法，仍然需要用户来解决冲突：
 * 		实现无默认实现的方法或重新含默认实现的方法
 * 
 * 4. 当实现的接口只包含同名且参数列表相同的方法，但都没有默认实现，则只需要为这个方法写一个实现就可以，或者不写实现，这样本身就是抽象类
 *
 * Chapter6/com.ling.learn0601.interfaces.InterfaceConflictTest.java
 *
 * author lingang
 *
 * createTime 2019-10-24 00:11:10
 *
 */
public class InterfaceConflictTest extends E implements A {
	/*
	 * 1. 当一个类继承的父类和实现的接口中有相同名称和参数列表相同的方法，且接口中的方法有默认实现，此时只会使用父类中的方法，而忽略接口中的默认方法
	 */
	public static void main(String[] args) {
		System.out.println(new InterfaceConflictTest().getName()); // E，调用了父类中的方法
	}
}

class One implements A, B {

	@Override
	public String getName() {
		// return B.super.getName(); //
		// 2. 当实现的多个接口中存在两个同名且参数列表一样的方法，且不写自己的实现，则需要指定使用哪个接口的方法
		return "Z";// 也可以重写自己的实现，不必选择接口
	}

}

class Two implements A, C {

	// The default method getName() inherited from A conflicts with another
	// method inherited from C
	/*
	 * 3. 当实现的接口中有同名且参数列表相同的方法，但是只有其中有一个有默认实现，这时不会继承这个有默认实现的方法，仍然需要用户来解决冲突：
	 * 实现无默认实现的方法或重新含默认实现的方法
	 */
	@Override
	public String getName() {
		return A.super.getName();
	}
}

class Three implements C, D {
	/* 4. 当实现的接口只包含同名且参数列表相同的方法，但都没有默认实现，则只需要为这个方法写一个实现就可以，或者不写实现，这样本身就是抽象类 */
	@Override
	public String getName() {
		return null;
	}

}

interface A {
	default String getName() {
		return "A";
	}
}

interface B {
	default String getName() {
		return "B";
	}
}

interface C {
	String getName();
}

interface D {
	String getName();
}

class E {
	public String getName() {
		return "E";
	}
}
