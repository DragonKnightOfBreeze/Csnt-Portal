package com.windea.demo.csntportal.security;

import io.jsonwebtoken.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Jwt提供器的组件。
 */
@Component
//使用这个注解从application.yml中的对应前缀的属性注入到对应的字段，必须是组件类或者已经显示声明
@ConfigurationProperties("com.windea.security")
public class JwtProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	private String jwtSecret;

	private int jwtExpiration;

	/**
	 * 生成令牌。
	 */
	public String generate(Authentication authentication) {
		//得到用户信息
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		//得到过期时间
		Date expiryDate = new Date(System.currentTimeMillis() + jwtExpiration * 1000);

		//配置用于验证的字段，起始时间，过期时间，加密方法和加密字符串
		return Jwts.builder()
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

	/**
	 * 验证令牌。
	 */
	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch(Exception e) {
			logger.error("JWT Authentication Failed.");
			return false;
		}
	}


	/**
	 * 得到用户名。
	 */
	public String getUsername(String token) {
		try {
			var claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
			var username = claims.getSubject();
			return username;
		} catch(Exception e) {
			return null;
		}
	}
}
