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

/**
 * @Classname UserRole
 * @Description TODO
 * @Date 2021/2/5 9:58 上午
 * @Author z7-x
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mybatis_user_role")
@ApiModel(value = "人员角色关联表")
public class UserRole implements Serializable {

    private static final long serialVersionUID = 5199200306752426433L;

    @TableId(type = IdType.AUTO)
    @Column(comment = "主键ID",isKey = true,isAutoIncrement = true)
    @ApiModelProperty(value = "主键ID")
    private Long id;

    @Column(name = "user_id", comment = "人员ID")
    @ApiModelProperty(value = "人员ID")
    private Long userId;

    @Column(name = "role_id", comment = "角色ID")
    @ApiModelProperty(value = "角色ID")
    private Long roleId;
}
