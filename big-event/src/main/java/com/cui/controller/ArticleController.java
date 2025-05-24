package com.cui.controller;

import com.cui.pojo.Article;
import com.cui.pojo.PageBean;
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
	//文章列表（条件分页）根据条件查询文章,带分页
	@GetMapping
	public Result<PageBean<Article>> list(Integer pageNum, Integer pageSize,
	@RequestParam(required = false) Integer categoryId,@RequestParam(required = false) String state){
	PageBean<Article>pageBean=articleService.list(pageNum,pageSize,categoryId,state);
	return Result.success(pageBean);
	}
}
