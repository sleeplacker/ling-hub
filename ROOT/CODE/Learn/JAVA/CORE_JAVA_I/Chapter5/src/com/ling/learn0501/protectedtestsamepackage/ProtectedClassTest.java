package com.ling.learn0501.protectedtestsamepackage;

/**
 * 为什么普通类(非内部类)不能被声明为protected的
 * 
 * 因为protected关键字是修改类中的域的，让域可以被子类访问，而类本身是在包中，要么只在包中可见，要么任何地方都可见
 * 比喻：父亲(父类)的财产(域)可以继承给孩子(子类)，但是父亲本人不能继承给孩子
 *
 * Chapter5/com.ling.learn0501.protectedtestsamepackage.ProtectedClassTest.java
 *
 * author lingang
 *
 * createTime 2019-10-17 22:28:27 
 *
 */
public class ProtectedClassTest {

}

//protected class A { //Illegal modifier for the class A; only public, abstract & final are permitted
//	
//}