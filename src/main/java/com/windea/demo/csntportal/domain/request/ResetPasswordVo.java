package com.windea.demo.csntportal.domain.request;

import com.windea.demo.csntportal.GlobalConsts;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户重置密码表单的视图对象。
 */
public class ResetPasswordVo {
	/** 密码。 */
	@NotEmpty(message = "{user.password.notEmpty}")
	@Pattern(regexp = GlobalConsts.RE_PASSWORD, message = "{user.password.pattern}")
	private String password;


	public String getPassword() {
		return password;
	}
}
