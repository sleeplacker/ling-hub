package com.ling.learn0806.processor;

import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.RoundEnvironment;
import javax.annotation.processing.SupportedAnnotationTypes;
import javax.annotation.processing.SupportedSourceVersion;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.TypeElement;

/**
 * 使用注解处理器来处理源码级别的注解
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0806.processor.ToStringAnnotationProcessor.java
 *
 * author lingang
 *
 * createTime 2020-03-03 00:52:57
 *
 */
@SupportedAnnotationTypes("com.ling.0805.*")
@SupportedSourceVersion(SourceVersion.RELEASE_8)
public class ToStringAnnotationProcessor extends AbstractProcessor {

	@Override
	public boolean process(Set<? extends TypeElement> annotations, RoundEnvironment roundEnv) {
		System.out.println("处理");
		return false;
	}

}
