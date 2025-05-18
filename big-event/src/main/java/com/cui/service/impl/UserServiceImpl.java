package com.cui.service.impl;

import com.cui.mapper.UserMapper;
import com.cui.pojo.User;
import com.cui.service.UserService;
import com.cui.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cui.utils.Md5Util;

import java.time.LocalDateTime;
import java.util.Map;

/**
 * @author 崔佩谣
 * @date 2025/5/14 16:44
 * @description: 类
 */
@Service //todo ？注册到容器里面什么意思
public class UserServiceImpl implements UserService {
    @Autowired //?
    private UserMapper userMapper;

    @Override
    public void update(User user) {
        user.setUpdateTime(LocalDateTime.now());
        userMapper.update(user);
    }

    @Override
    public User findByUserName(String username) {
        //调用mapper
        User u=userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password) {
    //密码加密
        String md5String = Md5Util.getMD5String(password);
        //添加username password
        userMapper.add(username,md5String);//加密后的字符串
    }

    @Override
    public void updateAvatar(String avatarUrl) {
        //从ThreadLocal获取用户id
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userMapper.updateAvatar(avatarUrl,id);
    }

    @Override
    public void updatePwd(String newPwd) {
        //根据id更新
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        //更新//newPwd加密后再传
        userMapper.updatePwd(Md5Util.getMD5String(newPwd),id);
    }
}
