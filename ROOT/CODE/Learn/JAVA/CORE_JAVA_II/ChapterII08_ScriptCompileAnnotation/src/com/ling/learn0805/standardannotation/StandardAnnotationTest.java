package com.ling.learn0805.standardannotation;

import javax.activation.DataSource;
import javax.annotation.Generated;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.annotation.Resource;

/**
 * 标准注解
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0805.standardannotation.StandardAnnotationTest.java
 *
 * author lingang
 *
 * createTime 2020-03-02 19:29:37
 *
 */

public class StandardAnnotationTest {
	@SuppressWarnings("unused") // 阻止警告信息

	private String name;
	private int age;

	public static void main(String[] args) {
		fun99();
	}

	@Deprecated // 标注这个方法不推荐使用，编译时会有警告
	public static void fun99() {

	}

	@Generated(value = { "" }) // 代码生成工具生成的代码，为了和用户写的代码区分，方便代码生成工具自动删除
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
	/*用于管理资源的注解*/
	@PostConstruct//控制对象生命周期，对象构造后
	void postAction(){
		
	}
	
	@PreDestroy//控制对象生命周期，对象销毁前
	void preAction(){
		
	}
	
	@Resource(name="jdbc/mydb")//用于数据库连接资源注入
	private DataSource soruce;

}
