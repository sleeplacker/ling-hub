package com.ling.learn0603.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 构造器引用-仍然是只能用于需要函数式接口的位置
 * 
 * 用法1：Class::new 等价于 (a,b,c...) -> new Class(a,b,c...)，选用哪个构造器根据上下文判断
 * 
 * 用法2：Class[]::new 等价于 len -> new Class[len]，作用：让Stream.toArray方法能返回T[]类型数组而不是Object[]类型数组
 * 
 *
 * Chapter6/com.ling.learn0603.lambda.ConstructorReference.java
 *
 * author lingang
 *
 * createTime 2019-10-25 00:47:40
 *
 */
public class ConstructorReference {
	public static void main(String[] args) {
		List<String> names = Arrays.asList("ling", "lzy");
		// 相当于 str -> new StringBuilder(str)
		Stream<StringBuilder> stream = names.stream().map(StringBuilder::new);
		List<StringBuilder> sbs = stream.collect(Collectors.toList());
		System.out.println(sbs);

		// 使用数组类型建立构造器引用
		Stream<StringBuilder> stream2 = names.stream().map(StringBuilder::new);
		// 相当于 len -> new StringBuilder[len]
		StringBuilder[] sbs2 = stream2.toArray(StringBuilder[]::new);
		System.out.println(Arrays.toString(sbs2));
	}
}
