package com.ling.learn0204.objectioandserialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * 对象的读写与序列化
 * 
 * 对象的读写为什么需要序列化：因为同一个对象可能会被多个对象引用，而在文件中不能通过对象的引用来找到对象，只能定义一个序列号来找它
 *
 * ChapterII02/com.ling.learn0204.objectioandserialize.ObjectInOutTest.java
 *
 * author lingang
 *
 * createTime 2020-01-23 23:44:01
 *
 */
public class ObjectInOutTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		Employee amy = new Employee("Amy", 20);
		Manager ling = new Manager("Ling", 28, amy);
		Manager lhj = new Manager("Lhj", 28, amy);

		/* 1. 对象写出 */
		ObjectOutput oo = new ObjectOutputStream(new FileOutputStream("D:/filetest/object_content.txt"));
		oo.writeObject(amy);
		oo.writeObject(ling);
		oo.writeObject(lhj);
		oo.close();

		/* 2. 对象读入-注意按照写对象的顺序读 */
		ObjectInput oi = new ObjectInputStream(new FileInputStream("D:/filetest/object_content.txt"));
		Employee e1 = (Employee) oi.readObject();
		Manager m1 = (Manager) oi.readObject();
		Manager m2 = (Manager) oi.readObject();
		System.out.println(e1);
		System.out.println(m1);
		System.out.println(m2);

	}
}

@SuppressWarnings("serial")
// class Employee {
class Employee implements Serializable {// 要实现对象读写必须实现Serialzable接口，否则运行时会有java.io.NotSerializableException

	private String name;
	private int age;

	public Employee(String name, int age) {
		super();
		this.name = name;
		this.age = age;
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
		return "Employee [name=" + name + ", age=" + age + "]";
	}

}

@SuppressWarnings("serial")
class Manager extends Employee {
	private Employee secretary;// 秘书

	public Employee getSecretary() {
		return secretary;
	}

	public void setSecretary(Employee secretary) {
		this.secretary = secretary;
	}

	public Manager(String name, int age, Employee secretary) {
		super(name, age);
		this.secretary = secretary;
	}

	@Override
	public String toString() {
		return super.toString() + " Manager [secretary=" + secretary + "]";
	}

}
