package com.ling.learn0507.reflect;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 使用反射机制访问对象内部信息，包括所有private域
 *
 * Chapter5/com.ling.learn0507.reflect.ReflectVisitObjectInsideTest.java
 *
 * author lingang
 *
 * createTime 2019-10-21 02:28:06 
 *
 */
public class ReflectVisitObjectInsideTest {
	public static void main(String[] args) throws IllegalArgumentException, IllegalAccessException {
		Map map = new HashMap<>();
		map.put("Name", "ling");
		List list = new ArrayList();
		int[] iArr = { 1, 2, 3, 4, 5 };
		list.add(iArr);
		list.add(map);
		// System.out.println(list);
		System.out.println(new ObjectAnalyzer().toString(list));
	}
}

class ObjectAnalyzer {
	private ArrayList<Object> visited = new ArrayList<>(); // 保存已解析的对象列表，解决循环引用导致无限递归问题

	public String toString(Object obj) throws IllegalArgumentException, IllegalAccessException {
		String result = "";
		if (obj == null)
			return null;
		if (visited.contains(obj))
			return ". . .";
		visited.add(obj);
		if (obj instanceof String)
			return (String) obj; // 字符串类型直接返回
		Class type = obj.getClass();
		if (type.isArray()) { // 数组类型
			Class itemType = type.getComponentType();// 返回数组元素类型
			result += (itemType + "[]{");
			for (int i = 0; i < Array.getLength(obj); ++i) {
				Object item = Array.get(obj, i);
				if (itemType.isPrimitive()) {
					// 基本数据类型直接拼接
					result += item + ", ";
				} else {
					result += toString(item) + ", ";
				}
			}

			if (result.endsWith(", "))
				result = result.substring(0, result.length() - 2);
			return result += "}";
		}

		// 遍历类的所有域，包括父类和往上的所有类中的域，直到没有父类
		result += (type.getName() + "[");
		do {
			Field[] fields = type.getDeclaredFields();
			AccessibleObject.setAccessible(fields, true); // 设置域为可访问权限，不管是否是什么访问级别
			for (Field field : fields) {
				if (!Modifier.isStatic(field.getModifiers())) {
					// 只查看非static域
					Class fType = field.getType();
					Object value = field.get(obj);
					result += (field.getName() + "=");
					if (fType.isPrimitive()) {
						result += (value);
					} else {
						result += toString(value);
					}
					result += ", ";
				}

			}
			type = type.getSuperclass(); // 查看父类域
		} while (type != null);
		if (result.endsWith(", "))
			result = result.substring(0, result.length() - 2);
		result += "]";
		return result;
	}

}
