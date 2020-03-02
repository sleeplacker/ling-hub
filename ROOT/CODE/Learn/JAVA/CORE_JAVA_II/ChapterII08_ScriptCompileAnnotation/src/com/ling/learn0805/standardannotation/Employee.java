package com.ling.learn0805.standardannotation;

/**
 * 父类使用MetaAnnotationTest注解(该注解使用了@Inherited元注解)
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0805.standardannotation.Employee.java
 *
 * author lingang
 *
 * createTime 2020-03-03 00:11:34
 *
 */
@MetaAnnotationTest
public class Employee {

}

//这里其实有个隐式的@MetaAnnotationTest注解
class Manager extends Employee {

}