package com.windea.demo.csntportal.enums;

/**
 * 专业级别的枚举。
 */
public enum ProfessionLevel {
	NORMAL, PROFESSIONAL, VERY_PROFESSIONAL;

	@Override
	public String toString() {
		return switch(this) {
			case NORMAL -> "普通";
			case PROFESSIONAL -> "专业";
			case VERY_PROFESSIONAL -> "非常专业";
		};
	}
}
