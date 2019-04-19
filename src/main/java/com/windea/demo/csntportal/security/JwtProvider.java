package com.windea.demo.csntportal.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * Jwt提供器的组件。
 */
@Component
@PropertySource("classpath:messages.yml")
public class JwtProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${security.jwtSecret}")
	private String jwtSecret;

	@Value("${security.jwtExpiration}")
	private int jwtExpiration;

	/**
	 * 生成口令。
	 */
	public String generate(Authentication authentication) {
		//得到用户信息
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		//得到过期时间
		Date expiryDate = new Date(new Date().getTime() + jwtExpiration * 1000);

		//配置用于验证的字段，起始时间，过期时间，加密方法和加密字符串
		return Jwts.builder()
			.setSubject(userDetails.getUsername())
			.setIssuedAt(new Date())
			.setExpiration(expiryDate)
			.signWith(SignatureAlgorithm.HS512, jwtSecret)
			.compact();
	}

	/**
	 * 验证口令。
	 */
	public boolean validate(String token) {
		try {
			Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
			return true;
		} catch(Exception e) {
			logger.error("JWT Authentication Failed.");
		}
		return false;
	}

	/**
	 * 通过口令得到用户名。
	 */
	public String getUsername(String token) {
		var claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
		return claims.getSubject();
	}
}
