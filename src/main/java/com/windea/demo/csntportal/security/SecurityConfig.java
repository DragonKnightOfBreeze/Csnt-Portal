package com.windea.demo.csntportal.security;

import com.windea.demo.csntportal.enums.Role;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	private final JwtUserDetailsServiceImpl service;

	public SecurityConfig(JwtFilter jwtFilter, JwtEntryPoint jwtEntryPoint, JwtUserDetailsServiceImpl service) {
		this.jwtFilter = jwtFilter;
		this.jwtEntryPoint = jwtEntryPoint;
		this.service = service;
	}

	/**
	 * 配置验证管理构建器。
	 */
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(service).passwordEncoder(passwordEncoder());
	}

	/**
	 * 配置Http安全验证规则。
	 */
	@Override
	protected void configure(HttpSecurity httpSecurity) throws Exception {
		//禁用CSRF（跨站点请求伪造）防护（因为使用了Jwt），允许跨域
		httpSecurity.csrf().disable().cors().and()
			//因为基于令牌，所以不需要启用会话
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
			.authorizeRequests()
			//NOTE 在这里配置路径权限规则
			.antMatchers("/account/**").authenticated()
			.antMatchers("/study-column/**").authenticated()
			.antMatchers("/admin/**").hasRole(Role.ADMIN.toString())
			// 对于获取token的rest api要允许匿名访问
			.antMatchers("/auth/**").permitAll()
			.antMatchers("/druid/**").permitAll()
			.anyRequest().permitAll()
			//登录：转发到`/login`。使用默认配置
			//记住登录：指定一个checkbox的name为`remember-me`。使用默认配置
			//退出登录：转发到`/logout`。使用默认配置
			.and()
			//配置异常处理器
			.exceptionHandling().authenticationEntryPoint(jwtEntryPoint)
			.and();

		//添加Jwt过滤器到用户密码验证过滤器之前
		httpSecurity.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
		//禁用缓存
		httpSecurity.headers().cacheControl();
	}


	/**
	 * 验证管理器的Bean。
	 */
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	/**
	 * 密码编码器的Bean。
	 */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}

