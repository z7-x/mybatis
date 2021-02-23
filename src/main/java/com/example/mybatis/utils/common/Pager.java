package com.example.mybatis.utils.common;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiModel("分页条件")
public class Pager {

  @ApiModelProperty(value = "页码", example = "0")
  @JsonProperty(value = "page")
  private Integer page;

  @ApiModelProperty(value = "单页记录条数", example = "10")
  private Integer pageSize;

  @ApiModelProperty(value = "记录总数", example = "10")
  @JsonProperty(value = "total")
  private Long total;
}