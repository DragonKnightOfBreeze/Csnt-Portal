package com.windea.demo.csntportal.enums;

import com.windea.commons.base.template.IEnumWithText;

/**
 * 难度级别的枚举。
 */
public enum DifficultyLevel implements IEnumWithText {
	EASY("简单"),
	NORMAL("普通"),
	HARD("困难"),
	HELL("地狱"),
	INHUMAN("非人"),
	IMMORTAL("远非常人");


	private final String text;

	DifficultyLevel(String text) {
		this.text = text;
	}

	@Override
	public String text() {
		return this.text;
	}
}
