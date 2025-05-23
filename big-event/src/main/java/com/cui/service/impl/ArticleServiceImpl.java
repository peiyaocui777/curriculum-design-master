package com.cui.service.impl;

import com.cui.mapper.ArticleMapper;
import com.cui.pojo.Article;
import com.cui.service.ArticleService;
import com.cui.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/22 22:13
 * @Description: TODO
 */
@Service
public class ArticleServiceImpl implements ArticleService {
	@Autowired
	private ArticleMapper articleMapper;
	@Override
	public void add(Article article) {
	//给数据库中的create_time update_time赋值，create_user是当前登录的用户
		article.setCreateTime(LocalDateTime.now());
		article.setUpdateTime(LocalDateTime.now());
		Map<String,Object> map = ThreadLocalUtil.get();
		Integer id= (Integer) map.get("id");
		article.setCreateUser(id);
		//新增
		articleMapper.add(article);
	}
}
