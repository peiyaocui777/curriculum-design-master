package com.cui.controller;

import com.cui.pojo.Result;
import com.cui.pojo.User;
import com.cui.service.UserService;
import com.cui.utils.JwtUtil;
import com.cui.utils.Md5Util;
import com.cui.utils.ThreadLocalUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;

/**
 * @author 崔佩谣
 * @date 2025/5/14 16:39
 * @description: Controller类
 */
@RestController //todo 这个注解什么作用 @RestController注解表示这是一个Controller类，处理HTTP请求，将方法的返回值转换成JSON格式给前端
@RequestMapping("/user")//@RequestMapping注解用来映射url地址
@Validated
public class UserController {
    @Resource//注入依赖，与@Autowrite一样 从ioc容器里面把UserService取出放到这里
    private UserService userService;
    //实现注册
    @PostMapping("/register")//映射post请求
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password){
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
    //登录的方法
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username,@Pattern(regexp = "^\\S{5,16}$") String password){//Result<String>指定该方法返回值类型是字符串？gwt
    //password是没有加密的密码
        User loginUser = userService.findByUserName(username);
        //查找用户名是否存在
        if (loginUser==null){
            return Result.error("用户名错误");
        }
        //用户名存在，对比密码是否正确（对传入的password加密后再比较）
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())){//T登录成功
            //定义一个map集合，存放loginUser的username password
            Map<String,Object> claims = new HashMap<>();
            claims.put("username",loginUser.getUsername());
            claims.put("password",loginUser.getPassword());
            //调用JwtUtils生成jwt令牌
            String token = JwtUtil.genToken(claims);

            return Result.success(token);
        }
        //密码错误，提示错误信息
        return Result.error("密码错误");
    }
    //获取用户信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(/* @RequestHeader(name = "Authorization") String token */){
       //解析token，获取username
        //Map<String, Object> map = JwtUtil.parseToken(token);
        Map<String,Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        User user = userService.findByUserName(username);
        return Result.success(user);
    }
    //更新用户信息
    @PutMapping("/update")
    public Result update(@RequestBody User user){//@RequestBody注解把JSON格式转化为目标格式？
      userService.update(user);
      return Result.success();
    }
}

