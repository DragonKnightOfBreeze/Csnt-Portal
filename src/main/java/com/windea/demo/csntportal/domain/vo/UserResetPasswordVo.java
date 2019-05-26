package com.windea.demo.csntportal.domain.vo;

import com.windea.demo.csntportal.enums.RegexConsts;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * 用户重置密码表单的视图对象。
 */
public class UserResetPasswordVo {
	/** 用户名。 */
	@NotEmpty()
	@Pattern(regexp = RegexConsts.USERNAME)
	private String username;

	/** 密码。 */
	@NotEmpty()
	@Pattern(regexp = RegexConsts.PASSWORD)
	private String password;


	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
