package com.cui.mapper;

import com.cui.pojo.Article;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/22 22:14
 * @Description: TODO
 */
@Mapper
public interface ArticleMapper {
	@Insert("insert into article (title,content,cover_img,state,category_id,create_user,create_time,update_time)" +
			"values (#{title},#{content},#{coverImg},#{state},#{categoryId},#{createUser},#{createTime},#{updateTime})")
	void add(Article article);

	List<Article> list(Integer userId, Integer categoryId, String state);
}
