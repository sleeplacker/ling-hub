package com.ling.learn0502.objectclass;

import java.util.Objects;

/**
 * equals方法和hashCode方法
 * 
 * 一、equals方法的五个特性：
 * 
 * 1. 自反性：对于任何非空引用x，x.equals(x)返回true。
 * 
 * 2. 对称性：对于任何引用x和y，当且仅当y.equals(x)返回true，x.equals(y)也应该返回true。
 * 
 * 3. 传递性：对于任何应用x、y和z，如果x.equals(y)返回true，y.equals(z)返回true，x.equals.(z)也应该返回true。
 * 
 * 4. 一致性：如果x和y引用的对象没有发生变化，反复调用x.equals(y)应该返回同样的结果。
 * 
 * 5. 对于任意非空引用x，x.equals(null)应该返回false。
 * 
 * 二、equals方法需要注意的几点
 * 
 * 1. 覆盖Object类的equals方法时必须使用public boolean equals(Object
 * otherObject)方法签名，参数类型不能是其他类型，否则并没有继承Object类的equals方法而是写了一个新方法
 * 
 * 2. 为了节省开销，如果两个对象引用同一个对象，则返回true
 * 
 * 3. 为了节省开销，如果参数为空，则返回false
 * 
 * 4. 如果父类不能决定相等性，则应该用this.getClass() != otherObject.getClass()的严格方式来比较类型是否相同
 * 
 * 5. 如果父类能决定相等性，则应该使用otherObject instanceof
 * 父类Class的宽松方式来对比类型是否相等，且应该将equals方法定义为final方法，因为不需要子类重写了
 * 
 * 三、hashCode方法要满足的原则：如果x.equals(y)，那么x.hashCode()和y.hashCode()相等，这要求计算hashCode使用的域必须和比较相等性使用的域相同
 *
 * Chapter5/com.ling.learn0502.objectclass.EqualsAndHashCodeMethodTest.java
 *
 * author lingang
 *
 * createTime 2019-10-17 22:36:52
 *
 */
public class EqualsAndHashCodeMethodTest {
	public static void main(String[] args) {
		Employee[] ems = { new Employee("ling", 10000), new Employee("ling", 10000), new Manager("ling", 10000, 5000) };

		System.out.println(ems[0].equals(ems[1])); // true

		System.out.println(ems[0].equals(ems[2])); // false

		System.out.println(ems[0].hashCode());
		System.out.println(ems[1].hashCode()); // 和ems[0].hashCode()相等
		System.out.println(ems[2].hashCode());

	}
}

class Employee {
	private String name;
	private double salary;

	public Employee(String name, double salary) {
		this.name = name;
		this.salary = salary;
	}
	/* equals方法定义建议 < */
	@Override
	// public boolean equals(Employee otherObject) { // 因为父类的参数类型是Object类型，所以这里必须使用Object参数类型
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;// 1. 如果两个对象引用同一个对象，则返回true
		if (otherObject == null)
			return false;// 2. 如果参数为空，则返回false
		/*
		 * 3_1. 如果this和参数不属于同一种类型，则返回false（这种检查类型的方式比较严格，适用于此例，因为子类多了一个域需要对比，
		 * 所以为了满足对称性，必须保证参与比较的类型必须是完全相同的）
		 */
		if (this.getClass() != otherObject.getClass())
			return false;
		/*
		 * 3_2. 如果参数类型不是当前类类型，或当前类的子类型，则返回false（这种检查类型的方式比较严格宽松，不适用于此例，
		 * 如果一个员工e和一个经理m的姓名和工资都一样，那么e.equals(m)=true，但是m.equals(e)=false，不满足对称性。
		 * 然而如果Employee类有个唯一的标识id，那么Employee类就能决定所有Employee类和子类的相等性，
		 * 这时可以使用这种类型比较方式，而且应该讲equals方法声明为final的，因为这个equals已经能准确比较两个对象的相等性）
		 */
		if (!(otherObject instanceof Employee))
			return false;
		Employee other = (Employee) otherObject;
		return Objects.equals(this.name, other.name) && this.salary == other.salary;
	}
	/* equals方法定义建议 > */
	
	@Override
	public int hashCode() {
		return Objects.hash(name, salary);
	}

	@Override
	public String toString() {
		return "Employee [name=" + name + ", salary=" + salary + "]";
	}

}

class Manager extends Employee {
	private double bonus; // 经理类特有的域

	public Manager(String name, double salary, double bonus) {
		super(name, salary);
		this.bonus = bonus;
	}

	@Override
	public boolean equals(Object otherObject) {
		return super.equals(otherObject) && this.bonus == ((Manager) otherObject).bonus;
	}

	@Override
	public int hashCode() {
		return super.hashCode() + Objects.hash(bonus); // 因为bonus为基础数据类型，所以使用Objects.hash方法来自动装箱
	}

	@Override
	public String toString() {
		return super.toString() + " Manager [bonus=" + bonus + "]";
	}

}
