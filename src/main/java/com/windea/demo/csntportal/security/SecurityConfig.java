package com.windea.demo.csntportal.security;

import com.windea.demo.csntportal.enums.Role;
import com.windea.demo.csntportal.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

//继承WebSecurityConfigurerAdapter以配置SpringSecurity。
//重载configure()方法以排除不需要进行权限校验的url。

/**
 * Spring Security的配置类。
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private final JwtFilter jwtFilter;
	private final JwtEntryPoint jwtEntryPoint;
	private final UserService userService;
	private final PasswordEncoder passwordEncoder;

	public SecurityConfig(JwtFilter jwtFilter, JwtEntryPoint jwtEntryPoint, UserService userService,
		PasswordEncoder passwordEncoder) {
		this.jwtFilter = jwtFilter;
		this.jwtEntryPoint = jwtEntryPoint;
		this.userService = userService;
		this.passwordEncoder = passwordEncoder;
	}

	/**
	 * 配置验证管理构建器。
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService).passwordEncoder(passwordEncoder);
	}

	/**
	 * 配置Http安全验证规则。
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//禁用CSRF（跨站点请求伪造）防护，允许跨域
		http.csrf().disable().cors().and().authorizeRequests()
			//NOTE 在这里配置路径权限规则
			.antMatchers("/account/**").authenticated()
			.antMatchers("/study-column/**").authenticated()
			.antMatchers("/admin/**").hasRole(Role.ADMIN.toString())
			.anyRequest().permitAll()
			//登录：转发到`/login`。使用默认配置
			//记住登录：指定一个checkbox的name为`remember-me`。使用默认配置
			//退出登录：转发到`/logout`。使用默认配置
			.and()
			//配置异常处理器
			.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
			.and()
			//配置会话管理器，启用令牌
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

		//添加Jwt过滤器到用户密码验证过滤器之前
		http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
	}


	/**
	 * 验证管理器的Bean。
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
}

