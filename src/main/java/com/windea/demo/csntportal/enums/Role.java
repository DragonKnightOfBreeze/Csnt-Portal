package com.windea.demo.csntportal.enums;

/**
 * 用户身份的枚举。
 */
public enum Role {
	VISITOR, STUDENT, TEACHER;

	@Override
	public String toString() {
		return switch(this) {
			case VISITOR -> "游客";
			case STUDENT -> "学生";
			case TEACHER -> "教师";
		};
	}
}
