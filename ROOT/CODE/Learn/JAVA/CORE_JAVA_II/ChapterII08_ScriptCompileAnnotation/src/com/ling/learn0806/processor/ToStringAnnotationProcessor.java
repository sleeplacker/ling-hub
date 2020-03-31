package com.ling.learn0806.processor;

import java.beans.*;
import java.io.*;
import java.util.*;

import javax.annotation.processing.*;
import javax.lang.model.*;
import javax.lang.model.element.*;
import javax.tools.*;
import javax.tools.Diagnostic.Kind;

/**
 * 使用注解处理器来处理源码级别的注解
 * 
 * 在此工程根目录/mybin下执行命令：javac -encoding UTF-8  -d . ../src/com/ling/learn0806/processor/*.java
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0806.processor.ToStringAnnotationProcessor.java
 *
 * author lingang
 *
 * createTime 2020-03-03 00:52:57
 *
 */
@SupportedAnnotationTypes("com.ling.learn0806.processor.ToString")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ToStringAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment currentRound) {
		if (annotations.size() == 0)
			return true;
		try {
			JavaFileObject sourceFile = processingEnv.getFiler()
					.createSourceFile("com.ling.learn0806.processor.ToStrings");
			try (PrintWriter out = new PrintWriter(sourceFile.openWriter())) {
				out.println("// Automatically generated by com.ling.learn0806.processor.ToStringAnnotationProcessor");
				out.println("package com.ling.learn0806.processor;");
				out.println("public class ToStrings {");

				for (Element e : currentRound.getElementsAnnotatedWith(ToString.class)) {//遍历使用了ToString注解的所有地方
					System.out.println("find "+ e.getSimpleName().toString());
					if (e instanceof TypeElement) {//TypeElement表示被注解的是类或接口等，其他地方的注解就不会处理了
						System.out.println("\thandle "+ e.getSimpleName().toString());
						TypeElement te = (TypeElement) e;
						writeToStringMethod(out, te);
					}
				}
				out.println("    public static String toString(Object obj) {");
				out.println("        return java.util.Objects.toString(obj);");
				out.println("    }");
				out.println("}");
			}
		} catch (IOException ex) {
			processingEnv.getMessager().printMessage(Kind.ERROR, ex.getMessage());
		}
		return true;
	}

	private void writeToStringMethod(PrintWriter out, TypeElement te) {
		String className = te.getQualifiedName().toString();
		out.println("    public static String toString(" + className + " obj) {");
		ToString ann = te.getAnnotation(ToString.class);//获取注解属性
		out.println("        StringBuilder result = new StringBuilder();");
		if (ann.includeName())
			out.println("        result.append(\"" + className + "\");");
		out.println("        result.append(\"[\");");
		boolean first = true;
		for (Element c : te.getEnclosedElements()) {//获取被注解的类的所有域和方法构成的列表
			String methodName = c.getSimpleName().toString();
			ann = c.getAnnotation(ToString.class);//获取每个域或者方法的ToString注解
			if (ann != null) {//如果有ToString注解的项才进行处理
				if (first)
					first = false;
				else
					out.println("        result.append(\",\");");
				if (ann.includeName()) {//同样检查注解属性
					String fieldName = Introspector.decapitalize(methodName.replaceAll("^(get|is)", ""));//根据方法名称推算出字段名
					// Turn getWidth into width, isDone into done, getURL into
					// URL
					out.println("        result.append(\"" + fieldName + "=" + "\");");
				}
				out.println("        result.append(toString(obj." + methodName + "()));");
			}
		}
		out.println("        result.append(\"]\");");
		out.println("        return result.toString();");
		out.println("    }");
	}
}