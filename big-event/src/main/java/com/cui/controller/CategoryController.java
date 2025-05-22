package com.cui.controller;

import com.cui.pojo.Category;
import com.cui.pojo.Result;
import com.cui.service.CategoryService;
import com.cui.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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
	@PostMapping
	public Result add(@RequestBody @Validated Category category) {
		// 调用categoryService的add方法，将category作为参数传入
		categoryService.add(category);//
		// 返回成功结果
		return Result.success();
	}
	//获取列表
	@GetMapping
	public Result<List<Category>> list(){
     List<Category>cs=categoryService.list();
		return Result.success(cs);
	}
	//获取文章分类详情
	@GetMapping("/detail")
	public Result<Category> findById(@RequestParam Integer id){//1.相应参数不是一组Category 2.请求参数是从前端传过来的

		/* Map<String,Object> map = ThreadLocalUtil.get();
		Integer id= (Integer) map.get("id"); //todo ThreadLocalUtil放的是用户相关的信息*/
		//获取
		Category c=categoryService.findById(id);
		//判断
		if (c==null){
			return Result.error("没有该分类");
		}
		//返回
		return Result.success(c);
	}
	//更新文章分类
	@PutMapping
	public Result<Category> update(@RequestBody @Validated Category category){//前端传进来的数据放在Category中
		//数据校验
		//更新
		categoryService.update(category);
		//返回
		return Result.success();
	}
	//删除文章分类
	@DeleteMapping
	public Result delete(@RequestParam Integer id){
		// 根据id删除数据
		categoryService.delete(id);
		return Result.success();
	}
}
