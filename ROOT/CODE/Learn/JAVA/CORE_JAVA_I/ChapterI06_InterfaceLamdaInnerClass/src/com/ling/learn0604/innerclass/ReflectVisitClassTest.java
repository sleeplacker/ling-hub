package com.ling.learn0604.innerclass;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Scanner;

/**
 * 此类用于测试-查看内部类结构
 * 
 * 输入：com.ling.learn0604.innerclass.TalkingClock$TimePrinter，以查看内部类结构
 * 输入：com.ling.learn0604.innerclass.TalkingClock，以查看外部类结构
 * 输入：com.ling.learn0604.innerclass.LocalInnerClassTest
 *
 * Chapter6/com.ling.learn0604.innerclass.ReflectVisitClassTest.java
 *
 * author lingang
 *
 * createTime 2019-10-28 23:30:59 
 *
 */
public class ReflectVisitClassTest {
	public static void main(String[] args) throws ClassNotFoundException {

		// 类名输入处理
		String className = null;
		if (args.length > 0) {
			className = args[0];
		} else {
			System.out.println("Input Class Name(like java.util.Date) : ");
			Scanner scan = new Scanner(System.in);
			className = scan.nextLine();
			scan.close();
		}

		Class cl = Class.forName(className);
		printClassInfo(cl);// 打印类定义信息
		printFields(cl);// 打印域信息
		printConstructors(cl);// 打印类构造器信息
		printMethods(cl);// 打印所有方法
	}

	private static void printClassInfo(Class cl) {// 打印类定义信息
		String simpleName = cl.getSimpleName(); // 类名
		String classModifier = Modifier.toString(cl.getModifiers());// 类修饰符
		Class superClass = cl.getSuperclass();// 父类Class
		String superClassSimpleName = (superClass == null || superClass == Object.class) ? null
				: superClass.getSimpleName();// 父类名
		Class[] interfaces = cl.getInterfaces();
		String interfaceNames = joinTypeName(interfaces);
		System.out.println(classModifier + " class " + simpleName + " "
				+ (superClassSimpleName == null ? "" : "extends " + superClassSimpleName + " ")
				+ (interfaceNames == null ? "" : "implements " + interfaceNames) + " {");
	}

	private static void printConstructors(Class cl) {// 打印构造器信息
		Constructor[] constructors = cl.getDeclaredConstructors(); // 获取所有构造器
		if (constructors.length <= 0)
			return;
		String name = cl.getSimpleName();// 构造器方法名称和类名一样
		for (Constructor cst : constructors) {
			String modifiers = Modifier.toString(cst.getModifiers());// 修饰符
			modifiers = (modifiers != null && !"".equals(modifiers)) ? modifiers + " " : modifiers;
			Class[] params = cst.getParameterTypes(); // 参数列表
			String paramsStr = joinTypeName(params);
			Class[] exceptions = cst.getExceptionTypes();// 抛出异常类型列表
			String exceptionsStr = joinTypeName(exceptions);
			System.out.println("\t" + modifiers + name + "(" + (paramsStr == null ? "" : paramsStr) + ")"
					+ (exceptionsStr == null ? "" : " throws " + exceptionsStr) + ";");
		}
	}

	private static void printMethods(Class cl) {// 打印方法信息
		Method[] methods = cl.getDeclaredMethods();
		if (methods.length <= 0)
			return;
		for (Method method : methods) {
			String modifiers = Modifier.toString(method.getModifiers());
			modifiers = (modifiers != null && !"".equals(modifiers)) ? modifiers + " " : modifiers;
			String returnType = method.getReturnType().getSimpleName();
			Class[] params = method.getParameterTypes();
			String paramsStr = joinTypeName(params);
			Class[] exceptions = method.getExceptionTypes();
			String exceptionsStr = joinTypeName(exceptions);
			System.out.println(
					"\t" + modifiers + returnType + " " + method.getName() + "(" + (paramsStr == null ? "" : paramsStr)
							+ ")" + (exceptionsStr == null ? "" : " throws " + exceptionsStr) + ";");
		}
		System.out.println("\r}");
	}

	private static void printFields(Class cl) {// 打印域信息
		Field[] fields = cl.getDeclaredFields();
		if (fields.length <= 0)
			return;
		for (Field field : fields) {
			String modifiers = Modifier.toString(field.getModifiers());
			modifiers = (modifiers != null && !"".equals(modifiers)) ? modifiers + " " : modifiers;
			String typeName = field.getType().getSimpleName();
			String name = field.getName();
			System.out.println("\t" + modifiers + typeName + " " + name + ";");
		}
	}

	private static String joinTypeName(Class[] cls) {
		if (cls.length <= 0)
			return null;
		String returnStr = "";
		for (Class cl : cls) {
			returnStr += cl.getSimpleName() + ", ";
		}
		returnStr = returnStr.substring(0, returnStr.length() - 2);
		return returnStr;
	}
}
