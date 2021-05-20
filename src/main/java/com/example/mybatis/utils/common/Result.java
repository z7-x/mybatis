package com.example.mybatis.utils.common;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.Page;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@ApiModel("返回数据")
@NoArgsConstructor
@AllArgsConstructor
public class Result<T> implements Serializable {

    @ApiModelProperty(value = "状态码", example = "200")
    protected Integer code;

    @ApiModelProperty(value = "返回数据")
    protected T data;

    @ApiModelProperty(value = "返回消息", example = "OK")
    protected String msg;

    public Result(Integer code, String msg, T data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public Result(Integer code, T data) {
        this.code = code;
        this.data = data;
    }

    public Result(String msg) {
        this.msg = msg;
    }

    public Result(@NotNull List<T> data, Pager pager, Integer code, String msg) {
        this.data = (T) new com.example.mybatis.utils.common.Data(data, pager);
        this.code = code;
        this.msg = msg;
    }

    public Result(@NotNull List<T> data, Pager pager) {
        this(data, pager, ResponseCode.RESPONSE_CODE_SUCCESS.getCode(), ResponseCode.RESPONSE_CODE_SUCCESS.getMessage());
    }

    public Result(@NotNull Page<T> data) {
        this(data.getContent(), new Pager(data.getPageable().getPageNumber(), data.getPageable().getPageSize(), data.getTotalElements()));
    }
}
