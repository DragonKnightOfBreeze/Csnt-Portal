package com.windea.demo.csntportal.enums;

import com.windea.java.template.ITextEnum;

/**
 * 用户登录类型。
 */
public enum LoginType implements ITextEnum {
	BY_USERNAME("用户名登录"),
	BY_PHONE_NUM("手机号码登录"),
	BY_EMAIL("邮箱地址登录");


	private final String text;

	LoginType(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
