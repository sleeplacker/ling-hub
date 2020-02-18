package com.ling.learn0603.lambda;

import java.util.Arrays;
import java.util.Comparator;

/**
 * 继续研究Comparator
 * 
 * Comparator作为一个函数式接口，虽然只有一个抽象方法，但是还有多个有用的静态方法和默认实现方法，列举其中的几个：
 * 
 * 1. 静态方法comparing：提取对象中可比较的域(例如String域)，并生成一个比较器
 * 
 * 2. 接口默认实现方法thenComparing：对comparing方法中比较相同的元素进行二次比较
 * 
 * 3. 静态方法comparingInt：提取对象中可比较的域的属性(例如域String的长度)，并生成一个比较器
 * 
 * 4. 静态方法nullsFirst：生成可以处理空值的比较器，即比较的元素为空，也不会出空指针异常
 * 
 * 5. 静态方法naturalOrder：为比较元素生成一个正序比较器(条件是比较元素必须实现Comparable接口)，类似的，reverseOrder方法会生成一个逆序比较器
 *
 * Chapter6/com.ling.learn0603.lambda.ComparatorAgainTest.java
 *
 * author lingang
 *
 * createTime 2019-10-25 23:49:39
 *
 */
public class ComparatorAgainTest {
	public static void main(String[] args) {
		Person[] pers = { new Person("lin", "neng", "zeyi"), new Person("tan", null, "xianqiong"),
				new Person("peng", null, "mingzhu"), new Person("lin", "daidai", "gang") };
		System.out.println(Arrays.toString(pers));
		Arrays.sort(pers, Comparator.comparing(Person::getFirstName));// 按姓的字典序排序，comparing方法参数等价于p
																		// ->
																		// p.getFirstName()
		System.out.println(Arrays.toString(pers));
		Arrays.sort(pers, Comparator.comparing(Person::getFirstName).thenComparing(Person::getLastName));// 如果同姓，则按名的字典序排序
		System.out.println(Arrays.toString(pers));
		Arrays.sort(pers, Comparator.comparing(Person::getFirstName, (p1, p2) -> p1.length() - p2.length()));// 按姓的长度排序
		System.out.println(Arrays.toString(pers));
		Arrays.sort(pers, Comparator.comparingInt(p -> p.getFirstName().length()));// 按姓的长度排序-简单写法
		System.out.println(Arrays.toString(pers));
		// Arrays.sort(pers, Comparator.comparing(Person::getMiddleName,(p1, p2)
		// -> p1.length() - p2.length()));//按辈分字典序排序，报空指针，因为存在备份为空的姓名
		Arrays.sort(pers, Comparator.comparing(Person::getMiddleName,
				Comparator.nullsFirst((p1, p2) -> p1.length() - p2.length())));// Comparator.nullsFirst静态方法可以处理比较元素为空的情况
		System.out.println(Arrays.toString(pers));
		Arrays.sort(pers,
				Comparator.comparing(Person::getMiddleName, Comparator.nullsFirst(Comparator.naturalOrder())));// Comparator.naturalOrder()会生成一个正序比较器
		System.out.println(Arrays.toString(pers));
		Arrays.sort(pers,
				Comparator.comparing(Person::getMiddleName, Comparator.nullsFirst(Comparator.reverseOrder())));// Comparator.reverseOrder()生成一个逆序比较器
		System.out.println(Arrays.toString(pers));

	}

}

class Person {
	private String firstName;
	private String middleName;
	private String lastName;

	public Person(String firstName, String middleName, String lastName) {
		super();
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	@Override
	public String toString() {
		return firstName + (middleName == null ? "" : middleName) + lastName;
	}

}