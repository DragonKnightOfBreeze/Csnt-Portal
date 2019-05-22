package com.windea.demo.csntportal.enums;

import com.windea.java.template.ITextEnum;

/**
 * 专业级别的枚举。
 */
public enum ProfessionLevel implements ITextEnum {
	NORMAL("普通"),
	PROFESSIONAL("专业"),
	VERY_PROFESSIONAL("非常专业");


	private final String text;

	ProfessionLevel(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
