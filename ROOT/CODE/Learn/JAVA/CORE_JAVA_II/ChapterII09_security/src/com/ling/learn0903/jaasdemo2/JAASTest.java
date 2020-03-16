package com.ling.learn0903.jaasdemo2;

import java.util.Scanner;

import javax.security.auth.Subject;
import javax.security.auth.login.LoginContext;
import javax.security.auth.login.LoginException;

/**
 * 实现自己的登录和角色认证
 * 
 * 操作步骤：
 * 
 * 进入工程bin目录：
 * 执行：jar cvf mylogin.jar com/ling/learn0903/jaasdemo2/JAASTest.class com/ling/learn0903/jaasdemo2/Simple*.class
 * 执行：jar cvf myaction.jar com/ling/learn0903/jaasdemo2/SysPropAction.class
 * 运行：java -classpath mylogin.jar;myaction.jar -Djava.security.policy=com/ling/learn0903/jaasdemo2/JAASTest.policy -Djava.security.auth.login.config=com/ling/learn0903/jaasdemo2/jaas.config com.ling.learn0903.jaasdemo2.JAASTest
 * 输入：如果登录carl用户，虽然登录成功，但是无法访问user.home属性；如果登录harry用户，可以登录成功也能访问user.home属性
 * 
 * 疑问：为什么JAASTest.policy中的grant principal语句找不到自己定义的SimplePrincipal，但是可以找到系统定义的NTUserPrincipal、JMXPrincipal等
 *
 * ChapterII09_security/com.ling.learn0903.jaasdemo2.JAASTest.java
 *
 * author lingang
 *
 * createTime 2020-03-16 23:54:23
 *
 */
public class JAASTest {
	public static void main(final String[] args) throws LoginException {
		System.setSecurityManager(new SecurityManager());
		try (Scanner in = new Scanner(System.in)) {
			System.out.println("输入用户名：");
			String username = in.nextLine();
			System.out.println("输入密码：");
			String password = in.nextLine();
			System.out.println("输入属性名：");
			String propertyName = in.nextLine();

			LoginContext context = new LoginContext("Login1",
					new SimpleCallbackHandler(username, password.toCharArray()));
			context.login();
			System.out.println("登录成功：用户名=" + username + ", " + "密码=" + password + ", " + "属性名=" + propertyName);
			Subject subject = context.getSubject();
			System.out.println("用户角色信息：");

			subject.getPrincipals().forEach(p -> {
				System.out.println("\t" + p.getName());
			});
			System.out.println("属性值：" + Subject.doAsPrivileged(subject, new SysPropAction(propertyName), null));
			context.logout();
		}

	}
}
