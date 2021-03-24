package com.example.mybatis.pojo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "返回结果集")
public class ResultBean<T> implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "返回code")
	private int code;

	@ApiModelProperty(value = "返回message")
	private String msg;

	@ApiModelProperty(value = "返回值")
	private T data;
}
