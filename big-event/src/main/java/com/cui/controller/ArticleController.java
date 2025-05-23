package com.cui.controller;

import com.cui.pojo.Article;
import com.cui.pojo.Result;
import com.cui.service.ArticleService;
import com.cui.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;

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
	@Autowired
	private ArticleService articleService;
	//新增文章
	@PostMapping
	public Result add(@RequestBody @Validated Article article){
	articleService.add(article);
	return Result.success();
	}
}
