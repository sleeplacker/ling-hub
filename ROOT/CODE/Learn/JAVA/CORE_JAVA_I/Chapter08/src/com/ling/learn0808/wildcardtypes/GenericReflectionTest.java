package com.ling.learn0808.wildcardtypes;

import java.lang.reflect.GenericArrayType;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.lang.reflect.TypeVariable;
import java.lang.reflect.WildcardType;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * 泛型的反射
 * 
 * 该工具类能打印出类或方法定义的各种泛型相关的类型，包括泛型类型、通配符类型、参数化类型、泛型数组
 *
 * Chapter8/com.ling.learn0808.wildcardtypes.GenericReflectionTest.java
 *
 * author lingang
 *
 * createTime 2019-11-20 15:11:05
 *
 */
public class GenericReflectionTest {
	public static void main(String[] args) throws ClassNotFoundException {
		String name;
		if (args.length > 0) {
			name = args[0];
		} else {
			Scanner input = new Scanner(System.in);
			name = input.next();
		}

		Class cl = Class.forName(name);
		printClass(cl);
		for (Method m : cl.getDeclaredMethods()) {
			printMethod(m);
		}
		System.out.print("}");
	}

	private static void printMethod(Method m) {
		String name = m.getName();
		System.out.print(Modifier.toString(m.getModifiers()));
		System.out.print(" ");
		System.out.print(makeTypes(m.getTypeParameters(), "<", ", ", "> ", true));
		System.out.print(makeType(m.getGenericReturnType(), false));
		System.out.print(" ");
		System.out.print(name);
		System.out.print("(");
		System.out.print(makeTypes(m.getGenericParameterTypes(), "", ", ", "", false));
		System.out.println(");");
	}

	private static void printClass(Class cl) {
		String ms = Modifier.toString(cl.getModifiers());
		System.out.print(("").equals(ms) ? "" : (ms + " "));
		System.out.print("class " + cl.getSimpleName());
		System.out.print(makeTypes(cl.getTypeParameters(), "<", ", ", ">", true));
		System.out.println(" {");
	}

	private static String makeTypes(Type[] typeParameters, String start, String split, String end,
			boolean isDefinition) {
		String result = "";
		if (typeParameters.length <= 0) {
			return result;
		}
		if (" extends ".equals(start) && Arrays.equals(typeParameters, new Type[] { Object.class })) {
			return result;
		}
		String[] strs = new String[typeParameters.length];
		for (int i = 0; i < strs.length; ++i) {
			Type t = typeParameters[i];
			strs[i] = makeType(t, isDefinition);
		}
		result = start + String.join(split, strs) + end;
		return result;
	}

	private static String makeType(Type t, boolean isDefinition) {
		String result = "";
		if (t instanceof Class) {// Class类型(例如：List类型)
			result = ((Class) t).getSimpleName();
		} else if (t instanceof TypeVariable) {// 泛型类型参数(例如：List<E>中的E)
			TypeVariable tv = (TypeVariable) t;
			result = tv.getName();
			if (isDefinition) {
				Type[] ts = tv.getBounds();
				String types = makeTypes(ts, " extends ", "&", "", true);
				result = result + types;
			}
		} else if (t instanceof WildcardType) {// 通配符类型(例如：List<?>中的?)
			WildcardType wt = (WildcardType) t;
			String ubs = makeTypes(wt.getUpperBounds(), " extends ", "&", "", false);
			String lbs = makeTypes(wt.getLowerBounds(), " super ", "&", "", false);
			result = result + "?" + ubs + lbs;
		} else if (t instanceof ParameterizedType) {// 参数化类型(例如：List<?>)
			ParameterizedType pt = (ParameterizedType) t;
			Type owner = pt.getOwnerType();
			if (owner != null) {
				result = result + makeType(owner, false) + ".";
			}
			result = result + makeType(pt.getRawType(), false)
					+ makeTypes(pt.getActualTypeArguments(), "<", ", ", ">", false);
		} else if (t instanceof GenericArrayType) {// 泛型数组类型(例如：T[])
			GenericArrayType gat = (GenericArrayType) t;
			result = result + makeType(gat.getGenericComponentType(), isDefinition) + "[]";
		}
		return result;
	}
}

/**
 * 该类用于测试上面定义的工具类
 *
 * Chapter8/com.ling.learn0808.wildcardtypes.GenericTest
 *
 * author lingang
 *
 * createTime 2019-11-20 16:54:57
 *
 */
class GenericTest<T> {
	public <U extends Comparable<U>> U fun1(U[] us) {
		return null;
	}

	public List<? extends T> fun2(List<? super T> ts) {
		return null;
	}

	public <V extends Comparable<? super V>> List<V> fun3(List<?> ts) {
		return null;
	}
}
