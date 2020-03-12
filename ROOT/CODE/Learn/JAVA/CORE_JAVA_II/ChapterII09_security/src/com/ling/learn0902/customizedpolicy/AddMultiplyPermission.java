package com.ling.learn0902.customizedpolicy;

import java.security.Permission;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 加和乘运算权限定制，规则是运算结果不能超过100
 * 
 * 50以内两个数可以做加运算，10以内两个数可以做乘运算
 *
 * ChapterII09_security/com.ling.learn0902.customizedpolicy.AddMultiplyPermission.java
 *
 * author lingang
 *
 * createTime 2020-03-13 01:49:02
 *
 */
public class AddMultiplyPermission extends Permission {
	private String action;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public AddMultiplyPermission(String name, String action) {
		super(name);
		this.action = action;
	}

	/**
	 * 该方法检查输入的参数虽然和policy文件中配置的参数不完全相同，但是符合符合policy配置的规则也可通过检查
	 */
	@Override
	public boolean implies(Permission permission) {
		if (!(permission instanceof AddMultiplyPermission))
			return false;
		AddMultiplyPermission other = (AddMultiplyPermission) permission;
		String name = other.getName();
		String[] nums = name.split(",");
		// 取参数中较大的一个值
		Optional<Integer> biggerNum = Stream.of(nums).map(Integer::parseInt).collect(Collectors.minBy((a,b)->a>b?a:b));
		if (biggerNum.get() <= Integer.parseInt(getName()))
			if (action.contains(other.getActions()))
				return true;
		return false;
	}

	/**
	 * 权限检查时，先走这个方法，如果返回true，则检查通过，不会再做下面的检查，如果检查失败，则会走implies检查
	 */
	@Override
	public boolean equals(Object other) {
		if (other == null)
			return false;
		if (!getClass().equals(other.getClass()))
			return false;
		return Objects.equals(this, other);
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName(), action);
	}

	@Override
	public String getActions() {
		return action;
	}

}
