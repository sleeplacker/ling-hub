package com.ling.learn0902.securitymanager;

import java.io.IOException;

/**
 * 安全策略文件，这里只是简单测试了一下文件访问安全策略的使用，更多使用参考第418页-424页
 * 
 * jre/bin/policytool工具可以用于编辑policy文件
 *
 * ChapterII09_security/com.ling.learn0902.securitymanager.PolicyFileTest.java
 *
 * author lingang
 *
 * createTime 2020-03-12 20:41:01
 *
 */
public class PolicyFileTest {
	public static void main(String[] args) throws IOException {
		// 指定自定义安全策略文件，如果注释掉这一行，那么会使用${JAVA_HOME}/jre/lib/security/java.policy中的策略，该策略文件没有配置对bin/com/ling/learn0902/securitymanager/*的访问权限，所以会报下面错：
		// java.security.AccessControlException: access denied ("java.io.FilePermission" "bin\com\ling\learn0902\securitymanager\TextFile.txt" "write")
		System.setProperty("java.security.policy", "src/com/ling/learn0902/securitymanager/MyPolicy.policy");
		// 开启安全策略检查，如果不开启，那不会进行安全策略检查，上面设置的策略文件自然就无效了
		System.setSecurityManager(new SecurityManager());
		//读bin/com/ling/learn0902/securitymanager/下面的文件
		System.out.println(FileOperation.readFile());
		//写bin/com/ling/learn0902/securitymanager/下面的文件
		FileOperation.wirteFile("EEEEEE\n");
		System.out.println(FileOperation.readFile());
	}
}
