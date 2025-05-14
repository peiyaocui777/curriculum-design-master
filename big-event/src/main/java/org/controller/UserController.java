package org.controller;

import org.pojo.Result;
import org.pojo.User;
import org.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 崔佩谣
 * @date 2025/5/14 16:39
 * @description: Controller类
 */
@RestController //todo 这个注解什么作用
@RequestMapping("/user")
public class UserController {
    private UserService userService;
    //实现注册
    @PostMapping("/register")
    public Result register(String username,String password){
    //根据用户名查询
        User u=userService.findByUserName(username);
        //判断是否被占用
        if (u==null){
            //没有占用
            //注册
            userService.register(username,password);
            //返回注册成功的信息
            return Result.success();
        }else {
            //占用
            return Result.error("用户名已被占用");
        }
    }
}
