package com.ling.learn0901.classloader;

import com.ling.learn0901.extendedclass.ExtendedClassRefBootClass;
import com.ling.learn0901.extendedclass.ExtendedClassRefExtendedClass;
import com.ling.learn0901.extendedclass.ExtendedClassRefSystemClass;

/**
 * 把jar包放到扩展类加载器路径的弊端
 * 
 * 首先将com.ling.learn0901.extendedclass包打包到一个jar包： 
 * 在工程根目录/bin/下执行：
 * 	 jar cvf extended.jar com/ling/learn0901/extendedclass/*.class
 * 然后执行：
 *  java com.ling.learn0901.classloader.ExtendedClassloaderTest
 *  查看结果，然后将extended.jar放到jdk的jre\lib\ext，比如：D:\Java\jdk1.8.0_40\jre\lib\ext，然后再执行：
 *  java com.ling.learn0901.classloader.ExtendedClassloaderTest
 *  再比较上面的结果，会发现bootClass和extendedClass都能加载到，而SystemClass就加载不到，报错：
 *  java.lang.NoClassDefFoundError: com/ling/learn0901/classloader/SystemClassToBeUsed。
 *  
 *  原因：扩展类加载器对系统类加载器的类路径不可见
 *  
 *  类查找过程：extended.jar在扩展类加载器路径下，且ExtendedClassRefSystemClass.java引用了其他类，
 *  	首先加载ExtendedClassRefSystemClass.class，
 *  	ExtendedClassRefSystemClass.class引用了SystemClassToBeUsed.class，需要加载SystemClassToBeUsed.class，
 *  	加载SystemClassToBeUsed.class，
 *  	首先会找扩展类加载器加载，
 *  	然后扩展类加载器委派给父亲-引导类加载器去加载，
 *  	因为引导类加载器没有父亲，所以引导类加载器开始在其路径下查找类，结果没查找到，返回null，
 *  	然后扩展类加载器收到null，就只能在自己的路径下查找类，结果也没找到，所以报NoClassDefFoundError
 * 
 * 
 * 
 *
 * ChapterII09_security/com.ling.learn0901.classloader.ExtendedClassloaderTest.java
 *
 * author lingang
 *
 * createTime 2020-03-08 01:19:59
 *
 */
public class ExtendedClassloaderTest {
	public static void main(String[] args) {
		ExtendedClassRefBootClass.call();
		ExtendedClassRefExtendedClass.call();
		ExtendedClassRefSystemClass.call();
	}
}
