package com.ling.learn0603.lambda;

import java.awt.event.ActionEvent;
import java.util.Arrays;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * 方法引用：与lambda一样，也只能用在需要函数接口的位置
 * 
 * 三种引用方式：
 * 
 * 1. 对象::实例方法，object::fun(a,b,c...) 等价于 (a,b,c...) -> object.fun(a,b,c...) 
 * 
 * 2. 类::静态方法，Class::fun(a,b,c...) 等价于 (a,b,c...) -> Class.fun(a,b,c...) 
 * 
 * 3. 类::实例方法，Class::fun(a,b,c...) 等价于 (a,b,c...) -> a.fun(b,c...)
 * 
 * 4. this::实例方法/super::实例方法，this::fun(a,b,c...) 等价于 (a,b,c...) -> this.fun(a,b,c...)，super::fun(a,b,c...) 等价于 (a,b,c...) -> super.fun(a,b,c...)
 *
 * Chapter6/com.ling.learn0603.lambda.MethodReference.java
 *
 * author lingang
 *
 * createTime 2019-10-24 23:51:00
 *
 */
public class MethodReference extends SuperC {
	public static void main(String[] args) {
		String[] ss = { "BBB", "AAAA", "cc", "D" };
		System.out.println(Arrays.toString(ss));
		Arrays.sort(ss, String::compareTo); // 相当于 (a, b) ->
											// a.compareTo(b)，因为compareTo是现成的方法，所以可以直接将该方法传入并使用
		System.out.println(Arrays.toString(ss));
		Arrays.sort(ss, String::compareToIgnoreCase);// 按字典序排列字符串，忽略大小写，相当于 (a,
														// b) ->
														// a.compareToIgnoreCase(b)
		System.out.println(Arrays.toString(ss));

		new MethodReference().sayHello();
		JOptionPane.showMessageDialog(null, "shut down");
		System.exit(0);
	}

	public void sayHello() {
		new Timer(3000, super::greet).start(); // 可以使用super::method来使用父类方法
	}

}

class SuperC {
	public void greet(ActionEvent e) {
		System.out.println("Say Hello in Super Calss !");
	}
}
