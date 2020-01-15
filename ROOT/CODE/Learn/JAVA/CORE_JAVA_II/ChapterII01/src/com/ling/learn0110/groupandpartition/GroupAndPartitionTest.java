package com.ling.learn0110.groupandpartition;

import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 分组和分区
 *
 * ChapterII01/com.ling.learn0110.groupandpartition.GroupAndPartitionTest.java
 *
 * author lingang
 *
 * createTime 2020-01-15 22:53:24
 *
 */
public class GroupAndPartitionTest {
	public static void main(String[] args) {
		// 分组
		Map<String, List<Locale>> countryToLocales = Stream.of(Locale.getAvailableLocales())
				.collect(Collectors.groupingBy(Locale::getCountry));
		List<Locale> chineseLocales = countryToLocales.get("CN");
		List<Locale> swissLocales = countryToLocales.get("CH");
		System.out.println(chineseLocales);
		System.out.println(swissLocales);// 瑞士有多种语言

		Stream<Person> ps = Stream.of(new Person("1", "Lin"), new Person("2", "Peng"), new Person("3", "Tan"),
				new Person("3", "Lu"));
		Map<String, List<Person>> peopleGroups = ps.collect(Collectors.groupingBy(Person::getId));// 按id分组
		System.out.println(peopleGroups);

		// 分区-分为两个区，一个是条件指定的区域，另一个是其他所有区域
		ps = Stream.of(new Person("1", "Lin"), new Person("2", "Peng"), new Person("3", "Tan"), new Person("3", "Lu"));
		Map<Boolean, List<Person>> peoplePartitions = ps.collect(Collectors.partitioningBy(p -> p.getId().equals("2")));// 将元素分为两部分-一部分id为2，另一部分id不为2
		System.out.println(peoplePartitions.get(true));
		System.out.println(peoplePartitions.get(false));
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
