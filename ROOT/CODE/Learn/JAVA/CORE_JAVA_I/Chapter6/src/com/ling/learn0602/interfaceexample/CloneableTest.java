package com.ling.learn0602.interfaceexample;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 对象克隆
 * 
 * 1. Object对象中clone方法时浅拷贝，只会复制实例域，而不会克隆实例域
 * 
 * 2. 当实例域中有类是可变的，就要考虑使用深拷贝(当可变类型的域没有被修改状态的可能，也不需要克隆该域)
 * 
 * 3. 子类克隆，如果子类中没有包含可变类型的域，则可以使用父类的克隆方法进行克隆
 *
 * Chapter6/com.ling.learn0602.interfaceexample.CloneableTest.java
 *
 * author lingang
 *
 * createTime 2019-10-24 01:22:49
 *
 */
public class CloneableTest {
	public static void main(String[] args) throws ParseException, CloneNotSupportedException {
		/* 1. Object对象中clone方法时浅拷贝，只会复制实例域，而不会克隆实例域 */
		Employee e1 = new Employee("ling", 50000, new SimpleDateFormat("yyyy-MM-dd").parse("1991-11-15"));
		Employee e2 = e1.clone();
		System.out.println(e1);
		System.out.println(e2);

		e1.getBirthday().setTime(20l * 366 * 24 * 60 * 60 * 1000); // 修改e1中的生日字段

		System.out.println(e1);
		System.out.println(e2);// e2的生日也被修改了，说明克隆不完整

		/* 2. 当实例域中有类是可变的，就要考虑使用深拷贝 */
		Student s1 = new Student("lzy", new SimpleDateFormat("yyyy-MM-dd").parse("2018-09-03"));
		Student s2 = s1.clone();
		System.out.println(s1);
		System.out.println(s2);

		s1.getBirthday().setTime(48l * 366 * 24 * 60 * 60 * 1000);// 修改s1中的生日字段

		System.out.println(s1);
		System.out.println(s2);// s2的生日未被修改，说明克隆完整
	}
}

class Employee implements Cloneable {
	private String name;
	private double salary;
	private Date birthday;

	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param name
	 * @param salary
	 * @param birthday
	 */
	public Employee(String name, double salary, Date birthday) {
		super();
		this.name = name;
		this.salary = salary;
		this.birthday = birthday;
	}

	@Override
	protected Employee clone() throws CloneNotSupportedException {
		return (Employee) super.clone(); // 继承Object对象中的克隆方式-浅拷贝
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + ", birthday=" + birthday + "]";
	}

}

class Student implements Cloneable {
	private String name;
	private Date birthday;

	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param name
	 * @param birthday
	 */
	public Student(String name, Date birthday) {
		super();
		this.name = name;
		this.birthday = birthday;
	}

	@Override
	protected Student clone() throws CloneNotSupportedException {
		Student cloned = (Student) super.clone();// 先调用Object的克隆方法
		cloned.birthday = (Date) birthday.clone();// 再将可变类型的域进行克隆
		return cloned;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", birthday=" + birthday + "]";
	}

}
