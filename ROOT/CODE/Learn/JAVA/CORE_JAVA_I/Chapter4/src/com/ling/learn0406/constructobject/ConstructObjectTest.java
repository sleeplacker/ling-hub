package com.ling.learn0406.constructobject;

/**
 * 对象构造
 * 
 * 1. 只要有自定义构造器，则默认构造器不会起作用
 * 
 * 2.  如果构造器中没有对域进行初始化，则对象构造时会对域赋默认值(数值赋0，boolean赋值false，对象赋null)
 * 
 * 3. 可以在一个构造器中调用其他构造器，但是必须在第一行调用
 * 
 * 4. 静态初始化块会先执行，且多次实例化对象只会执行一次执行静态初始化块，而实例初始化块在每次实例化对象都会执行一次
 *
 * Chapter4/com.ling.learn0406.constructobject.ConstructObjectTest.java
 *
 * author lingang
 *
 * createTime 2019-10-13 23:01:40
 *
 */
public class ConstructObjectTest {
	public static void main(String[] args) {
		// Employee em1 = new Employee(); // error 1. 只要有自定义构造器，则默认构造器不会起作用
		Student yiyi = new Student();
		System.out.println(yiyi); // 2.  如果构造器中没有对域进行初始化，则对象构造时会对域赋默认值(数值赋0，boolean赋值false，对象赋null)

		Student txq = new Student("tan");
		System.out.println(txq); // 3. 可以在一个构造器中调用其他构造器，但是必须在第一行调用

		// 4. 从打印结果可以看出，虽然在代码中静态初始化块写在实例初始化块的后面，
		// 但是静态初始化块会先执行，且多次实例化对象只会执行一次执行静态初始化块，而实例初始化块在每次实例化对象都会执行一次
	}
}

class Student {
	private static int nextId; // 不赋值，则为默认值初始值0
	private int id;
	private int age;
	private String name;
	private String remark = "";// 可以显示地给域初始化

	{
		System.out.println("执行实例初始化块");
	}

	static {
		System.out.println("执行静态初始化块");
	}

	public Student() {
		this.id = ++nextId;
	}

	Student(String name) {
		this(); // 调用Student()构造器
		this.name = name;
	}

	public Student(int age, String name) {
		this();// 调用Student()构造器
		this.age = age;
		this.name = name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", age=" + age + ", name=" + name + ", remark=" + remark + "]";
	}

}

class Employee {
	private String name;

	@Override
	public String toString() {
		return "Employee [name=" + name + "]";
	}

	public Employee(String name) {
		super();
		this.name = name;
	}
}
