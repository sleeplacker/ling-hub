package com.ling.learn0804.grammar;

/**
 * 注解使用语法 *
 * 默认注解值不是保存在使用注解的地方的，如果使用注解的类已经编译好，并且使用默认注解值，然后注解的定义修改了默认值，重新编译了，这时使用注解的地方用到的默认值也会跟着改变
 *
 * 注解可以使用在哪些地方参考第382页中间
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0804.grammar.AnnotationUseGrammar.java
 *
 * author lingang
 *
 * createTime 2020-03-02 01:08:18
 *
 */
// @AnnotationDefineGrammar(severity = 0, assignedTo = "xxx") // ok，注解赋值顺序无关紧要
// @AnnotationDefineGrammar(severity = 0) // ok，有默认值的注解可以不指定值
// @AnnotationDefineGrammar // 如果所有注解元素都有默认值，可以省略赋值语句
// @AnnotationDefineGrammar(666) // 如果注解只有一个元素，且元素名为value，可以省略赋值语句，直接在括号里面赋值
// @SuppressWarnings("serial")//可以有多个注解
// @AnnotationDefineGrammar(severity = 0, assignedTo = new String("xxx")) //
// wrong，注解赋值必须使用编译时常量
@AnnotationDefineGrammar(severity = 0, assignedTo = "xxx")
@AnnotationDefineGrammar(severity = 0) // 如果注解可以重复使用，可以使用多次同一个注解
public class AnnotationUseGrammar {
	@AnnotationDefineGrammar(severity = 0) // 注解可以使用在域上
	private String name;

	@AnnotationDefineGrammar(severity = 0) // 注解可以使用在方法上
	public String getName() {
		return name;
	}

	public void setName(@AnnotationDefineGrammar(severity = 0) String name) {// 注解可以使用在局部变量上
		this.name = name;
	}

}
