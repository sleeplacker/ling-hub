package com.ling.learn0302.comments;

/**
 * 3.可自动生成文档的注释
 * 
 * @author lingang
 *
 */
public class CommentsTest {
	public static void main(String[] args) {
		int age = 17; // 1.单行注释
		/*
		 * 姓 名 2.多行注释
		 */
		String name = "ling";

		// /*
		// /*/
		// */ 4.多行注释不能嵌套

		System.out.println(name + ":" + age);
	}
}