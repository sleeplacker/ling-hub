package com.ling.learn0805.standardannotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *  元注解-定义注解使用时的注解
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0805.standardannotation.MetaAnnotationTest.java
 *
 * author lingang
 *
 * createTime 2020-03-02 23:06:09 
 *
 */
//该元注解用于指定现在定义的注解可以用在什么位置，类型取值参考第387页
//如果使用该注解，则定义的注解可以使用在任何地方
@Target({ElementType.TYPE,ElementType.METHOD,ElementType.FIELD})
//该元注解指定一条注解应该保存多少时间，取值和说明见第387页
@Retention(RetentionPolicy.CLASS)
//该注解会让一条注解使用后，归档文件中会含有这一条注解
@Documented//注意Documented可以用在自身定义上
//该注解会让一条注解使用后可以被继承到子类
@Inherited
public @interface MetaAnnotationTest {
}
