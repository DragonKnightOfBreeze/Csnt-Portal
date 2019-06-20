package com.windea.demo.csntportal.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.Date;

//使用@ConfigurationProperties从application.yml中注入对应前缀的属性到对应字段，必须是组件类或者已显式声明
//必须具有对应的set方法，或者以动态方式初始化（例如对象、列表，基本类型除外）
//也可以作为替代使用@Value注解，两种方式都适用于yaml和properties

/**
 * Jwt提供器的组件。
 */
@Component
public class JwtProvider {
	private static final Logger logger = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${com.windea.security.jwtSecret}")
	private String jwtSecret;

	@Value("${com.windea.security.jwtExpiration}")
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
