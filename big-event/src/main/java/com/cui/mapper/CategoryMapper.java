package com.cui.mapper;

import com.cui.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

/**
@author: xuYuYu
@createTime: 2025/5/19 0:35
@Description: TODO
*/
@Mapper
public interface CategoryMapper {
	@Insert("insert into category (category_name,category_alias,create_user,create_time,update_time)" +
			"values (#{categoryName},#{categoryAlias,#{createUser},#{createTime},#{updateTime})"
	)
	void add(Category category);
}
