package com.ling.learn0501.superandsubclass;

/**
 * 父类和子类
 *
 * Chapter5/com.ling.learn0501.superandsubclass.SuperClassAndSubClassTest.java
 *
 * 1. 子类构造器：为了确保子类能构造父类那部分对象，子类构造器必须要调用一个父类的构造器，
 * 		此例中由于父类有带参构造器，所以没有默认构造器，子类必须调用一个父类带参构造器
 * 
 * 2. 多态问题：由于多态性，子类对象数组可以赋值给父类对象数组的变量，所以可以通过这个父类
 * 		对象数组的变量试图将父类对象放入子类对象数组中，虽然编译通过，但运行会报错
 * 
 * 3. 如果子类不能访问父类任何一个构造器(例如父类构造器为private类型)，则不能继承该父类
 *
 * author lingang
 *
 * createTime 2019-10-15 22:04:40
 *
 */
public class SuperClassAndSubClassTest {
	public static void main(String[] args) {
		System.out.println("1. 子类构造器");
		Employee[] emps = new Employee[3];
		Manager boss = new Manager("ling", 100000, 20000);
		Employee e1 = new Employee("txq", 60000);
		Employee e2 = new Employee("lzy", 50000);
		emps[0] = boss; // Employee数组可以存放Manager对象，因为经理也属于员工
		emps[1] = e1;
		emps[2] = e2;
		System.out.println(emps[0].getName() + " " + emps[0].getSalary());
		System.out.println(emps[1].getName() + " " + emps[1].getSalary());

		System.out.println("\n2. 多态问题");
		Manager[] mans = new Manager[10];
		Employee[] manEmps = mans; // 由于多态性，子类对象数组可以赋值给父类对象数组的变量
		manEmps[0] = new Employee("ben", 20000); // 试图将父类对象放入子类对象数组中(因为manEmps是父类对象数组变量)，编译通过，运行报错：java.lang.ArrayStoreException:
													// com.ling.learn0501.superandsubclass.Employee
		mans[0].setBonus(5000); // 不会走到这里，上一行就会报错
	}
}

// 员工类
class Employee {
	private String name;
	private double salary;

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + "]";
	}

}

// 经理类-继承自员工类
class Manager extends Employee {
	private double bonus; // 奖金

	public Manager(String name, double salary, double bonus) {
		super(name, salary);// 为了确保能构造父类对象，子类必须要调用一个父类的构造器，此例中由于父类有带参构造器，所以没有默认构造器，子类必须调用这个带参构造器
		this.bonus = bonus;
	}

	// 重写父类方法getSalary()
	public double getSalary() {
		// return salary + bonus; //The field Employee.salary is not
		// visible，子类不能直接访问父类中的私有域
		// return getSalary() + bonus;
		// //编译通过，但是运行会有灾难，因为getSalary()正是当前方法，会循环调用，直到程序崩溃
		return super.getSalary() + bonus; // 使用super.来指定调用父类中的那个getSalary()方法
	}

	public double getBonus() {
		return bonus;
	}

	public void setBonus(double bonus) {
		this.bonus = bonus;
	}

	@Override
	public String toString() {
		return super.toString() + "Manager [bonus=" + bonus + "]";
	}

}

class Factory {
	private Factory() {
		
	}
	public Factory produce(){
		return new Factory();
	}
}

//class FactoryImpl extends Factory { // 如果子类不能访问父类任何一个构造器(例如父类构造器为private类型)，则不能继承该父类
//	FactoryImpl() {
//		super();
//	}
//}
