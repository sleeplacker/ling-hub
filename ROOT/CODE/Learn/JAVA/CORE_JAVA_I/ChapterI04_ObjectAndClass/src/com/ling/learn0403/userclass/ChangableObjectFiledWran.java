package com.ling.learn0403.userclass;

import java.util.Date;

/**
 * 尽量避免访问可变对象域
 *
 * Chapter4/com.ling.learn0403.userclass.ChangableObjectFiledWran.java
 *
 * author lingang
 *
 * createTime 2019-10-11 23:46:46
 *
 */
public class ChangableObjectFiledWran {
	public static void main(String[] args) {
		Date birthday = new Date();
		birthday.setTime(System.currentTimeMillis() - 18l * 365 * 24 * 60 * 60 * 1000); // 18岁
		Student stu = new Student("ling", birthday); // 学生对象已经构造好
		System.out.println(stu);// 打印学生信息

		String getName = stu.getName(); // 访问学生姓名
		Date getBirthDay = stu.getBirthday(); // 访问学生生日
		getName.replace("l", "q"); // 尝试修改名字
		getBirthDay.setTime(System.currentTimeMillis() - 17l * 365 * 24 * 60 * 60 * 1000);// 尝试修改生日
		System.out.println(stu);// 姓名无法被修改，生日可以被修改
		// Warning : Student类并没有提供修改器方法，但是却能修改对象的域，这破坏了类的封装性
		// 因为Date对象是可变的，所以访问器方法可以访问到其中的域再赋值给其他变量，然后通过其他变量改变该域的值；
		// 而String对象是不可变的，就是能访问到该域并赋值给其他变量，也不能通过其他变量修改该值。

		// Tips：为了保护类的封装性，应该将类的可变对象域的访问器方法改为返回克隆对象，如：return (Date)
		// birthday.clone()
	}
}

class Student {
	public Student(String name, Date birthday) {
		this.name = name;
		this.birthday = birthday;
	}

	private String name;
	private Date birthday;

	public String getName() {
		return name;
	}

	public Date getBirthday() {
		return birthday;
		// return (Date) birthday.clone();
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", birthday=" + birthday + "]";
	}

}
