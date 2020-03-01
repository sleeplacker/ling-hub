package com.ling.learn0802.compilerapi;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.FileObject;
import javax.tools.ForwardingJavaFileManager;
import javax.tools.JavaCompiler;
import javax.tools.JavaCompiler.CompilationTask;
import javax.tools.JavaFileManager;
import javax.tools.JavaFileObject;
import javax.tools.JavaFileObject.Kind;
import javax.tools.SimpleJavaFileObject;
import javax.tools.ToolProvider;

/**
 * 使用CompilationTask来进行更复杂的编译操作，包括下面几点，具体参考第363页8.2.2小节：
 * 
 * 1. 控制源码来源
 * 
 * 2. 控制类文件存放位置
 * 
 * 3. 监听在编译过程中产生的错误和警告信息
 * 
 * 4. 在后台运行编译器
 * 
 * 本例实现了从StringBuilder中读取源码，并将编译后的class文件保存到包含字节数组的对象中，并且可以将编译异常信息存放到Diagnostic对象，可以查看其中的各种诊断信息
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0802.compilerapi.CompilationTaskTest.java
 *
 * author lingang
 *
 * createTime 2020-03-01 15:56:16
 *
 */
public class CompilationTaskTest {
	public static void main(String[] args) {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		JavaFileManager manager = compiler.getStandardFileManager(null, null, null);
		final List<ByteArrayJavaClass> classFileObjects = new ArrayList<>();
		manager = new ForwardingJavaFileManager<JavaFileManager>(manager) {

			@Override
			public JavaFileObject getJavaFileForOutput(Location location, String className, Kind kind,
					FileObject sibling) throws IOException {
				if (className.startsWith("com.x.")) {
					ByteArrayJavaClass fileObject = new ByteArrayJavaClass(className);
					classFileObjects.add(fileObject);
					return fileObject;
				}
				return super.getJavaFileForOutput(location, className, kind, sibling);
			}

		};
		// 使用字符串创建java源码
		JavaFileObject source = buildSource();
		// 创建诊断信息收集器，以记录编译过程中的诊断信息
		DiagnosticCollector<JavaFileObject> diagnostics = new DiagnosticCollector<>();
		CompilationTask task = compiler.getTask(null, manager, diagnostics, null, null, Arrays.asList(source));
		Boolean result = task.call();

		if (result) {
			System.out.println("编译成功");
			// 查看编译结果
			System.out.println(new String(classFileObjects.get(0).getBytes()));
		} else {
			System.out.println("编译失败");
			// 遍历诊断信息
			for (Diagnostic<? extends JavaFileObject> d : diagnostics.getDiagnostics()) {
				System.out.println("诊断类型：" + d.getKind() + "，诊断信息：" + d.getMessage(Locale.CHINA) + "，问题所在行："
						+ d.getLineNumber() + "，列：" + d.getColumnNumber());
			}
		}
	}

	/**
	 * 用字符串创建java源代码
	 * 
	 * @param frameClassName
	 * @return
	 */
	private static JavaFileObject buildSource() {
		StringBuilderJavaSource source = new StringBuilderJavaSource("com.x.TestHello");
		source.append("package com.x;\n");
		source.append("public class TestHello {\n");
		source.append("public static void1 main(String[] args) {\n");
		source.append("System.out.println(\"Hello Compiler!\");\n");
		source.append("}}\n");
		return source;
	}
}

/**
 * 提供另一种读取源代码方式，即从StringBuilder中读取，而不是默认方式(从磁盘中获取)
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0802.compilerapi.CompilationTaskTest.java
 *
 * author lingang
 *
 * createTime 2020-03-01 16:05:11
 *
 */
class StringBuilderJavaSource extends SimpleJavaFileObject {
	private StringBuilder code;

	public StringBuilderJavaSource(String name) {
		super(URI.create("string:///" + name.replace('.', '/') + Kind.SOURCE.extension), Kind.SOURCE);
		code = new StringBuilder();
	}

	@Override
	public CharSequence getCharContent(boolean ignoreEncodingErrors) {
		return code;
	}

	public void append(String str) {
		code.append(str);
		code.append("\n");
	}
}

/**
 * 提供另一种存放类文件的方式，存放到字节数组中，而不是默认方式(存放在磁盘文件中)
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0802.compilerapi.CompilationTaskTest.java
 *
 * author lingang
 *
 * createTime 2020-03-01 16:13:49
 *
 */
class ByteArrayJavaClass extends SimpleJavaFileObject {
	private ByteArrayOutputStream stream;

	public ByteArrayJavaClass(String name) {
		super(URI.create("bytes:///" + name), Kind.CLASS);
		stream = new ByteArrayOutputStream();
	}

	@Override
	public OutputStream openOutputStream() {
		return stream;
	}

	public byte[] getBytes() {
		return stream.toByteArray();
	}

}
