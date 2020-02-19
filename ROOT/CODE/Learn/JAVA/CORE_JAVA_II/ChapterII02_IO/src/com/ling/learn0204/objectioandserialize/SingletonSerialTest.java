package com.ling.learn0204.objectioandserialize;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 单例和遗留的类型安全枚举的序列化
 * 
 * 按照默认序列化机制，单例对象被序列化到文件后，再读取后不是同一个对象；所以要对单例模式特殊处理，在读入时返回已有的对象
 *
 * ChapterII02/com.ling.learn0204.objectioandserialize.SingletonSerialTest.java
 *
 * author lingang
 *
 * createTime 2020-01-29 23:52:40
 *
 */
public class SingletonSerialTest {
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		HOLIDAY nationalDay = HOLIDAY.NATIONAL_DAY;
		HOLIDAY2 nationalDayMod = HOLIDAY2.NATIONAL_DAY;
		// 写对象
		ObjectOutput oo = new ObjectOutputStream(new FileOutputStream("D:/filetest/object_singalton.txt"));
		oo.writeObject(nationalDay);
		oo.writeObject(nationalDayMod);
		oo.close();

		// 读对象
		ObjectInput oi = new ObjectInputStream(new FileInputStream("D:/filetest/object_singalton.txt"));
		HOLIDAY nationalDay_save = (HOLIDAY) oi.readObject();
		HOLIDAY2 nationalDayMod_save = (HOLIDAY2) oi.readObject();
		System.out.println(nationalDay);
		System.out.println(nationalDay_save);
		System.out.println(nationalDay == nationalDay_save);// false，单例对象和从文件中读取的对象不是同一个，破坏了单例的特性
		System.out.println(nationalDayMod);
		System.out.println(nationalDayMod_save);
		System.out.println(nationalDayMod == nationalDayMod_save);// true
		oi.close();
	}
}

@SuppressWarnings("serial")
class HOLIDAY implements Serializable {
	private Date date;
	public static final HOLIDAY NATIONAL_DAY = new HOLIDAY("19491001");

	private HOLIDAY(String dateStr) {
		try {
			this.date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String toString() {
		return "HOLIDAY [date=" + date + "]";
	}
}

@SuppressWarnings("serial")
class HOLIDAY2 implements Serializable {
	private Date date;
	public static final HOLIDAY2 NATIONAL_DAY = new HOLIDAY2("19491001");

	private HOLIDAY2(String dateStr) {
		try {
			this.date = new SimpleDateFormat("yyyyMMdd").parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	protected Object readResolve() throws ObjectStreamException {
		String dateStr = new SimpleDateFormat("yyyyMMdd").format(date);
		if (dateStr.equals("19491001")) {// 判断值相同时，返回同一个对象
			return NATIONAL_DAY;
		}
		throw new ObjectStreamException() {
		};
	}

	@Override
	public String toString() {
		return "HOLIDAY [date=" + date + "]";
	}
}
