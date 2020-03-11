package com.ling.learn0902.securitymanager;

import java.io.FilePermission;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.security.ProtectionDomain;

import com.ling.learn0901.validate.ByteCodeValidationTest;

/**
 * 类保护域：每个类都有一个保护域，当SecurityManager类需要检查某个权限时，
 * 它要查看当前位于调用堆栈上的所有方法的类，然后获得所有类的保护域，并访问每个保护域，
 * 其权限集合是否允许执行当前正在被检查的操作，如果所有的域都同意，那么检查得以通过。
 * 否则会抛出SecurityException异常。之所以要检查整改堆栈的方法的类，是因为这些方法涉及的类权限可能不同，必须保证每个类都有权限
 *
 * ChapterII09_security/com.ling.learn0902.securitymanager.ProtectionDomainTest.java
 *
 * author lingang
 *
 * createTime 2020-03-12 01:09:19
 *
 */
public class ProtectionDomainTest {
	public static void main(String[] args) {
		// 获取类的保护域
		ProtectionDomain pd = ByteCodeValidationTest.class.getProtectionDomain();
		/* 获取保护域中的代码位置属性 */
		CodeSource cs = pd.getCodeSource();
		// 获取用于类文件签名的证书链
		System.out.println(cs.getCertificates());
		// 获取类文件代码位置
		System.out.println(cs.getLocation());

		/* 获取保护域中的权限集 */
		PermissionCollection pc = pd.getPermissions();
		System.out.println(pc);
		// 检查权限集中是否包含特定权限
		System.out.println(pc.implies(new FilePermission(
				"\\D:\\MyGitHubLib\\ling-hub\\ROOT\\CODE\\Learn\\JAVA\\CORE_JAVA_II\\ChapterII09_security\\bin\\-",
				"read")));
	}
}
