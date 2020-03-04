package com.ling.learn0803.annotationdemo;

import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 这个类的作用是在运行时解析注解
 *
 * ChapterII08_ScriptCompileAnnotation/com.ling.learn0803.annotationdemo.ActionListenerInstaller.java
 *
 * author lingang
 *
 * createTime 2020-03-04 19:56:23 
 *
 */
public class ActionListenerInstaller {
	public static void processAnnotations(Object obj) {
		try {
			Class<?> cl = obj.getClass();
			for (Method m : cl.getDeclaredMethods()) {
				//Method有实现AnnotatedElement接口，AnnotatedElement接口的getAnnotation方法可以获取该方法是否包含指定的注解
				ActionListenerFor a = m.getAnnotation(ActionListenerFor.class);
				if (a != null) {//只处理有指定注解的项
					Field f = cl.getDeclaredField(a.source());
					f.setAccessible(true);
					addListener(f.get(obj), obj, m);
				}
			}
		} catch (ReflectiveOperationException e) {
			e.printStackTrace();
		}
	}

	public static void addListener(Object source, final Object param, final Method m)
			throws ReflectiveOperationException {
		InvocationHandler handler = new InvocationHandler() {

			@Override
			public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
				return m.invoke(param);
			}

		};
		//使用代理方式来调用addActionListener方法
		Object listener = Proxy.newProxyInstance(null, new Class[] { java.awt.event.ActionListener.class }, handler);
		Method adder = source.getClass().getMethod("addActionListener", ActionListener.class);
		adder.invoke(source, listener);
	}
}
