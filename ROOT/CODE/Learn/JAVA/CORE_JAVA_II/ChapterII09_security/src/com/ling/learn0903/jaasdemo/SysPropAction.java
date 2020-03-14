package com.ling.learn0903.jaasdemo;

import java.security.*;

public class SysPropAction implements PrivilegedAction {
	private String propertyName;

	public SysPropAction(String propertyName) {
		this.propertyName = propertyName;
	}

	@Override
	public Object run() {
		return System.getProperty(propertyName);
	}
}
