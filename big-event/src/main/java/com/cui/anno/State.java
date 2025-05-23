package com.cui.anno;

import com.cui.validation.StateValidation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/23 0:42
 * @Description: TODO
 */
@Target({ElementType.FIELD})//属性
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(
		validatedBy = {StateValidation.class }
)
//自定义注解State
public @interface State {
	String message() default "{状态请填入已发布/草稿}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
