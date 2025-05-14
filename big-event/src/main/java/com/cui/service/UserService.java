package com.cui.service;

import com.cui.pojo.User;

/**
 * @author 崔佩谣
 * @date 2025/5/14 16:44
 * @description: userservice接口
 */
public interface UserService {
    //根据用户名查询用户
    User findByUserName(String username);
    //注册
    void register(String username, String password);
}
