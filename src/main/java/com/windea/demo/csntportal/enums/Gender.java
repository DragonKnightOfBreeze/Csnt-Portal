package com.windea.demo.csntportal.enums;

/**
 * 用户性别的枚举。
 * NOTE 使用java12的新语法：switch分支选择
 */
public enum Gender {
	MALE, FEMALE;

	@Override
	public String toString() {
		return switch(this) {
			case MALE -> "男性";
			case FEMALE -> "女性";
		};
	}
}
