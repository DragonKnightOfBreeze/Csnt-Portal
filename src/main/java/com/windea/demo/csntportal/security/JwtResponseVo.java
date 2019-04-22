package com.windea.demo.csntportal.security;

import java.io.Serializable;

/**
 * Jwt响应的视图对象。
 */
public class JwtResponseVo implements Serializable {
	private static final long serialVersionUID = -2582893279697162978L;

	/** Jwt口令。 */
	private String token;

	/** Jwt类型 */
	private String type = "Bearer";

	/** Jwt用户名。 */
	private String username;

	/** Jwt身份。 */
	private String role;


	public JwtResponseVo(String token, String username, String role) {
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

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
