package com.example.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mybatis_role")
@ApiModel(value = "角色表")
public class Role implements Serializable {

    private static final long serialVersionUID = 5199200306752426433L;

    @TableId(type = IdType.AUTO)
    @Column(comment = "角色ID",isKey = true,isAutoIncrement = true)
    @ApiModelProperty(value = "角色ID")
    private Long id;

    @Column(name = "role_name", comment = "角色名称")
    @ApiModelProperty(value = "角色名称")
    private String roleName;

    @ApiModelProperty(value = "角色对应的用户列表")
    private List<User> users;

    @ApiModelProperty(value = "角色所拥有的操作权限列表")
    private List<Permission> permissions;
}
