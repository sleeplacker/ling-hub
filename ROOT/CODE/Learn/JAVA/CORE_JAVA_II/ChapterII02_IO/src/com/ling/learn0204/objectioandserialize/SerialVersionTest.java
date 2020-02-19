package com.ling.learn0204.objectioandserialize;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.Serializable;

/**
 * 序列化版本管理
 *
 * ChapterII02/com.ling.learn0204.objectioandserialize.SerialVersionTest.java
 *
 * author lingang
 *
 * createTime 2020-01-30 00:31:27
 *
 */
public class SerialVersionTest {
	@SuppressWarnings("unused")
	public static void main(String[] args) throws ClassNotFoundException, IOException {
		OldClass1 linzy = new OldClass1("Linzy", "20180903");
		/*
		 * 1. 没使用serialVersionUID的情况：如果将对象写入文件后，类的指纹有改动(即类、超类、接口、域类和方法签名有改动)，
		 * 那么再从文件读取文件时就会出现java.io.InvalidClassException
		 */
		// 写对象--第一遍执行下面4行，第二遍执行时注释下面4行
		// ObjectOutput oo = new ObjectOutputStream(new
		// FileOutputStream("D:/filetest/object_version.txt"));
		// oo.writeObject(linzy);
		// oo.close();

		// 读对象-第一遍执行时注释下面3行，第二遍执行下面3行，并将OldClass1类的域name你改为name1(引用该域的地方一起改)
		// ObjectInput oi = new ObjectInputStream(new
		// FileInputStream("D:/filetest/object_version.txt"));
		// OldClass1 oc1 = (OldClass1) oi.readObject();
		// System.out.println(oc1);// java.io.InvalidClassException

		/*
		 * 2. 有使用serialVersionUID的情况：如果将对象写入文件后，类的指纹有改动(即类、超类、接口、域类和方法签名有改动)，
		 * 那么再从文件读取文件时就会出现java.io.InvalidClassException
		 */
		// OldClass2 linzy2 = new OldClass2("Linzy", "20180903");
		// // 写对象--第一遍执行下面4行，第二遍执行时注释下面4行
		// ObjectOutput oo2 = new ObjectOutputStream(new
		// FileOutputStream("D:/filetest/object_version2.txt"));
		// oo2.writeObject(linzy2);
		// oo2.close();

		// 读对象-第一遍执行时注释下面3行，第二遍执行下面3行，并在OldClass2类中新增age域，并相应地修改toString方法
		ObjectInput oi2 = new ObjectInputStream(new FileInputStream("D:/filetest/object_version2.txt"));
		OldClass2 oc2 = (OldClass2) oi2.readObject();
		System.out.println(oc2);// 读取正常，OldClass2 [name=Linzy,
								// birthday=20180903,
								// age=0]，age取默认值0，如果去掉birthday字段，那读取对象会少birthday字段
		oi2.close();
	}
}

@SuppressWarnings("serial")
class OldClass1 implements Serializable {
	private String name;
	private String birthday;

	public OldClass1(String name, String birthday) {
		this.name = name;
		this.birthday = birthday;
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

	@Override
	public String toString() {
		return "OldClass1 [name=" + name + ", birthday=" + birthday + "]";
	}

}

class OldClass2 implements Serializable {
	/**
	* 
	*/
	private static final long serialVersionUID = 5135272122814187509L;
	private String name;
	private String birthday;
	private int age;

	public OldClass2(String name, String birthday) {
		this.name = name;
		this.birthday = birthday;
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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "OldClass2 [name=" + name + ", birthday=" + birthday + ", age=" + age + "]";
	}

}
