package com.example.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
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
@Table(name = "mybatis_permission")
@ApiModel(value = "权限表")
@JsonIgnoreProperties(ignoreUnknown = true)
public class Permission implements Serializable {

    private static final long serialVersionUID = 5199200306752426433L;

    @TableId(type = IdType.AUTO)
    @Column(comment = "权限ID", isKey = true, isAutoIncrement = true)
    @ApiModelProperty(value = "权限ID")
    private Long id;

    @Column(name = "permission_name", comment = "权限名称")
    @ApiModelProperty(value = "权限名称")
    private String permissionName;

    @Column(name = "url", comment = "权限对应的资源")
    @ApiModelProperty(value = "权限对应的资源")
    private String url;
}
