package com.windea.demo.csntportal.domain.vo;

import com.windea.demo.csntportal.enums.RegexConsts;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户重置密码表单的视图对象。
 */
public class UserResetPasswordVo {
	/** 密码。 */
	@NotEmpty(message = "{user.password.notEmpty}")
	@Pattern(regexp = RegexConsts.PASSWORD, message = "{user.password.pattern}")
	private String password;


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
