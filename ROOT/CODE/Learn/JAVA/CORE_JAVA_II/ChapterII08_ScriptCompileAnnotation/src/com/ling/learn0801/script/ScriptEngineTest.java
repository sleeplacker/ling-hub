package com.ling.learn0801.script;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.script.Bindings;
import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

/**
 * 脚本引擎的创建和属性查看
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0801.script.ScriptEngineTest.java
 *
 * author lingang
 *
 * createTime 2020-02-29 15:12:04
 *
 */
public class ScriptEngineTest {
	public static void main(String[] args) throws IOException, ScriptException, NoSuchMethodException {
		/* 1. 创建脚本引擎 */
		System.out.println("\n1. 创建脚本引擎");
		ScriptEngineManager manager = new ScriptEngineManager();
		// 获取可用的脚本引擎
		List<ScriptEngineFactory> factorys = manager.getEngineFactories();
		System.out.println(factorys);

		// 获取脚本引擎属性
		for (ScriptEngineFactory factory : factorys) {
			// 引擎名称
			System.out.println(factory.getEngineName());
			// 引擎了解的名字，也就是可以通过这些名字调用manager.getEngineByName方法获取脚本引擎
			System.out.println("\t" + factory.getNames());
			// 引擎支持的脚本文件扩展名，也就是可以通过这些扩展名调用manager.getExtensions方法获取脚本引擎
			System.out.println("\t" + factory.getExtensions());
			// 引擎支持的MIME类型，也就是可以通过这些MIME类型调用manager.getMimeTypes方法获取脚本引擎
			System.out.println("\t" + factory.getMimeTypes());
		}

		// 可以通过名字，脚本文件扩展名或MIME类型获取脚本引擎
		ScriptEngine engine = manager.getEngineByName("Nashorn");
		System.out.println(engine);
		System.out.println(engine.getFactory().getEngineName());
		System.out.println("\t" + engine.getFactory().getNames());
		System.out.println("\t" + engine.getFactory().getExtensions());
		System.out.println("\t" + engine.getFactory().getMimeTypes());
		engine = manager.getEngineByExtension("js");
		System.out.println(engine);
		System.out.println(engine.getFactory().getEngineName());
		System.out.println("\t" + engine.getFactory().getNames());
		System.out.println("\t" + engine.getFactory().getExtensions());
		System.out.println("\t" + engine.getFactory().getMimeTypes());
		engine = manager.getEngineByMimeType("text/javascript");
		System.out.println(engine);
		System.out.println(engine.getFactory().getEngineName());
		System.out.println("\t" + engine.getFactory().getNames());
		System.out.println("\t" + engine.getFactory().getExtensions());
		System.out.println("\t" + engine.getFactory().getMimeTypes());

		/* 2. 调用脚本 */
		System.out.println("\n2. 调用脚本");
		// 直接将脚本以字符串格式传入eval方法
		engine.eval("a = 111");
		engine.eval("b = 222");
		Object result = engine.eval("a+b");
		System.out.println(result);

		// 读入文件的方式调用脚本
		Reader reader = Files.newBufferedReader(Paths.get("src/com/ling/learn0801/script/test.js"));
		result = engine.eval(reader);
		System.out.println(result);

		// 查看多线程执行脚本是否安全
		Object param = engine.getFactory().getParameter("THREADING");
		// null：并发执行不安全
		// MULTITHREADED：并发执行安全，一个线程的执行效果对另外的线程有可能是可视的
		// THREAD-ISOLATED：除了"STATELESS"之外，会为每个线程维护不同的变量绑定
		// STATELESS：除了"THREAD-ISOLATED"之外，脚本不会改变变量绑定
		System.out.println(param);// null

		/* 3. 变量绑定和作用域 */
		System.out.println("\n3. 变量绑定和作用域");
		// 向引擎中添加变量绑定
		engine.put("Name", "linzeyi");
		engine.put("Birthday", LocalDate.of(2019, 9, 3));// 可以绑定java对象
		// 从引擎中获取绑定的变量
		LocalDate birthday = (LocalDate) engine.get("Birthday");
		System.out.println(birthday);

		// 上面添加的变量绑定是添加到引擎作用域中，还可以添加到全局作用域中，方式是将变量绑定添加到引擎管理器中
		manager.put("CurrentTime", LocalTime.now());
		// 全局作用域中的变量绑定对所有引擎都是可见的
		LocalTime now = (LocalTime) engine.get("CurrentTime");
		System.out.println(now);

		// 还可以自定义作用域
		Bindings scope = engine.createBindings();
		// 然后向作用域中添加变量绑定
		scope.put("CurrentDate", LocalDate.now());
		// 这个作用域中的变量绑定只有当前作用域可见，而创建该作用域的引擎也不可见
		LocalDate nowDate = (LocalDate) scope.get("CurrentDate");
		System.out.println(nowDate);
		// 视图从创建该作用域的引擎获取该变量绑定
		nowDate = (LocalDate) engine.get("CurrentDate");
		System.out.println(nowDate);// null
		// 可以将自定义作用域通过eval方法传递给引擎，然后在引擎中引用作用域中的变量绑定
		nowDate = (LocalDate) engine.eval("CurrentDate", scope);
		System.out.println(nowDate);// 2020-02-29

		/* 4. 重定向输入和输出 */
		System.out.println("\n4. 重定向输入和输出");
		System.out.println(engine.getContext().getWriter());// 获取输出写出器
		System.out.println(engine.getContext().getErrorWriter());// 获取错误写出器
		System.out.println(engine.getContext().getReader());// 获取读入写入器
		// 重定向输出

		Path path = Paths.get("src/com/ling/learn0801/script/write_result.txt");
		if (!Files.exists(path)) {
			Files.createFile(path);
		}
		BufferedWriter writer = Files.newBufferedWriter(path);
		engine.getContext().setWriter(new PrintWriter(writer, true));// 设置脚本输出重定向
		// js标准输出方法，会重定向到文件
		engine.eval("print(\"aaa\")");
		engine.eval("print(\"bbb\")");
		// 非js标准输出方法，不会重定向到文件
		engine.eval("java.lang.System.out.println(\"ccc\")");

		// Nashorn引擎没有标准输入源的概念，因此调用setReader没有任何效果

		/* 5. 调用脚本的函数和方法 */
		System.out.println("\n5. 调用脚本的函数和方法");
		// Nashorn引擎支持调用，所以可以转换为Invocable
		Invocable inv_engine = (Invocable) engine;
		// 调用js内置方法
		inv_engine.invokeFunction("print", "ddd");
		// 调用自定义js方法
		engine.eval("function inc1(a) {return a+1;}");
		result = inv_engine.invokeFunction("inc1", 1);
		System.out.println(result);

		/* 如果脚本引擎没有实现Invocable接口，可以使用另一种方式调用，参见第356页底部说明 */

		// 如果脚本语言是面向对象的(js是支持的)，可以使用invokeMethod方法
		engine.eval("function Greeter(how) {this.how = how;}");
		engine.eval("Greeter.prototype.welcome = " + " function(whom) { return this.how+\", \"+whom+\"!\";}");

		Object yo = engine.eval("new Greeter(\"Yo\")");

		result = inv_engine.invokeMethod(yo, "welcome", "World");
		System.out.println(result);

		// 让脚本引擎实现java接口
		engine.eval("function welcome(whom) { return \"Hello, \" + whom + \"!\";}");
		Greeter g = inv_engine.getInterface(Greeter.class);
		result = g.welcome("World");
		System.out.println(result);

		// 在面向对象脚本语言中，可以通过相匹配的Java接口来访问一个脚本类
		g = inv_engine.getInterface(yo, Greeter.class);
		result = g.welcome("World");
		System.out.println(result);

		/* 6. 脚本编译 */
		System.out.println("\n6. 脚本编译");
		CompiledScript script = null;
		if (engine instanceof Compilable)
			script = ((Compilable) engine).compile("1+1");// 这里的script对象就是腳本编译后的结果

		if (script != null) {
			// 如果引擎支持编译，则执行编译结果
			result = script.eval();
			System.out.println("支持编译，" + result);
		} else {
			// 如果引擎不支持编译，则编译执行
			result = engine.eval("1+1");
			System.out.println("不支持编译，" + result);
		}
		// 从执行结果可以看出Nashorn支持脚本编译
	}
}
