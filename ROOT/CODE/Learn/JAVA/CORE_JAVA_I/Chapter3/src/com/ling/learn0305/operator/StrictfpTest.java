package com.ling.learn0305.operator;

public class StrictfpTest {
	public static strictfp void main(String[] args) {
		// strictfp关键字标记的方法必须将上面计算的每一步中间结果截断为64位(double类型的长度)，
		// 如果没用这个关键字，则使用不同的处理器计算的中间结果会不同，比如80位寄存器的处理器就增加了计算中间过程的精度
		double w = 0.1 * 0.1 / 3.0;
		System.out.println(w);
	}
}
