package org.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.pojo.User;

/**
 * @author 崔佩谣
 * @date 2025/5/14 16:46
 * @description: userMapper接口
 */
@Mapper //?什么用
public interface UserMapper {
    //根据用户名查询用户
    @Select("select * from user where username=#{username}")//#{username}
    User findByUserName(String username);
//添加
    @Insert("insert into user(username,password,create_time,update_time)"+
    "values (#{username,#{password},now(),now()")
    void add(String username, String password);
}
