package com.windea.demo.csntportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * SpringBoot应用程序的启动类。
 */
@SpringBootApplication
public class CsntPortalApplication {
	/**
	 * 密码编码器的Bean。<br>
	 * TODO 套用到项目中
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public static void main(String[] args) {
		SpringApplication.run(CsntPortalApplication.class, args);
	}

}
