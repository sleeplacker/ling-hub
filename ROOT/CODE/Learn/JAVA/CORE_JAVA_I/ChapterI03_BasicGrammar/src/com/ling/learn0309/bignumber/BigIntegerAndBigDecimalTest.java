package com.ling.learn0309.bignumber;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 * 大数字
 *
 * 1. BigDecimal除不尽时会抛出java.lang.ArithmeticException，所以应该习惯性的给除法加上舍入参数
 * 
 * 2. 两个BigDecimal相加或相减，结果的小数位与小数位较长的一个数一致
 * 
 * 3. 两个BigDecimal相，结果的小数位为两个数小数位相加
 * 
 * 4. 两个BigDecimal相除，结果的小数位为被除数小数位数减除数小数位数，如果相减小于0则取0；
 * 	且在保证精度的前提下，例如10.00/8.00，虽然小数位相减为0，但是精确的结果是1.25
 *
 * Chapter3/com.ling.learn0309.bignumber.BigIntegerAndBigDecimalTest.java
 *
 * author lingang
 *
 * createTime 2019-10-09 00:20:32
 *
 */
public class BigIntegerAndBigDecimalTest {
	public static void main(String[] args) {
		BigInteger bigInt = new BigInteger("10").divide(BigInteger.valueOf(3));
		// BigDecimal bigDcm = new
		// BigDecimal("10").divide(BigDecimal.valueOf(3)); //
		// BigDecimal除不尽时会抛出java.lang.ArithmeticException，所以应该习惯性的给除法加上舍入参数，像下面一行
		BigDecimal bigDcm = new BigDecimal("10").divide(BigDecimal.valueOf(3), BigDecimal.ROUND_HALF_UP); // 四舍五入方式

		BigDecimal bigDcmAdd = new BigDecimal("10.0000").add(new BigDecimal("8.00")); // 18.0000
																						// 两个BigDecimal相加，结果的小数位与小数位较长的一个数一致。
		BigDecimal bigDcmSub = new BigDecimal("10.0000").subtract(new BigDecimal("8.00"));// 2.0000
																							// 两个BigDecimal相减，结果的小数位与小数位较长的一个数一致。
		BigDecimal bigDcmMul = new BigDecimal("10.0000").multiply(new BigDecimal("8.00"));// 80.000000
																							// 两个BigDecimal相，结果的小数位为两个数小数位相加。
		BigDecimal bigDcmDiv = new BigDecimal("1000.0000").divide(new BigDecimal("8.00"));// 125.00
																							// 两个BigDecimal相除，结果的小数位为被除数小数位数减除数小数位数，如果相减小于0则取0；且在保证精度的前提下，例如10.00/8.00，虽然小数位相减为0，但是精确的结果是1.25。
		System.out.println(bigInt);
		System.out.println(bigDcm);
		System.out.println(bigDcmAdd);
		System.out.println(bigDcmSub);
		System.out.println(bigDcmMul);
		System.out.println(bigDcmDiv);
	}
}
