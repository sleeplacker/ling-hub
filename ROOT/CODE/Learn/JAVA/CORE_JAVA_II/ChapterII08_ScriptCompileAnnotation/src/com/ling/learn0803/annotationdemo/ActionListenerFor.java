package com.ling.learn0803.annotationdemo;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 注解接口
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0803.annotationdemo.ActionListenerFor.java
 *
 * author lingang
 *
 * createTime 2020-03-01 19:36:10
 *
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ActionListenerFor {
	String source();
}
