package com.example.mybatis.pojo.result;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "排序")
public class Sort {

	@ApiModelProperty(value = "字段名")
	private String field;

	@ApiModelProperty(value = "升序,asc,desc")
    private String order;
}
