package com.cui.service;

import com.cui.pojo.Article;
import com.cui.pojo.PageBean;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/22 22:13
 * @Description: TODO
 */
public interface ArticleService {
	//新增文章
	void add(Article article);
 //条件列表分页查询
	PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
