package com.example.mybatis.utils.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.util.List;


@lombok.Data
@ApiModel("分页数据")
public class Data<T> {

  @ApiModelProperty(value = "数据")
  private List<T> data;

  @ApiModelProperty(value = "分页")
  private Pager pager;

  public Data(List<T> data, Pager pager) {
    this.data = data;
    this.pager = pager;
  }
}
