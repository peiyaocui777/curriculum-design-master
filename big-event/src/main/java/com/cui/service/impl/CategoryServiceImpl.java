package com.cui.service.impl;

import com.cui.mapper.CategoryMapper;
import com.cui.pojo.Category;
import com.cui.service.CategoryService;
import com.cui.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/19 0:33
 * @Description: TODO
 */
@Service
public class CategoryServiceImpl implements CategoryService {
	@Autowired
	private CategoryMapper categoryMapper;
	@Override
	public void add(Category category) {
		//设置创建时间 更新时间 更新人
		Map<String,Object> map = ThreadLocalUtil.get();
		Integer id = (Integer) map.get("username");
		category.setCreateUser(id);
		category.setCreateTime(LocalDateTime.now());
		category.setUpdateTime(LocalDateTime.now());
		//add
		categoryMapper.add(category);
	}
}
