package com.ling.learn0802.compilerapi;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

/**
 * 编译器API
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0802.compilerapi.CompilerAPITest.java
 *
 * author lingang S createTime 2020-02-29 18:59:47
 *
 */
public class CompilerAPITest {
	public static void main(String[] args) throws IOException {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		OutputStream out = Files.newOutputStream(Paths.get("src/com/ling/learn0802/compilerapi/out.log"));
		OutputStream err = Files.newOutputStream(Paths.get("src/com/ling/learn0802/compilerapi/err.log"));
		// 第1个参数总是为null，这个参数是从Tool接口继承来的
		// 第2第3个参数分别是输出流和错误流，如果传null，则会使用System.out和System.err
		//后面的参数作为和javac命令后面的参数一样
		int result = compiler.run(null, out, err, "src/com/ling/learn0802/compilerapi/Test.java");
		System.out.println(result);//编译成功了，在当前包下有个class文件，用Navigator或者去文件夹能看到
	}
}
