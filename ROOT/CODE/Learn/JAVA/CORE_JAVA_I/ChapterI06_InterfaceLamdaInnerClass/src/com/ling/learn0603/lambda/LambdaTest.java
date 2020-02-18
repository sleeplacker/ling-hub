package com.ling.learn0603.lambda;

import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import javax.swing.JOptionPane;
import javax.swing.Timer;

/**
 * Lambda表达式
 * 
 * 1. lambda表达式只能出现在需要函数式接口的位置
 * 
 * 2. 当lambda表达式没有参数时，需要提供空括号()，例如 () -> ...
 * 
 * 3. 如果可以推导出lambda表达式的参数类型，可以省略参数类型：例如：(a, b) -> ...
 * 
 * 4. 如果lambda表达式只有一个参数，且可推导其类型，可以省略小括号，例如：a -> ...
 * 
 * 5. 无需指定lambda表达式的返回类型，因为返回类型总是能推导出
 * 
 * 6. 如果lambda表达式中有分支，且其中一条分支有返回值，那么所有分支都应该有返回值，否则编译错误
 *
 * Chapter6/com.ling.learn0603.lambda.LambdaTest.java
 *
 * author lingang
 *
 * createTime 2019-10-24 23:02:56
 *
 */
public class LambdaTest {
	public static void main(String[] args) {
		String[] names = { "EEE", "AAAA", "BBB", "CCCC", "DD" };
		System.out.println(Arrays.toString(names));
		Arrays.sort(names, (self, other) -> self.length() - other.length());// 使用lambda表达式代替Comparator比较器
		System.out.println(Arrays.toString(names));
		Arrays.sort(names, (self, other) -> other.compareTo(self)); // 使用lambda表达式按字典序逆序排列字符串
		System.out.println(Arrays.toString(names));

		new Timer(5000, event -> { // 只有1个参数且可推导时可以省略()，直接使用变量名 ->
			System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			Toolkit.getDefaultToolkit().beep();
		}).start();
		JOptionPane.showMessageDialog(null, "结束任务");
		System.exit(0);
	}
}
