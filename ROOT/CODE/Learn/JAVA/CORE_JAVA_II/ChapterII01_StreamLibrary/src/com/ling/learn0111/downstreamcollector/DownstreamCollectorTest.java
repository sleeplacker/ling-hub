package com.ling.learn0111.downstreamcollector;

import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 下游收集器
 *
 * ChapterII01/com.ling.learn0111.downstreamcollector.DownstreamCollectorTest.java
 *
 * author lingang
 *
 * createTime 2020-01-19 11:51:44
 *
 */
public class DownstreamCollectorTest {
	public static void main(String[] args) {
		Stream<Person> ps = Stream.of(new Person("1", "Lin"), new Person("2", "Peng"), new Person("3", "Tan"),
				new Person("3", "Lu"));

		// 指定分组中每一组的类型为Set，而不是默认的List
		Map<String, Set<Person>> group1 = ps.collect(Collectors.groupingBy(Person::getId, Collectors.toSet()));
		System.out.println(group1.get("1").getClass().getSimpleName() + ": " + group1);

		// 指定分组中每一组的类型为列表长度Long类型，而不是默认的List
		ps = Stream.of(new Person("1", "Lin"), new Person("2", "Peng"), new Person("3", "Tan"), new Person("3", "Lu"));
		Map<String, Long> group2 = ps.collect(Collectors.groupingBy(Person::getId, Collectors.counting()));
		System.out.println(group2.get("1").getClass().getSimpleName() + ": " + group2);

		// 指定分组中每一组的类型为列表中所有年龄总和Integer类型，而不是默认的List
		ps = Stream.of(new Person("1", "Lin", 3), new Person("2", "Peng", 4), new Person("3", "Tan", 5),
				new Person("3", "Lu", 9));
		Map<String, Integer> group3 = ps
				.collect(Collectors.groupingBy(Person::getId, Collectors.summingInt(Person::getAge)));
		System.out.println(group3.get("1").getClass().getSimpleName() + ": " + group3);

		// 指定分组中每一组的类型为列表中年龄最大的Person并包装到Optional对象，而不是默认的List
		ps = Stream.of(new Person("1", "Lin", 3), new Person("2", "Peng", 4), new Person("3", "Tan", 5),
				new Person("3", "Lu", 9));
		Map<String, Optional<Person>> group4 = ps
				.collect(Collectors.groupingBy(Person::getId, Collectors.maxBy(Comparator.comparing(Person::getAge))));// 相反，minBy返回最小年龄的Person
		System.out.println(group4.get("1").getClass().getSimpleName() + ": " + group4);

		// 指定分组中每一组的类型为列表中姓名最长的名字String并包装到Optional对象，而不是默认的List，且收集的不是Person对象，而是Person对象的一部分
		ps = Stream.of(new Person("1", "Lin", 3), new Person("2", "Peng", 4), new Person("3", "Tan", 5),
				new Person("3", "Lu", 9));
		Map<String, Optional<String>> group5 = ps.collect(Collectors.groupingBy(Person::getId,
				Collectors.mapping(Person::getName, Collectors.maxBy(Comparator.comparing(String::length)))));
		System.out.println(group5.get("1").getClass().getSimpleName() + ": " + group5);

		// 指定分组中每一组的类型为列表中姓名放到Set中，而不是默认的List，且收集的不是Person对象，而是Person对象的一部分
		ps = Stream.of(new Person("1", "Lin", 3), new Person("2", "Peng", 4), new Person("3", "Tan", 5),
				new Person("3", "Lu", 9));
		Map<String, Set<String>> group6 = ps
				.collect(Collectors.groupingBy(Person::getId, Collectors.mapping(Person::getName, Collectors.toSet())));
		System.out.println(group6.get("1").getClass().getSimpleName() + ": " + group6);

		// 指定分组中每一组的类型为列表中年龄的统计信息，而不是默认的List
		ps = Stream.of(new Person("1", "Lin", 3), new Person("2", "Peng", 4), new Person("3", "Tan", 5),
				new Person("3", "Lu", 9));
		Map<String, IntSummaryStatistics> group7 = ps
				.collect(Collectors.groupingBy(Person::getId, Collectors.summarizingInt(Person::getAge)));
		System.out.println(group7.get("1").getClass().getSimpleName() + ": " + group7);
	}
}

class Person {
	private String id;
	private String name;
	private int age;

	/**
	 * @param id
	 * @param name
	 */
	public Person(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	/**
	 * @param id
	 * @param name
	 * @param age
	 */
	public Person(String id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", age=" + age + "]";
	}

}
