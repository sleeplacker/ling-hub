package com.ling.learn0109.stream2map;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 将流收集到映射表中
 *
 * ChapterII01/com.ling.learn0109.stream2map.Stream2MapTest.java
 *
 * author lingang
 *
 * createTime 2020-01-15 21:31:46
 *
 */
public class Stream2MapTest {
	public static void main(String[] args) {
		// 1. 将流转Map-最简情况
		Stream<Person> sp = Stream.of(new Person("1", "Lin"), new Person("2", "Peng"), new Person("3", "Tan"));
		Map idAndName = sp.collect(Collectors.toMap(Person::getId, Person::getName));
		System.out.println(idAndName);

		sp = Stream.of(new Person("1", "Lin"), new Person("2", "Peng"), new Person("3", "Tan"));
		Map idAndPerson = sp.collect(Collectors.toMap(Person::getId, Function.identity()));// 将流元素自身作为value值
		System.out.println(idAndPerson);

		// 2. 将流转Map-处理键冲突问题
		sp = Stream.of(new Person("1", "Lin"), new Person("2", "Peng"), new Person("3", "Tan"), new Person("3", "Lu"));
		// Map idAndName1 = sp.collect(Collectors.toMap(Person::getId,
		// Person::getName));//键重复会抛错：java.lang.IllegalStateException: Duplicate
		// key Tan
		Map idAndName1 = sp.collect(Collectors.toMap(Person::getId, Person::getName, (oldValue, newValue) -> oldValue));// 键重复时，使用旧值
		System.out.println(idAndName1);

		sp = Stream.of(new Person("1", "Lin"), new Person("2", "Peng"), new Person("3", "Tan"), new Person("3", "Lu"));
		Map idAndName2 = sp.collect(Collectors.toMap(Person::getId, Person::getName, (oldValue, newValue) -> newValue));// 键重复时，使用新值
		System.out.println(idAndName2);

		// 3. 将流转Map-指定要生成的Map类型
		sp = Stream.of(new Person("6", "Lin"), new Person("1", "Peng"), new Person("3", "Tan"), new Person("3", "Lu"));
		Map idAndName3 = sp.collect(
				Collectors.toMap(Person::getId, Person::getName, (oldValue, newValue) -> newValue, TreeMap::new));// 指定生成的Map为TreeMap
		System.out.println(idAndName3);// 结果会按键排序

		// 4. 将流转Map-以上三种Collectors.toMap方法都有toConcurrentMap并行版本
		sp = Stream.of(new Person("6", "Lin"), new Person("1", "Peng"), new Person("3", "Tan"), new Person("3", "Lu"));
		Map idAndName4 = sp.collect(Collectors.toConcurrentMap(Person::getId, Person::getName,
				(oldValue, newValue) -> newValue, ConcurrentHashMap::new));// 指定生成的Map为TreeMap
		System.out.println(idAndName4);

	}

}

class Person {
	private String id;
	private String name;

	/**
	 * @param id
	 * @param name
	 */
	public Person(String id, String name) {
		super();
		this.id = id;
		this.name = name;
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

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + "]";
	}

}
