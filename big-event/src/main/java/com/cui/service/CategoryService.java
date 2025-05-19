package com.cui.service;

import com.cui.pojo.Category;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/19 0:32
 * @Description: TODO
 */

public interface CategoryService {
	//新增文章列表
	void add(Category category);
	//所有列表
	List<Category> list();
	//根据id获取文章详情
	Category findById(Integer id);
	//更新文章分类
	void update(Category category);
}
