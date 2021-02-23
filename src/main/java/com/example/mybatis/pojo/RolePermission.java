package com.example.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsAutoIncrement;
import com.gitee.sunchenbin.mybatis.actable.annotation.IsKey;
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
@Table(name = "mybatis_role_permission")
@ApiModel(value = "角色权限关联表")
public class RolePermission implements Serializable {

    private static final long serialVersionUID = 5199200306752426433L;

    @TableId(type = IdType.AUTO)
    @IsKey
    @IsAutoIncrement
    @Column(comment = "主键ID")
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @Column(name = "role_id", comment = "角色ID")
    @ApiModelProperty(value = "角色ID")
    private Long roleId;

    @Column(name = "permission_id", comment = "权限ID")
    @ApiModelProperty(value = "权限ID")
    private Long permissionId;
}
