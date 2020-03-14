package com.ling.learn0903.jaasdemo;

import java.security.PrivilegedAction;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;

/**
 * JAAS认证测试，按下面步骤测试：
 * 进入工程/bin目录
 * 
 * 执行：jar cvf login.jar com/ling/learn0903/jaasdemo/JAASAuthTest.class
 * 执行：jar cvf action.jar com/ling/learn0903/jaasdemo/SysPropAction.class
 * 执行：java -classpath action.jar;login.jar -Djava.security.policy=com/ling/learn0903/jaasdemo/JAASAuthTest.policy -Djava.security.auth.login.config=com/ling/learn0903/jaasdemo/jaas.config com.ling.learn0903.jaasdemo.JAASAuthTest
 *
 * 测试时如果将JAASAuthTest.policy中的用户名改为和当前登录用户名不一致，则会出现：java.security.AccessControlException: access denied ("java.util.PropertyPermission" "user.home" "read")
 *
 * ChapterII09_security/com.ling.learn0903.jaasdemo.JAASAuthTest.java
 *
 * author lingang
 *
 * createTime 2020-03-15 00:41:39 
 *
 */
public class JAASAuthTest {
	public static void main(String[] args) {
		System.setSecurityManager(new SecurityManager());
		try {
			// 创建登录上下文，这里的Login1对应于JAAS配置文件中的登录描述符
			LoginContext context = new LoginContext("Login1");
			// 建立登录操作，会调用JAAS配置文件中的管理器上的login方法
			context.login();
			System.out.println("认证成功！");
			// 返回认证过的Subject
			Subject subject = context.getSubject();
			PrivilegedAction<String> action = new SysPropAction("user.home");
			String result = Subject.doAsPrivileged(subject, action, null);
			System.out.println(result);
			context.login();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
