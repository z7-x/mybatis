package com.example.mybatis.exception;


import com.example.mybatis.pojo.result.ResultEnum;

public class YiMallException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private ResultEnum resultEnum;
	
	public YiMallException(ResultEnum resultEnum) {
		super(resultEnum.getMsg());
		this.resultEnum=resultEnum;
	}

	public ResultEnum getResultEnum() {
		return resultEnum;
	}

}
