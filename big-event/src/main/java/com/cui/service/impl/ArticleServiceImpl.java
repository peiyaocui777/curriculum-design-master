package com.cui.service.impl;

import com.cui.mapper.ArticleMapper;
import com.cui.pojo.Article;
import com.cui.pojo.PageBean;
import com.cui.service.ArticleService;
import com.cui.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
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

	@Override
	public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
		//创建PageBean对象
		PageBean<Article> pageBean =new PageBean<>();
		//开启分页查询 PageHelper
		PageHelper.startPage(pageNum,pageSize);

		//查询当前用户的文章信息
		Map<String,Object>map=ThreadLocalUtil.get();
		Integer userId= (Integer) map.get("id");
		//调用mapper，得到list集合
		List<Article> list=articleMapper.list(userId,categoryId,state);
		//将list装换成Page类型，使用Page的方法得到总页数和当前页数据
		Page<Article> page= (Page<Article>) list;
		//装载到PageBean
		pageBean.setTotal(page.getTotal());
		pageBean.setItems(page.getResult());
		return pageBean;
	}
}
