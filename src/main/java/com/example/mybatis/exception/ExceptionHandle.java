package com.example.mybatis.exception;

import com.example.mybatis.pojo.result.ResultBean;
import com.example.mybatis.pojo.result.ResultEnum;
import com.example.mybatis.utils.ResultUtil;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * 异常处理类
 * @author yizl
 * @param <T>
 *
 */
@ControllerAdvice
@ResponseBody
public class ExceptionHandle<T> {
	
	@ExceptionHandler(value = Exception.class)
	public ResultBean<T> handle(Exception e) {
		if(e instanceof YiMallException) {
			YiMallException yiMallException=(YiMallException) e;
			return ResultUtil.error(yiMallException.getResultEnum());
		}else {
			return ResultUtil.error(ResultEnum.UNKNOW_ERROR);
		}
	}
}
