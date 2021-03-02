package com.example.mybatis.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "分页参数过滤")
public class PageQuery<T> {

	@ApiModelProperty(value = "分页条件")
	private PageInfo pageInfo;

	@ApiModelProperty(value = "排序字段")
	private Sort sort;

	@ApiModelProperty(value = "查询参数类")
	private T params;

	@ApiModelProperty(value = "返回结果集")
	private List<T> results;

	@ApiModelProperty(value = "不在T类中的参数")
	private Map<String, String> queryParam;
}
