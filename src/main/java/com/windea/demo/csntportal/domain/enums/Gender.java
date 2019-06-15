package com.windea.demo.csntportal.domain.enums;

import com.windea.java.template.ITextEnum;

/**
 * 用户性别的枚举。
 */
public enum Gender implements ITextEnum {
	MALE("男性"),
	FEMALE("女性"),
	ALIEN("外星人");


	private final String text;

	Gender(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
