package com.windea.demo.csntportal.interceptor;

import com.windea.commons.base.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 日志拦截器。<br>
 * 用于打印传递到前端的数据。
 */
@Component
public class LogInterceptor implements HandlerInterceptor {
	private static final Log logger = LogFactory.getLog(LogInterceptor.class);

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
		ModelAndView modelAndView) throws Exception {
		modelAndView.getModel().forEach((k, v) -> {
			var message = StringUtils.format("传递数据：\n键：{0}\n值：{1}\n\n", k, v);
			logger.info(message);
		});
	}
}

