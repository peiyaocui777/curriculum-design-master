package com.cui.exception;

import com.cui.pojo.Result;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/15 21:50
 * @Description: TODO 全局异常处理器
 */
@RestControllerAdvice //标记该类是做异常处理的，Rest将结果转换成JSON格式
public class GlobalExceptionHandler {
	@ExceptionHandler
	public Result handleException(Exception e){
    e.printStackTrace();//异常信息输出到控制台
		return Result.error(StringUtils.hasLength(e.getMessage())? e.getMessage() : "操作失败");
	}
}
