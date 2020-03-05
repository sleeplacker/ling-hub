package com.ling.learn0807.bytedemo;

import java.lang.instrument.*;

import org.objectweb.asm.*;

/**
 * EntryLogger.java中需要从旧的class生成新的class文件，不是很方便。还可以使用代理方式在类加载时转换class文件
 * 
 * 这里要先将本类打包到一个jar包中
 * 
 * 进入本工程根目录/bin下面执行：jar cvfm EntryLoggingAgent.jar  com/ling/learn0807/bytedemo/EntryLoggingAgent.mf com/ling/learn0807/bytedemo/*.class
 * 然后执行：java -javaagent:EntryLoggingAgent.jar=com.ling.learn0807.bytedemo.Item -classpath .;../lib/\* com.ling.learn0807.bytedemo.Item
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0807.bytedemo.EntryLoggingAgent.java
 *
 * author lingang
 *
 * createTime 2020-03-05 22:19:02 
 *
 */
public class EntryLoggingAgent
{
   public static void premain(final String arg, Instrumentation instr)
   {
      instr.addTransformer((loader, className, cl, pd, data) ->
         {
            if (!className.replace("/", ".").equals(arg)) return null;
            ClassReader reader = new ClassReader(data);
            ClassWriter writer = new ClassWriter(
               ClassWriter.COMPUTE_MAXS | ClassWriter.COMPUTE_FRAMES);
            EntryLogger el = new EntryLogger(writer, className);
            reader.accept(el, ClassReader.EXPAND_FRAMES);
            return writer.toByteArray();
      });
   }
}