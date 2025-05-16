package com.cui.config;

import com.cui.interceptors.LoginInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/16 22:44
 * @Description: TODO
 */
public class WebConfig implements WebMvcConfigurer {
	// 重写addInterceptor方法
	private LoginInterceptor loginInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 需要一个LoginInterceptor对象
		registry.addInterceptor(loginInterceptor).excludePathPatterns("/login","/register");
	}
}
