package com.ling.learn0605.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Random;

/**
 * 代理
 *
 * Chapter6/com.ling.learn0605.proxy.ProxyTest.java
 *
 * author lingang
 *
 * createTime 2019-10-29 22:35:56
 *
 */
public class ProxyTest {
	public static void main(String[] args) {
		/* 填充数字数组 */
		Object[] items = new Object[1000];
		for (int i = 0; i < 1000; ++i) {
			Integer ival = i + 1;
			InvocationHandler ih = new TraceHandler(ival);
			Object obj = Proxy.newProxyInstance(null, new Class[] { Comparable.class }, ih);
			/* obj对象属于运行时定义的类，类名为：com.sun.proxy.$Proxy0 */
			if (i < 2)
				/*
				 * 可以看到每次打印的类名都为：com.sun.proxy.$Proxy0，这是因为对于同一类加载器和同一接口数组，
				 * 只能有一个代理类
				 */
				System.out.println(obj.getClass().getName());
			items[i] = obj;
		}
		/* 生成随机数字作为查找目标 */
		Integer target = new Random().nextInt(1000);

		/* 开始查找查找 */
		Integer result = Arrays.binarySearch(items, target); // 返回的是查找到的位置，而不是查找到的值

		/* 打印查找结果 */
		if (result > 0) {
			System.out.println(items[result]);
		}

		/*
		 * 下面是代理类通过invoke方法来调用方法时跟踪打印的信息，
		 * 可以看出除了传入接口数组中的Comparable中的compareTo方法被代理类覆盖了以外，
		 * Object类的toString方法也被覆盖了，这是因为代理类都会覆盖Object类中的toString，
		 * equals和hashCode方法， 而Object类中的其他方法都没覆盖(例如clone和getClass方法)
		 */
		// 500.compareTo(580)
		// 750.compareTo(580)
		// 625.compareTo(580)
		// 562.compareTo(580)
		// 593.compareTo(580)
		// 577.compareTo(580)
		// 585.compareTo(580)
		// 581.compareTo(580)
		// 579.compareTo(580)
		// 580.compareTo(580)
		// 580.toString()
		Class cl = Proxy.getProxyClass(null, new Class[] { Runnable.class }); // getProxyClass方法可以获得代理类
		System.out.println(cl);
		System.out.println(Proxy.isProxyClass(cl)); // true，isProxyClass方法检测一个Class对象是否属于代理类
		System.out.println(Proxy.isProxyClass(new Object[] {}.getClass()));// false
	}

}

// 调用处理器
class TraceHandler implements InvocationHandler {
	private Object item; // 调用处理器中保存要代理的对象

	/**
	 * @param item
	 */
	public TraceHandler(Object item) {
		this.item = item;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.print(item + "." + method.getName());// 打印调用的方法名
		System.out.print("("); // 打印调用参数列表
		if (args != null && args.length > 0) {
			for (int i = 0; i < args.length; ++i) {
				System.out.print(args[i]);
				if (i < args.length - 1) {
					System.out.print(", ");
				}
			}
		}
		System.out.println(")");
		return method.invoke(item, args);
	}

}
