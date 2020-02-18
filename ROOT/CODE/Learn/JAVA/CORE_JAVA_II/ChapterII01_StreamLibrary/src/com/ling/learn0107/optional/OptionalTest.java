package com.ling.learn0107.optional;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * Optional包装器
 *
 * ChapterII01/com.ling.learn0107.optional.OptionalTest.java
 *
 * author lingang
 *
 * createTime 2020-01-13 22:06:07
 *
 */
public class OptionalTest {
	public static void main(String[] args) {
		Optional<String> os1 = Stream.<String>empty().findAny();
		os1 = Optional.empty();// 和上一行等价的写法
		os1 = Optional.ofNullable(null);// 和上二行等价的写法
		Optional<String> os2 = Optional.of("AAA");
		System.out.println(os1.orElse("空字符串"));// orElse方法在当Optional为空时返回默认值
		System.out.println(os1.orElseGet(() -> "空值"));// orElseGet方法在当Optional为空时返回函数式接口参数返回的值
		System.out.println(os2.orElse("空字符串"));

		os1.ifPresent(x -> System.out.println("值存在：" + x));
		os2.ifPresent(x -> System.out.println("值存在：" + x));// ifPresent方法在Optional不为空是访问该值

		System.out.println(os1.isPresent());
		System.out.println(os2.isPresent());// 检查是否为空

		System.out.println(os1.map(x -> "XXX"));// 这里不会返回XXX，而是返回Optional.empty，因为os1本身是空的
		System.out.println(os2.map(x -> x == null ? "NULL" : "XXX"));

		// os1.get();//java.util.NoSuchElementException: No value
		// present，如果Optional为空，则调用get方法会抛出该异常
		os2.get();

		// 将两个Optional连接成一个
		Optional<String> os3 = os1.flatMap(s -> s == null ? Optional.of("空空") : Optional.of(s));
		System.out.println(os3);
		Optional<String> os4 = os2.flatMap(s -> s == null ? Optional.of("空空") : Optional.of(s));
		System.out.println(os4);

		System.out.println(os1.orElseThrow(RuntimeException::new));// orElseThrow方法在当Optional为空时抛出函数式接口参数创建的异常
	}
}
