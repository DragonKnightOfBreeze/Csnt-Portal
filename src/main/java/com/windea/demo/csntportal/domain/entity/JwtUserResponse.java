package com.windea.demo.csntportal.domain.entity;

import java.io.Serializable;

/**
 * Jwt用户响应类。用于回应用户基本信息到前端。
 * 不需要进行参数验证。
 */
public class JwtUserResponse implements Serializable {
	private static final long serialVersionUID = -2582893279697162978L;

	/** Jwt用户名。 */
	private String username;

	/** Jwt身份。 */
	private String role;

	/** Jwt令牌。 */
	private String token;

	/** Jwt类型 */
	private String type = "Bearer";


	public JwtUserResponse(String token, String username, String role) {
		this.token = token;
		this.username = username;
		this.role = role;
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
}
