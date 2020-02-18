package com.ling.learn0303.datatype;

/**
 * 浮点数
 *
 * Chapter3/com.ling.learn0303.datatype.FloatTest.java
 *
 * author lingang
 *
 * createTime 2019-10-03 13:13:22 
 *
 */
public class FloatTest {
public static void main(String[] args) {
	System.out.println("float绝对值 : "+(Float.MIN_VALUE) + " ~ " +Float.MAX_VALUE); // float型数据绝对值取值范围 : 1.4E-45 ~ 3.4028235E38
	System.out.println("double绝对值 : "+Double.MIN_VALUE + " ~ " +Double.MAX_VALUE); // double型数据绝对值取值范围 : 4.9E-324 ~ 1.7976931348623157E308
	System.out.println("float正无穷大 : "+1.0f/0.0f);
	System.out.println("float负无穷大 : "+-1.0f/0.0f);
	System.out.println("float非数值 : "+0.0f/0.0f);
	System.out.println("double正无穷大 : "+1.0/0.0);
	System.out.println("double负无穷大 : "+-1.0/0.0);
	System.out.println("double非数值 : "+0.0/0.0);
	System.out.println("2.0f-1.1f = "+ (2.0f-1.9f));// float计算误差
	System.out.println("2.0d-1.1d = "+ (2.0d-1.9d)); // double计算误差 
}
}
