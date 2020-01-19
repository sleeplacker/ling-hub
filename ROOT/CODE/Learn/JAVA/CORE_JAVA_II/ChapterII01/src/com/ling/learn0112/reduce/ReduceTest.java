package com.ling.learn0112.reduce;

import java.util.Optional;
import java.util.stream.Stream;

/**
 * 约简操作
 *
 * ChapterII01/com.ling.learn0112.reduce.ReduceTest.java
 *
 * author lingang
 *
 * createTime 2020-01-19 12:57:09
 *
 */
public class ReduceTest {
	public static void main(String[] args) {
		// 约简为所有元素的年龄总和
		Stream<Person> ps = Stream.of(new Person("1", "Lin", 3), new Person("2", "Peng", 4), new Person("3", "Tan", 5),
				new Person("3", "Lu", 9));
		Optional<Integer> reduce1 = ps.map(Person::getAge).reduce((age1, age2) -> age1 + age2);
		System.out.println(reduce1);

		// 当指定幺元后可以直接返回目标类型，而不是通过Optional包装后的值，幺元就是不影响计算结果的值：幺元 OP Value = Value
		ps = Stream.of(new Person("1", "Lin", 3), new Person("2", "Peng", 4), new Person("3", "Tan", 5),
				new Person("3", "Lu", 9));
		Integer reduce2 = ps.map(Person::getAge).reduce(0, (age1, age2) -> age1 + age2);
		System.out.println(reduce2);

		// 不用把Person映射为age的写法
		ps = Stream.of(new Person("1", "Lin", 3), new Person("2", "Peng", 4), new Person("3", "Tan", 5),
				new Person("3", "Lu", 9));
		Integer reduce3 = ps.reduce(0, (total, person) -> total + person.getAge(), (total1, total2) -> total1 + total2);
		System.out.println(reduce3);
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
