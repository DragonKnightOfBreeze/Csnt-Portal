package com.windea.demo.csntportal.security;

import com.windea.demo.csntportal.service.impl.JwtUserDetailsServiceImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Jwt过滤器的组件。
 */
@Component
public class JwtFilter extends OncePerRequestFilter {
	private final JwtProvider jwtProvider;
	private final JwtUserDetailsServiceImpl userService;

	public JwtFilter(JwtProvider jwtProvider, JwtUserDetailsServiceImpl userService) {
		this.jwtProvider = jwtProvider;
		this.userService = userService;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	throws ServletException, IOException {
		//得到Jwt令牌
		String jwtToken = getToken(request);
		//如果令牌不为空字符串且验证通过，则进行后续操作
		// 如果我们足够相信token中的数据，也就是我们足够相信签名token的secret的机制足够好
		// 这种情况下，我们可以不用再查询数据库，而直接采用token中的数据
		// 本例中，我们还是通过Spring Security的 @UserDetailsService 进行了数据查询
		// 但简单验证的话，你可以采用直接验证token是否合法来避免昂贵的数据查询
		if(StringUtils.hasText(jwtToken) && jwtProvider.validate(jwtToken)) {
			try {
				String username = jwtProvider.getUsername(jwtToken);
				var userDetails = userService.loadUserByUsername(username);
				//NOTE 参数principal表示身份，对应实现了UserDetails的实体类，参数credentials表示证书，对应密码
				var auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
				SecurityContextHolder.getContext().setAuthentication(auth);
				logger.info("Set Authentication from JWT success. Authenticated user: " + username);
			} catch(Exception e) {
				logger.error("Set Authentication from JWT failed.");
			}
		}
		chain.doFilter(request, response);
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
