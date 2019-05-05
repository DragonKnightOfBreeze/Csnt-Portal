package com.windea.demo.csntportal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * SpringBoot应用程序的启动类。
 */
@SpringBootApplication
public class CsntPortalApplication implements WebMvcConfigurer {
	/**
	 * 添加跨域请求映射。默认为空。
	 */
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
			.allowCredentials(false)
			.maxAge(3600);
	}

	public static void main(String[] args) {
		SpringApplication.run(CsntPortalApplication.class, args);
	}
}
