package com.ling.learn0901.validate;

/**
 * 字节码校验，检验是在虚拟机中进行的，由于编译器编译的类文件肯定是合法的，所以校验器主要任务是校验人为修改过的类文件
 * 
 * 直接在工程根目录/bin下执行：java
 * com.ling.learn0901.validate.ByteCodeValidationTest，程序正常运行
 * 
 * 将ByteCodeValidationTest.class在十六进制编辑器进行篡改(篡改方式参考第412页)，篡改操作是将b=2改为a=2，这样变量b就没被初始化，
 * 然后将得到的类放到当前包下，
 * 
 * 然后进入工程根目录/src下执行：java com.ling.learn0901.validate.ByteCodeValidationTest，会出现运行错误： 
 * Error: A JNI error has occurred, please check your installation and try again 
 * Exception in thread "main" java.lang.VerifyError: Bad local variable type
 * 
 * 如果执行：java -noverify com.ling.learn0901.validate.ByteCodeValidationTest，绕过了字节码校验
 * 运行结果为：1 + 2 = 2，是错误的结果，看来未被初始化的参数还是被虚拟机安全地被初始化为0了
 *
 * ChapterII09_security/com.ling.learn0901.validate.ByteCodeValidationTest.java
 *
 * author lingang
 *
 * createTime 2020-03-11 19:00:22
 *
 */
public class ByteCodeValidationTest {
	public static void main(String[] args) {
		int a;
		int b;
		a = 1;
		b = 2;
		int r = a + b;
		System.out.println("1 + 2 = " + r);
	}
}
