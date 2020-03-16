package com.ling.learn0903.jaasdemo2;

import java.security.Principal;
import java.util.Objects;

/**
 * 
 *
 * ChapterII09_security/com.ling.learn0903.jaasdemo2.SimplePrincipal.java
 *
 * author lingang
 *
 * createTime 2020-03-17 01:26:35
 *
 */
public class SimplePrincipal implements Principal {
	/**
	 * 
	 */
	private String descr;
	private String value;

	/**
	 * Constructs a SimplePrincipal to hold a description and a value.
	 * 
	 * @param descr
	 *            the description
	 * @param value
	 *            the associated value
	 */
	public SimplePrincipal(String descr, String value) {
		this.descr = descr;
		this.value = value;
	}

	/**
	 * Returns the role name of this principal.
	 * 
	 * @return the role name
	 */
	public String getName() {
		return descr + "=" + value;
	}

	/**
	 * 此方法判断角色是否具有读取指定属性的权限
	 */
	public boolean equals(Object otherObject) {
		if (this == otherObject)
			return true;
		if (otherObject == null)
			return false;
		System.out.println("THIS : " + this.getClass().getSimpleName() + "/" + this.getName());
		System.out.println("OTHER：" + otherObject.getClass() + "/" + ((Principal) otherObject).getName());
		/**
		 * 因为JAASTest.policy中的grant principal
		 * com.ling.learn0903.jaasdemo2.SimplePrincipal语句找不到SimplePrincipal，所以使用的是默认的sun.security.provider.PolicyParser$PrincipalEntry，所以这里不区分Principal类型
		 */
		// if (getClass() != otherObject.getClass()) return false;
		// SimplePrincipal other = (SimplePrincipal) otherObject;
		return Objects.equals(getName(), ((Principal) otherObject).getName());
	}

	public int hashCode() {
		return Objects.hashCode(getName());
	}
}
