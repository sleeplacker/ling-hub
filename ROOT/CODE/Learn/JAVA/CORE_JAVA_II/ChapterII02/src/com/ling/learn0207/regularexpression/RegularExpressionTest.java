package com.ling.learn0207.regularexpression;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 正则表达式
 *
 * ChapterII02/com.ling.learn0207.regularexpression.RegularExpressionTest.java
 *
 * author lingang
 *
 * createTime 2020-02-05 16:20:13
 *
 */
public class RegularExpressionTest {
	public static void main(String[] args) throws IOException {
		/* 1. .匹配任意字符，有可能不包含行终止符\n和\r，取决于标志位的设置 */
		System.out.println("abcABC123 ,.!@#$%^&*()[]\t\f".matches(".+"));// true

		/* 2. \是转义字符，\.表示句号 */
		System.out.println("...abc".matches("[\\.ab]+"));// false

		/* 3. ^和$分别匹配开头和结尾 */
		Pattern pattern = Pattern.compile("^ja[0-9]+va$");
		System.out.println(pattern.matcher("i like ja6va").find());// false，找不到匹配部分
		pattern = Pattern.compile("ja[0-9]+va");// 不带^和$版本的正则表达式
		System.out.println(pattern.matcher("i like ja6va").find());// true，能找到正则表达式匹配的部分

		/* 4. 正则表达式组合 */
		// AB形式，表示任何A的匹配后面跟着B的匹配
		System.out.println("123abc".matches("[0-9]+[a-z]+"));// true
		System.out.println("123abc".matches("[a-z]+[0-9]+"));// false

		// A|B形式，表示任何A或B的匹配
		System.out.println("123abc".matches("[0-9a-z]+|[0-9A-Z]+"));// false

		/* 5. 正则表达式量词，+表示1个或多个，*表示0或多个，?表示0或1个 */
		System.out.println("123abc".matches("[0-9]+[a-z]*[A-Z]?"));// true

		/* 6. 量词的吝啬匹配和贪婪匹配 */
		System.out.println("123abc".matches("[0-9a-z]+abc"));// true，吝啬匹配(默认是吝啬模式)，[0-9a-z]+匹配到了1
		System.out.println("123abc".matches("[0-9a-z]+?abc"));// true，吝啬匹配(用?指明为吝啬模式)，[0-9a-z]+匹配到了1
		System.out.println("123abc".matches("[0-9a-z]++abc"));// false，贪婪匹配，[0-9a-z]++匹配到了123abc
		System.out.println("123abc".matches("[0-9]++abc"));// true，贪婪匹配，[0-9]++匹配到了123

		/* 7_1. 分组匹配-普通情况 */
		pattern = Pattern.compile("([+-]*)([0-9]+)");
		Matcher matcher = pattern.matcher("+666");
		System.out.println("整体是否匹配：" + matcher.matches());
		System.out.println("分组1匹配的内容：" + matcher.group(1));
		System.out.println("分组2匹配的内容：" + matcher.group(2));

		matcher = pattern.matcher("999");
		System.out.println("整体是否匹配：" + matcher.matches());
		System.out.println("分组1匹配的内容：" + matcher.group(1));
		System.out.println("分组2匹配的内容：" + matcher.group(2));

		/* 7_2. 分组匹配-嵌套分组：分组顺序按左括号出现顺序 */
		pattern = Pattern.compile("(([+-]*[0-9]*)([\\.]*))([0-9]+)");
		matcher = pattern.matcher("-12.88");
		matcher.matches();// 必须先调这个方法才能调group方法
		System.out.println("第0组内容：" + matcher.group());
		System.out.println("第0组起始位置：" + matcher.start());
		System.out.println("第0组结束位置：" + matcher.end());
		System.out.println("第1组内容：" + matcher.group(1));
		System.out.println("第1组起始位置：" + matcher.start(1));
		System.out.println("第1组结束位置：" + matcher.end(1));
		System.out.println("第2组内容：" + matcher.group(2));
		System.out.println("第2组起始位置：" + matcher.start(2));
		System.out.println("第2组结束位置：" + matcher.end(2));
		System.out.println("第3组内容：" + matcher.group(3));
		System.out.println("第3组起始位置：" + matcher.start(3));
		System.out.println("第3组结束位置：" + matcher.end(3));
		System.out.println("第4组内容：" + matcher.group(4));
		System.out.println("第4组起始位置：" + matcher.start(4));
		System.out.println("第4组结束位置：" + matcher.end(4));

		/* 8. 标志位，标志位详细信息参考110页 */
		// Pattern.MULTILINE
		pattern = Pattern.compile("^[a-z]*java[a-z]*$");
		System.out.println(pattern.matcher("firstline\njava\nlastliine").find());// false，^和$默认只匹配第一行
		pattern = Pattern.compile("^[a-z]*java[a-z]*$", Pattern.MULTILINE);
		System.out.println(pattern.matcher("firstline\njava\nlastliine").find());// true，指定Pattern.MULTILINE标志后，^和$会匹配所有行

		// Pattern.DOTALL
		pattern = Pattern.compile("aa.*aa");
		System.out.println(pattern.matcher("aabb\nbbaa").matches());// false，默认情况.不匹配回车(\r)和换行符(\n)
		pattern = Pattern.compile("aa.*aa", Pattern.DOTALL);
		System.out.println(pattern.matcher("aabb\nbbaa").matches());// true，指定Pattern.DOTALL标志后，.会匹配回车(\r)和换行符(\n)

		/* 9. 查找多个匹配项 */
		pattern = Pattern.compile("aa[0-9]+aa");
		matcher = pattern.matcher("#aa11aaaaa1111aa#");
		while (matcher.find()) {
			System.out.println("找到匹配串：" + matcher.group());
			System.out.println("该匹配串起始位置：" + matcher.start());
			System.out.println("该匹配串结束位置：" + matcher.end());
		}

	}
}
