package com.cui.controller;

import com.cui.pojo.Category;
import com.cui.pojo.Result;
import com.cui.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/19 0:30
 * @Description: TODO
 */
@RestController
@RequestMapping("/category")
public class CategoryController {
	@Autowired
	private CategoryService categoryService;

	// 新增文章分类
	public Result add(@RequestBody @Validated Category category) {
		categoryService.add(category);//
		return Result.success();
	}
}
