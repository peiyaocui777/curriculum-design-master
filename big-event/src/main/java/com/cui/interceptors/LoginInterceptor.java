package com.cui.interceptors;

import com.cui.pojo.Result;
import com.cui.utils.JwtUtil;
import com.cui.utils.ThreadLocalUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/16 22:37
 * @Description: TODO
 */
@Component
@Slf4j
public class LoginInterceptor implements HandlerInterceptor {
	@Autowired
	private StringRedisTemplate stringRedisTemplate;
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String token = request.getHeader("Authorization");
		// log.info("收到的Authorization头值: {}", token);
		System.out.println(request.getRequestURI() + "被拦截URL");
		log.info("{}被拦截URL ", request.getRequestURI());
		//
		try {
			//redis中的token是否存在
			ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
			String redisToken = stringStringValueOperations.get(token);
			if (redisToken==null){
				 throw new RuntimeException();
			}
			Map<String, Object> claims = JwtUtil.parseToken(token);//
			ThreadLocalUtil.set(claims);
			// 没有报错，放行
			return true;
		} catch (Exception e) {
			// 报错
			// 状态响应码设为401（需要一个Response对象，从参数列表传进来）
			response.setStatus(401);
			// 提示错误信息
			return false;
		}
	}
	//删除线程

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		ThreadLocalUtil.remove();
	}
}
