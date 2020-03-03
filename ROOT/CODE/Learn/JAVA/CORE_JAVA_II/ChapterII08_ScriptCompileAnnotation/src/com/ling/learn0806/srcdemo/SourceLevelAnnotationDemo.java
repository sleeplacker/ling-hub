package com.ling.learn0806.srcdemo;

//import com.ling.learn0806.processor.ToStrings;

/**
 * 测试ToStringAnnotationProcessor，当然测试之前要先编译ToStringAnnotationProcessor，测试之前先去掉本文件的所有//注释，这里注释只是为了eclipse编译不报错
 * 
 * 编译：在此工程根目录/mybin下执行命令：javac -encoding UTF-8 -d . -processor com.ling.learn0806.processor.ToStringAnnotationProcessor ../src/com/ling/learn0806/srcdemo/*.java
 * 这里编译过后会在mybin/com/ling/learn0806/processor下生产源文件ToStrings.java一级ToStrings.class
 * 
 * 运行：在此工程根目录/mybin下执行命令：java com.ling.learn0806.srcdemo.SourceLevelAnnotationDemo
 * 
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0806.srcdemo.SourceLevelAnnotationDemo.java
 *
 * author lingang
 *
 * createTime 2020-03-03 23:09:28
 *
 */
public class SourceLevelAnnotationDemo {
	public static void main(String[] args) {
		// Rectangle rect = new Rectangle(new Point(10, 10), 20, 30);
		// System.out.println(ToStrings.toString(rect));
	}
}
