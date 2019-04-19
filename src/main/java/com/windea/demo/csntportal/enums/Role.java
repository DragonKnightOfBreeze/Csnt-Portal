package com.windea.demo.csntportal.enums;

/**
 * 用户身份的枚举。
 */
public enum Role {
	VISITOR, STUDENT, TEACHER, ADMIN;

	@Override
	public String toString() {
		return switch(this) {
			case VISITOR -> "游客";
			case STUDENT -> "学生";
			case TEACHER -> "教师";
			case ADMIN -> "管理员";
		};
	}
}
