package com.windea.demo.csntportal.domain.response;

import com.windea.demo.csntportal.enums.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * Jwt响应的视图对象。<br>
 * 用于进行安全验证。
 */
public class JwtResponseVo {
	/** Jwt口令。 */
	private String token;

	/** Jwt类型 */
	private String type = "Bearer";

	/** Jwt用户名。 */
	private String username;

	/** Jwt身份。 */
	@Enumerated(EnumType.STRING)
	private Role role;


	public JwtResponseVo(String token, String username, Role role) {
		this.token = token;
		this.username = username;
		this.role = role;
	}


	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
}
