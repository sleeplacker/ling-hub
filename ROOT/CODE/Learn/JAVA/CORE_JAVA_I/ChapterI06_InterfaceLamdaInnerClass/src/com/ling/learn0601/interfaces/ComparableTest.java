package com.ling.learn0601.interfaces;

import java.util.Arrays;


/**
 * Comparable接口示例
 * 
 * 在继承中重写compareTo方法需求注意的地方(与equals方法在继承中重写要注意的地方一样)
 * 
 * 如果子类的之间的比较方法不一样，那不能将子类和父类进行比较，需要在compareTo方法中使用if(getClass() == other.getClass()) throw new ClassCastException();来检查两个比较的类型是否完全一致
 * 
 * 如果所有子类之间的比较方法一样，那应该把compareTo方法放在父类中，并声明为final方法
 *
 * Chapter6/com.ling.learn0601.interfaces.ComparableTest.java
 *
 * author lingang
 *
 * createTime 2019-10-23 13:11:31 
 *
 */
public class ComparableTest {
public static void main(String[] args) {
	Comparable[] es = new Comparable[3];
	Comparable<Employee> e1 = new Employee("ling", 55555);
	Comparable<Employee> e2 = new Employee("ling", 99999);
	Comparable<Employee> e3 = new Employee("ling", 88888);
	es[0] =e1;
	es[1] =e2;
	es[2] =e3;
	System.out.println(Arrays.toString(es));
	Arrays.sort(es); //排序
	System.out.println(Arrays.toString(es));
}
}

class Employee implements Comparable<Employee>{
	private String name;
	private double salary;

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + "]";
	}
	@Override
	public final int compareTo(Employee other) {
		return Double.compare(this.salary, other.salary);
	}

}


class Manager extends Employee {
	private double bonus; // 经理类特有的域

	public Manager(String name, double salary, double bonus) {
		super(name, salary);
		this.bonus = bonus;
	}
	
	public String toString() {
		return super.toString() + " Manager [bonus=" + bonus + "]";
	}

}