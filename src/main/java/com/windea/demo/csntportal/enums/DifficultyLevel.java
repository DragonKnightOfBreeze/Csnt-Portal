package com.windea.demo.csntportal.enums;

/**
 * 难度级别的枚举。
 */
public enum DifficultyLevel {
	EASY, NORMAL, HARD, HELL, INHUMAN;

	@Override
	public String toString() {
		return switch(this) {
			case EASY -> "简单";
			case NORMAL -> "普通";
			case HARD -> "困难";
			case HELL -> "炼狱";
			case INHUMAN -> "非人";
		};
	}
}
