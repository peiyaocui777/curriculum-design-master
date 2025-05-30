package com.cui.controller;

import com.cui.pojo.Result;
import com.cui.pojo.User;
import com.cui.service.UserService;
import com.cui.utils.JwtUtil;
import com.cui.utils.Md5Util;
import com.cui.utils.ThreadLocalUtil;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.Pattern;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author 崔佩谣
 * @date 2025/5/14 16:39
 * @description: Controller类
 */
@RestController // todo 这个注解什么作用 @RestController注解表示这是一个Controller类，处理HTTP请求，将方法的返回值转换成JSON格式给前端
@RequestMapping("/user")//@RequestMapping注解用来映射url地址
@Validated
public class UserController {
	@Resource// 注入依赖，与@Autowrite一样 从ioc容器里面把UserService取出放到这里
	private UserService userService;
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	// 实现注册
	@PostMapping("/register")// 映射post请求
	public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {
		// 根据用户名查询
		User u = userService.findByUserName(username);
		// 判断是否被占用
		if (u == null) {
			// 没有占用
			// 注册
			userService.register(username, password);
			// 返回注册成功的信息
			return Result.success();
		} else {
			// 占用
			return Result.error("用户名已被占用");
		}
	}

	// 登录的方法
	@PostMapping("/login")
	public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password) {// Result<String>指定该方法返回值类型是字符串？gwt
		// password是没有加密的密码
		User loginUser = userService.findByUserName(username);
		// 查找用户名是否存在
		if (loginUser == null) {
			return Result.error("用户名错误");
		}
		// 用户名存在，对比密码是否正确（对传入的password加密后再比较）
		if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {// T登录成功
			// 定义一个map集合，存放loginUser的username password
			Map<String, Object> claims = new HashMap<>();
			claims.put("username", loginUser.getUsername());
			claims.put("password", loginUser.getPassword());
			claims.put("id", loginUser.getId());
			// 调用JwtUtils生成jwt令牌
			String token = JwtUtil.genToken(claims);
			// token存入redis
			ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
			stringStringValueOperations.set(token, token, 1, TimeUnit.HOURS);

			return Result.success(token);
		}
		// 密码错误，提示错误信息
		return Result.error("密码错误");
	}

	// 获取用户信息
	@GetMapping("/userInfo")
	public Result<User> userInfo(/* @RequestHeader(name = "Authorization") String token */) {
		// 解析token，获取username
		// Map<String, Object> map = JwtUtil.parseToken(token);
		Map<String, Object> map = ThreadLocalUtil.get();
		String username = (String) map.get("username");
		User user = userService.findByUserName(username);
		return Result.success(user);
	}

	// 更新用户信息
	@PutMapping("/update")
	public Result update(@RequestBody @Validated User user) {// RequestBody类
		// TODO @RequestBody注解把JSON格式转化为目标格式？
		//@Validate 权限校验
		userService.update(user);
		return Result.success();
	}

	// 更新用户头像
	@PatchMapping("/updateAvatar")
	public Result updateAvatar(@RequestParam @URL String avatarUrl) {//@RequestParam:1个或者多个参数
		userService.updateAvatar(avatarUrl);
		return Result.success();
	}

	// 更新密码
	@PatchMapping("/updatePwd")
	public Result updatePwd(@RequestBody Map<String, String> params, @RequestHeader("Authorization") String token) {
		// 数据校验
		String oldPwd = params.get("old_pwd");
		String newPwd = params.get("new_pwd");
		String rePwd = params.get("re_pwd");
		// 三个参数是否为空
		if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
			return Result.error("请输入参数");
		}
		// 判断旧密码是否正确（）
		// 得到原密码
		Map<String, Object> map = ThreadLocalUtil.get();
		String username = (String) map.get("username");
		User user = userService.findByUserName(username);// 根据用户名查询用户信息 返回值是User类
		String password = user.getPassword();// 得到的是加密过的pwd
		// 比对pwd
		if (!password.equals(Md5Util.getMD5String(oldPwd))) {
			return Result.error("旧密码不正确");
		}
		// 新密码和旧密码是否一致
		if (newPwd.equals(oldPwd)) {
			return Result.error("新密码与旧密码相同");
		}
		// 新密码和rePwd
		if (!rePwd.equals(newPwd)) {
			return Result.error("两次输入的密码不一致");
		}
		// 更新
		userService.updatePwd(newPwd);
		// 更新后原密码失效
		ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
		//TODO  下面的替换 掉就可以了 继续
		String s = stringStringValueOperations.get(token);
		if (s != null) {
			stringStringValueOperations.getOperations().delete(s);
		}
		// TODO 就是这里有问题 因为获取的有问题所以需要看下 字符串不完整 不是jwt 或者命令有问题 应该是代码写错了

		// TODO 就是命令问题 这个getand delete 是因为 redis 版本太低了 无法使用这个 只能先 get 然后再delete 分开执行 才行  是软件问题 redis 版本
		return Result.success();
	}
}

