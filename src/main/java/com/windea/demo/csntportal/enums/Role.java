package com.windea.demo.csntportal.enums;

/**
 * 用户身份的枚举。
 */
public enum Role {
	ROLE_VISITOR, ROLE_STUDENT, ROLE_TEACHER, ROLE_ADMIN;

	@Override
	public String toString() {
		return switch(this) {
			case ROLE_VISITOR -> "游客";
			case ROLE_STUDENT -> "学生";
			case ROLE_TEACHER -> "教师";
			case ROLE_ADMIN -> "管理员";
		};
	}
}
