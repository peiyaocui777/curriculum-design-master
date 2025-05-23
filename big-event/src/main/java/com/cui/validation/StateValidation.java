package com.cui.validation;

import com.cui.anno.State;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @author: xuYuYu
 * @createTime: 2025/5/23 0:47
 * @Description: TODO
 */
public class StateValidation implements ConstraintValidator<State,String> {
	@Override
	public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
		if (s==null){
			return false;
		}
		if (s.equals("已发布")||s.equals("草稿")){
			return true;
		}
		return false;
	}
}
