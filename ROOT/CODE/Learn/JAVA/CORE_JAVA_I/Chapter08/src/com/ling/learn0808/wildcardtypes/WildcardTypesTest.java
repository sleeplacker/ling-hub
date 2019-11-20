package com.ling.learn0808.wildcardtypes;

/**
 * 泛型通配符类型
 *
 * Chapter8/com.ling.learn0808.wildcardtypes.WildcardTypesTest.java
 *
 * author lingang
 *
 * createTime 2019-11-20 11:46:21 
 *
 */
public class WildcardTypesTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		/*
		 * 0. 前提-方法类型协变规则：
		 * 
		 * 参数类型：可以传入方法定义参数类型的子类型
		 * 
		 * 返回类型：可以赋值给方法定义返回类型的父类型
		 */
		SuperC sc = fun1(new SubC());

		/* 1. 泛型的通配符类型不能用于泛型方法 */
		// WildcardTypesTest.<? extends C>fun2(new C());

		/* 2. 通配符的子类限定类型只能用于方法返回，但是和不用是一样的效果，都可以赋值给方法定义返回类型的父类型 */
		Pair<? extends C> p = new Pair<>();
		C c = p.get();
		SuperC c1 = p.get();
		// SubC c2 = p.get();
		/*
		 * 子类限定类型不能用于方法参数，因为本身参数类型就要求是定义类型的子类型，这里参数定义类型不知道限定为哪个子类型，
		 * 所以不能传入任何特点类型的参数，只能传null
		 */
		// p.set(new SubC());// 编译错误

		/* 3. 与第2点相反，通配符的超类限定类型只能用于方法参数，而不能用户方法返回 */
		Pair<? super C> p1 = new Pair<>();
		p1.set(new C());
		p1.set(new SubC());
		// C c3 = p1.get();// 编译错误
	}

	public static C fun1(C c) {
		return null;
	}

	public static <T> T fun2(T t) {
		return null;
	}

}

class SuperC {

}

class C extends SuperC {

}

class SubC extends C {

}

class Pair<T> {
	T get() {
		return null;
	}

	void set(T t) {

	}
}