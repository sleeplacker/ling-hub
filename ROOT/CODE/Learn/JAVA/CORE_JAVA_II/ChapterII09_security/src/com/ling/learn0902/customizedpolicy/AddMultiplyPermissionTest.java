package com.ling.learn0902.customizedpolicy;

/**
 * 定制权限测试
 *
 * ChapterII09_security/com.ling.learn0902.customizedpolicy.AddMultiplyPermissionTest.java
 *
 * author lingang
 *
 * createTime 2020-03-13 01:53:11
 *
 */
public class AddMultiplyPermissionTest {
	public static void main(String[] args) {
		// 设置权限文件，该权限文件引用的是自定义的权限类
		System.setProperty("java.security.policy",
				"src/com/ling/learn0902/customizedpolicy/AddMultiplyPermission.policy");
		// 开启权限检查功能
		System.setSecurityManager(new SecurityManager());
		System.out.println(safeAdd(40, 40));// OK，结果小于100
		System.out.println(safeMultiply(8, 8));// OK，结果小于100
		System.out.println(safeAdd(55, 66));// ERRRO，结果大于100
		System.out.println(safeMultiply(11, 12));// ERRRO，结果大于100
	}

	public static int safeAdd(int a, int b) {
		SecurityManager manager = System.getSecurityManager();
		if (manager != null) {
			manager.checkPermission(new AddMultiplyPermission(a + "," + b, "add"));
		}

		return a + b;
	}

	public static int safeMultiply(int a, int b) {
		SecurityManager manager = System.getSecurityManager();
		if (manager != null) {
			manager.checkPermission(new AddMultiplyPermission(a + "," + b, "mul"));
		}

		return a * b;
	}
}
