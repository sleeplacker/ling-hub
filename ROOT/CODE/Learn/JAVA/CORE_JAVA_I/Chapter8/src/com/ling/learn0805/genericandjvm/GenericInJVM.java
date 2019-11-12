package com.ling.learn0805.genericandjvm;

/**
 * 泛型和JVM
 * 
 * 1. 对于虚拟机来说-没有泛型类型对象，所有对象都属于普通类
 * 
 * 2. JVM类型擦除规则：
 * 
 * 1) 没有限定类型：如ClassName<T>，那么T会被替换为Object类型
 * 
 * 2) 有限定类型：如ClassName<T extends T1&T2>，那么T会被替换为T1，即替换为声明的第一个限定类型
 * 
 * 3) 调用第二个或者后面的接口或类的方法时，编译器会向该类或接口插入强制类型转换；所以为了提高效率，应该将标签接口(没有方法的接口)放到限定类型的最后
 *
 * Chapter8/com.ling.learn0805.genericandjvm.GenericInJVM.java
 *
 * author lingang
 *
 * createTime 2019-11-13 00:33:56
 *
 */
public class GenericInJVM {

}
