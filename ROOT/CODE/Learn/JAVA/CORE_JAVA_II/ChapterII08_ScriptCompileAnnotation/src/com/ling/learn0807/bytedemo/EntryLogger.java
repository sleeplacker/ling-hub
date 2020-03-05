package com.ling.learn0807.bytedemo;

import java.io.*;
import java.nio.file.*;

import org.objectweb.asm.*;
import org.objectweb.asm.commons.*;

/**
 * 这个类可以读取class文件，然后修改，然后写回
 * 
 * 执行完这个类后，/ChapterII08_ScriptCompileAnnotation/bin/com/ling/learn0807/bytedemo/Item.class会被新class替换，并且可以比较替换后class中多了几行，并且替换后执行Item类会多打印一些信息
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0807.bytedemo.EntryLogger.java
 *
 * author lingang
 *
 * createTime 2020-03-05 19:11:08
 *
 */
public class EntryLogger extends ClassVisitor {
	private String className;

	/**
	 * Constructs an EntryLogger that inserts logging into annotated methods of
	 * a given class.
	 * 
	 * @param cg
	 *            the class
	 */
	public EntryLogger(ClassWriter writer, String className) {
		super(Opcodes.ASM5, writer);
		this.className = className;
	}

	@Override
	public MethodVisitor visitMethod(int access, String methodName, String desc, String signature,
			String[] exceptions) {
		MethodVisitor mv = cv.visitMethod(access, methodName, desc, signature, exceptions);
		return new AdviceAdapter(Opcodes.ASM5, mv, access, methodName, desc) {
			private String loggerName;

			public AnnotationVisitor visitAnnotation(String desc, boolean visible) {
				return new AnnotationVisitor(Opcodes.ASM5) {
					public void visit(String name, Object value) {
						if (desc.equals("Lcom/ling/learn0807/bytedemo/LogEntry;") && name.equals("logger"))
							loggerName = value.toString();
					}
				};
			}

			public void onMethodEnter() {
				if (loggerName != null) {
					visitLdcInsn(loggerName);
					visitMethodInsn(INVOKESTATIC, "java/util/logging/Logger", "getLogger",
							"(Ljava/lang/String;)Ljava/util/logging/Logger;", false);
					visitLdcInsn(className);
					visitLdcInsn(methodName);
					visitMethodInsn(INVOKEVIRTUAL, "java/util/logging/Logger", "entering",
							"(Ljava/lang/String;Ljava/lang/String;)V", false);
					loggerName = null;
				}
			}
		};
	}

	/**
	 * Adds entry logging code to the given class.
	 * 
	 * @param args
	 *            the name of the class file to patch
	 */
	public static void main(String[] args) throws IOException {
		Path path = Paths.get("bin/com/ling/learn0807/bytedemo/Item.class");
		ClassReader reader = new ClassReader(Files.newInputStream(path));// 读class文件
		ClassWriter writer = new ClassWriter(ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
		EntryLogger entryLogger = new EntryLogger(writer,
				path.toString().replace(".class", "").replaceAll("[/\\\\]", "."));
		reader.accept(entryLogger, ClassReader.EXPAND_FRAMES);// 修改class文件
		Files.write(Paths.get("bin/com/ling/learn0807/bytedemo/Item.class"), writer.toByteArray());// 写修改过的class文件，这里覆盖了原class文件
	}
}
