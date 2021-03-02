package com.example.mybatis.pojo;

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
@ApiModel(value = "分页条件")
public class PageInfo {

    @ApiModelProperty(value = "当前页数")
    private int pageNum;

    @ApiModelProperty(value = "每页条数")
    private int pageSize;

    @ApiModelProperty(value = "总总数")
    private long total;

}
