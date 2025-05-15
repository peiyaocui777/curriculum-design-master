package com.cui.controller;

import com.cui.pojo.Result;
import com.cui.utils.JwtUtil;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/16 0:51
 * @Description: TODO
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
	@GetMapping("/list")
	public Result<String> list(@RequestHeader(name = "Authorization") String token, HttpServletResponse response){
		//验证token（token从参数列表传进来）
		try {
			Map<String, Object> claims = JwtUtil.parseToken(token);//如果报错说明令牌有问题
			//没有报错，显示信息
			return Result.success("所有文章信息。。");
		} catch (Exception e) {
			//报错
			//状态响应码设为401（需要一个Response对象，从参数列表传进来）
			response.setStatus(401);
			//提示错误信息
			return Result.error("未登录");
		}
	}
}
