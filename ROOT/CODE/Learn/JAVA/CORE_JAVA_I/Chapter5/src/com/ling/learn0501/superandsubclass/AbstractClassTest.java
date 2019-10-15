package com.ling.learn0501.superandsubclass;

/**
 * 抽象类
 * 
 * 1. 抽象类可以不包含任何抽象方法，而只包含非抽象方法，但是这种类不能被实例化
 * 
 * 2. 只要一个类含有一个抽象方法，这个类必须是抽象类
 * 
 * 3. 抽象类的子类可以实现部分父类的抽象类，但是只要父类还有未被实现的抽象类，该子类也是抽象类
 * 
 * 4. 抽象类也可以继承实体类
 *
 * Chapter5/com.ling.learn0501.superandsubclass.AbstractClassTest.java
 *
 * author lingang
 *
 * createTime 2019-10-16 01:58:57 
 *
 */
public class AbstractClassTest {
	public static void main(String[] args) {
		Person[] pers = new Person[10]; // 使用抽象类型创建数组
		Student stu = new Student("lzy");
		Teacher tea = new Teacher("txq");
		pers[0] = stu; // 使用实体类填充数组
		pers[1] = tea;

		System.out.println(pers[0].getName() + " " + pers[0].getDescription());
		System.out.println(pers[1].getName() + " " + pers[1].getDescription());
	}
}

abstract class A { // 抽象类可以不包含任何抽象方法，而只包含非抽象方法，但是这种类不能被实例化
	void f() {

	}
}

// class B { // error，含有一个抽象方法的类必须是抽象类
// abstract void f() {
//
// }
// }

class Thing {
}

abstract class Person extends Thing{ // 抽象类也可以继承实体类
	private String name;

	public Person(String name) {
		this.name = name;
	}

	public abstract String getDescription();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}

class Student extends Person {

	public Student(String name) {
		super(name);
	}

	@Override
	public String getDescription() { // 要实现所有抽象方法，才能成为非抽象类
		return "a student";
	}

}

class Teacher extends Person {
	public Teacher(String name) {
		super(name);
	}

	@Override
	public String getDescription() {
		return "a teacher";
	}

}