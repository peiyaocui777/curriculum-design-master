package com.cui.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import com.cui.pojo.User;
import org.apache.ibatis.annotations.Update;

/**
 * @author 崔佩谣
 * @date 2025/5/14 16:46
 * @description: userMapper接口
 */
@Mapper //?什么用 是Mybatis的注解 标记这个接口是数据库操作接口
public interface UserMapper {
    // 根据用户名查询用户
    @Select("select * from user where username=#{username}")
    // #{username}是参数占位符，会被方法参数替换，并防止SQL注入
    User findByUserName(String username);

    // 添加用户
    @Insert("insert into user(username,password,create_time,update_time)"+
            "values (#{username},#{password},now(),now())")
    void add(String username, String password);
@Update("update user set nickname=#{nickname},email=#{email},update_time=#{updateTime}where id=#{id}")
	void update(User user);
}
