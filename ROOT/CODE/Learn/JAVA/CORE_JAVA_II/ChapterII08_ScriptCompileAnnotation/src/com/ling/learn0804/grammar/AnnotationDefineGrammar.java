package com.ling.learn0804.grammar;

import java.lang.annotation.Repeatable;

/**
 * 注解定义语法
 * 
 * 注解接口隐式扩展自java.lang.annotation.Annotation接口
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0804.grammar.AnnotationDefineGrammar.java
 *
 * author lingang
 *
 * createTime 2020-03-02 01:01:57
 *
 */
// 定义该注解可以重复使用，为了实现这一点，需要再定义一个注解容器即AnnotationContainer
@Repeatable(AnnotationContainer.class)
public @interface AnnotationDefineGrammar {
	// 元素格式：[类型 元素名称 default 默认值] ，这里的默认值不是必输，但是不能设置为null
	// 这里的类型包括很多种，具体参考第380页顶上
	String assignedTo() default "none";
	// String assignedTo() default null;//元素默认值不允许为null

	int severity();

	int value() default 0;

	SuppressWarnings sw() default @SuppressWarnings("xxx");// 注解元素类型可以也是注解
	// AnnotationDefineGrammar self() default
	// AnnotationDefineGrammar("");//wrong，但是不能自己引用自己

}
