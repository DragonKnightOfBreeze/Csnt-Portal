package com.windea.demo.csntportal.security;

import com.windea.demo.csntportal.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt过滤器的组件。<br>
 * NOTE 这里通过用户名进行验证，没有证书
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
	private final JwtProvider jwtProvider;
	private final UserService userService;

	public JwtFilter(JwtProvider jwtProvider, UserService userService) {
		this.jwtProvider = jwtProvider;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
		FilterChain filterChain) throws ServletException, IOException {
		//得到Jwt令牌
		String jwt = getToken(httpServletRequest);
		//如果令牌不为空字符串且验证通过，则进行后续操作
		if(StringUtils.hasText(jwt) && jwtProvider.validate(jwt)) {
			try {
				String username = jwtProvider.getUsername(jwt);
				var user = userService.findByUsername(username);
				//NOTE 参数principal表示身份，对应实现了UserDetails的实体类，参数credentials表示证书，对应密码
				var auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(auth);
			} catch(Exception e) {
				logger.error("Set Authentication from JWT failed.");
			}
		}
		filterChain.doFilter(httpServletRequest, httpServletResponse);
	}

	/**
	 * 从Http头部中得到Jwt令牌。
	 */
	private String getToken(HttpServletRequest request) {
		String authHeader = request.getHeader("Authorization");
		if(StringUtils.startsWithIgnoreCase(authHeader, "Bearer ")) {
			return authHeader.replace("Bearer ", "");
		}
		return null;
	}
}
