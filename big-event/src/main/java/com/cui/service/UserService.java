package com.cui.service;

import com.cui.pojo.User;
import org.hibernate.validator.constraints.URL;

/**
 * @author 崔佩谣
 * @date 2025/5/14 16:44
 * @description: userservice接口
 */
public interface UserService {
	void update(User user);

	//根据用户名查询用户
    User findByUserName(String username);
    //注册
    void register(String username, String password);
    //更新头像
	void updateAvatar(@URL String avatarUrl);
	//更新pwd
	void updatePwd(String newPwd);
}
