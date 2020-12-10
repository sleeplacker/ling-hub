package com.lg.date202012;

/**
 * 浮点数的性质
 *
 * DailyTips/com.lg.date202012.Date10_FloatingPoint.java
 *
 * author ling
 *
 * createTime 2020-12-10 15:20:33
 *
 */
public class Date10_FloatingPoint {

	public static void main(String[] args) {

		/* 1. 一些浮点数特殊值的计算 */
		Float f1 = 1.0f;
		Float f2 = 0f;
		Float f3 = -0f;
		System.out.println("1/-0 = " + f1 / f2);// ∞
		System.out.println("1/-0 = " + f1 / f3);// -∞
		System.out.println("∞-∞ = +∞ + (-∞) = " + (Float.POSITIVE_INFINITY + Float.NEGATIVE_INFINITY));// NaN
		System.out.println("NaN + 任意浮点数 = " + (Float.NaN + 1.0));// NaN

		/* 2. 加减法不满足结合律 */
		Float f4 = 3.14f;
		Float f5 = 1e10f;
		System.out.println("(f4 + f5) - f5 = " + ((f4 + f5) - f5)); // f4+f5被舍入为f4
		System.out.println("f4 + (f5 - f5) = " + (f4 + (f5 - f5)));

		/* 3. 加法的单调属性 */
		System.out.println("∞+2.0 >= ∞+1.0 : " + ((Float.POSITIVE_INFINITY + 2.0) >= (Float.POSITIVE_INFINITY + 1.0)));

		/* 4. 乘法不满足结合律和加法上的分配律 */
		Float f6 = 1e20f;
		Float f7 = 1e-20f;
		System.out.println("(f6 * f6) * f7 = " + ((f6 * f6) * f7)); // f6 * f6 得到
																	// ∞，然后∞*任何数=∞
		System.out.println("f6 * (f6 * f7) = " + (f6 * (f6 * f7)));

		System.out.println("f6 * (f6 - f6) = " + (f6 * (f6 - f6)));
		System.out.println("f6 * f6 - f6 * f6 = " + (f6 * f6 - f6 * f6)); // f6*f6得到∞，而∞-∞=NaN

		/* 5. 浮点和整数类型转换 */
		int i1 = Integer.MAX_VALUE;
		// 整数转 Float
		float f8 = i1;
		// 整数转 Double
		double d1 = i1;
		// Float 转 Double
		float f9 = (float) Double.MAX_VALUE; // 绝对值的最大值 
		float f10 = (float) Double.MIN_VALUE;// 绝对值的最小值 
		// Float 转 整数 (Double 转 整数有类似结果)
		float f11 = 1.8f;
		float f12 = -1.8f;
		float f13 = Float.MAX_VALUE;
		float f14 = -Float.MAX_VALUE;
		int i2 = (int) f11;
		int i3 = (int) f12;
		// 当浮点数值超出整数值表示范围时的情况
		int i4 = (int) f13;
		int i5 = (int) f14;
		System.out.println("int value : "+i1);//2147483647，精确值
		System.out.println("int to float : "+f8);//2.14748365E9，舍入后的值
		System.out.println("int to double : "+d1);//2.147483647E9，可以精确表示 int 类型最大值
		System.out.println("double to float : "+f9);//∞，因为超出了 float 的表示范围
		System.out.println("double to float : "+f10);//0.0，因为精度超出了 float 的表示范围
		System.out.println("float to int : "+i2);//1，向0舍入
		System.out.println("float to int : "+i3);//-1，向0舍入
		System.out.println("float to int : "+i4);//2147483647，正值超出 int 类型范围，取 Integer.MAX_VALUE
		System.out.println("float to int : "+i5);//-2147483648，负值超出 int 类型范围，取 Integer.MIN_VALUE
	}
}
