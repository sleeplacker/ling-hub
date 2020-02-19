package com.ling.learn0204.objectioandserialize;

import java.io.Externalizable;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Date;

/**
 * 修改默认的序列化机制
 * ChapterII02/com.ling.learn0204.objectioandserialize.ModDefSerialTest.java
 *
 * author lingang
 *
 * createTime 2020-01-28 23:12:23
 *
 */
public class ModDefSerialTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {

		/* 1. transient关键字：使用该关键字修饰的域在对象读写时不会被处理 */
		Student ling = new Student("Ling", "19911115");
		Student linzy = new Student("Linzy", "20180903");

		// 写对象
		ObjectOutput oo = new ObjectOutputStream(new FileOutputStream("D:/filetest/object_content2.txt"));
		oo.writeObject(ling);
		oo.writeObject(linzy);
		/*
		 * ObjectOutput实现了DataOutput接口，
		 * 所以可以在对象读写默认行为的基础上再使用DataOutput接口的二进制读写方法来写额外信息
		 */
		oo.writeChars(linzy.getBirthday());
		oo.close();

		// 读对象
		ObjectInput oi = new ObjectInputStream(new FileInputStream("D:/filetest/object_content2.txt"));
		Student stu1 = (Student) oi.readObject();
		Student stu2 = (Student) oi.readObject();
		String stu2_birthday = "" + oi.readChar() + oi.readChar() + oi.readChar() + oi.readChar() + oi.readChar()
				+ oi.readChar() + oi.readChar() + oi.readChar();
		stu2.setBirthday(stu2_birthday);
		/* birthday域被transient关键字修饰，所以对象流读写时不会包含这个字段，所以读出来的值为空 */
		System.out.println(stu1);// Student [name=Ling, birthday=null]
		System.out.println(stu2);// 使用二进制读取方式读入了额外的信息
		oi.close();
		/*
		 * 2. Date类的对象读写：这里记录Date类对象读写的原因是，Date类定义了字节readObject和writeObject方法，
		 * 这两个方法是private的，但是序列化机制可以调用这两个方法*
		 */
		System.out.println("\njava.util.Date类的对象读写：");
		Date date = new Date();
		// 写对象
		ObjectOutput oo2 = new ObjectOutputStream(new FileOutputStream("D:/filetest/object_content3.txt"));
		oo2.writeObject(date);
		oo2.close();

		// 读对象
		ObjectInput oi2 = new ObjectInputStream(new FileInputStream("D:/filetest/object_content3.txt"));
		Date date1 = (Date) oi2.readObject();
		System.out.println(date1);
		oi2.close();
		/*
		 * 3.
		 * 自定义对象序列化机制：注意，自定义序列化机制的类必须实现Externalizable接口，并由无参构造器，因为对象输入流会调用无参构造器，
		 * 然后再调用自定义的readExternal方法
		 */
		System.out.println("\n自定义序列化机制：");
		Student2 tan = new Student2("Txq", "19940924");
		// 写对象
		ObjectOutput oo3 = new ObjectOutputStream(new FileOutputStream("D:/filetest/object_content4.txt"));
		oo3.writeObject(tan);
		oo3.close();

		// 读对象
		ObjectInput oi3 = new ObjectInputStream(new FileInputStream("D:/filetest/object_content4.txt"));
		Student2 stu3 = (Student2) oi3.readObject();
		System.out.println(stu3);
		oi3.close();
	}
}

@SuppressWarnings("serial")
class Student implements Serializable {
	private String name;
	private transient String birthday;// 声明为transient的域，在对象写入和读出时不会包含

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Student(String name, String birthday) {
		this.name = name;
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", birthday=" + birthday + "]";
	}

}

class Student2 implements Serializable, Externalizable {
	private String name;
	private transient String birthday;// 声明为transient的域，在对象写入和读出时不会包含

	public Student2() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public Student2(String name, String birthday) {
		this.name = name;
		this.birthday = birthday;
	}

	@Override
	public String toString() {
		return "Student [name=" + name + ", birthday=" + birthday + "]";
	}

	@Override
	public void writeExternal(ObjectOutput out) throws IOException {// 自定义写对象方法
		out.writeUTF(name);
		out.writeUTF(birthday);
	}

	@Override
	public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {// 自定义读对象方法
		name = in.readUTF();
		birthday = in.readUTF();
	}

}
