package com.ling.learn0504.boxingandunboxing;

/**
 * 自动装箱和自动拆箱
 *
 * Chapter5/com.ling.learn0504.boxingandunboxing.BoxingAndUnboxing.java
 *
 * 1. 一些常用数值会被装箱到同一个对象，也就是数值相同，就指向同一个对象，这些数值范围如下： 
 * 1) boolean、byte、char： ≤ 127 
 * 2) short和int：-128 ~ 127
 * 
 * 2. 如果在一个条件表达式中混合使用较低精确类型(如Integer)和较高精确类型(如Double)，
 * 	较低精确类型值会拆箱，提升为较高精确类型的基础类型(如double)，然后再装箱为较高精确类型(如Double)
 * 
 *
 * author lingang
 *
 * createTime 2019-10-18 00:49:33
 *
 */
public class BoxingAndUnboxing {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Integer i1 = 10;// 在-128 ~ 127范围内
		Integer i2 = 10;
		Integer i3 = 1000;// 不在-128 ~ 127范围内
		Integer i4 = 1000;
		System.out.println(i1 == i2);// 在-128 ~
										// 127范围内自动装箱成Integer，只要值相同，都指向同一个对象
		System.out.println(i3 == i4);// 不在-128 ~
										// 127范围内自动装箱成Integer，值相同，不一定指向同一个对象，但是也有可能
		System.out.println(i3.equals(i4));// 调用equals方法可以比较任何Integer值

		Integer iValue = 10;
		Double dValue = 3.333;
		/* 如果在一个条件表达式中混合使用Integer和Double，Integer值会拆箱，提升为double，然后再装箱为Double */
		System.out.println(true ? iValue : dValue);
	}

}
