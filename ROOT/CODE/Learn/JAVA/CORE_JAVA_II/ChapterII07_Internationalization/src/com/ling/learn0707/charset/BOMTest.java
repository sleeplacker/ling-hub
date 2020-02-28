package com.ling.learn0707.charset;

/**
 * BOM-byte order mark，字节顺序标志：即文件头开始的有U+FEFF来表示大端法还是小端法，UTF-8文件中编码为EF BB BF
 * 
 * 这种带BOM的文件很多编辑器都能处理，比如eclipse就能处理，都能编译，但是在cmd使用javac命令就不行，
 * 
 * 比如此包下的BOMTestWithBOM.java在cmd编译就会报错(注意：因为类文件中包含中文，javac要指定编码：javac -encoding UTF-8 com/ling/learn0707/charset/BOMTestWithBOM.java)
 *
 * ChapterII07_Internationalization/com.ling.learn0707.charset.BOMTest.java
 *
 * author lingang
 *
 * createTime 2020-02-28 19:03:30
 *
 */
public class BOMTest {
	public static void main(String[] args) {
		System.out.println("H W!");
	}
}
