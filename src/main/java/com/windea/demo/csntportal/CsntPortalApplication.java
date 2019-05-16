package com.windea.demo.csntportal;

import com.windea.demo.csntportal.interceptor.LogInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.*;

/**
 * SpringBoot应用程序的启动类。
 */
@SpringBootApplication
public class CsntPortalApplication implements WebMvcConfigurer {
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		//添加跨域请求映射。默认为空。
		registry.addMapping("/**").allowedOrigins("*").allowedMethods("*")
			.allowCredentials(false)
			.maxAge(3600);
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		//配置拦截器
		registry.addInterceptor(new LogInterceptor()).addPathPatterns("/**");
	}

	public static void main(String[] args) {
		SpringApplication.run(CsntPortalApplication.class, args);
	}
}
