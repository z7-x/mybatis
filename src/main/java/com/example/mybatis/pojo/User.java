package com.example.mybatis.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.gitee.sunchenbin.mybatis.actable.annotation.Column;
import com.gitee.sunchenbin.mybatis.actable.annotation.Table;
import com.gitee.sunchenbin.mybatis.actable.constants.MySqlTypeConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @Classname User
 * @Description TODO
 * @Date 2021/2/7 10:45 上午
 * @Author z7-x
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "mybatis_user")
@ApiModel(value = "人员信息")
@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {
        private static final long serialVersionUID = 5199200306752426433L;

        @TableId(type = IdType.AUTO) //mybatis-plus主键注解
        @Column(comment = "用户ID",isKey = true,isAutoIncrement = true)
        @ApiModelProperty(value = "用户ID")
        private Long id;

        @Column(name = "user_name", comment = "用户名称",type = MySqlTypeConstant.VARCHAR,length = 255)
        @ApiModelProperty(value = "用户名称")
        private String userName;

        @Column(name = "pass_word", comment = "用户密码",type = MySqlTypeConstant.VARCHAR,length = 255)
        @ApiModelProperty(value = "用户密码")
        private String passWord;

        @Column(name = "salt", comment = "盐",type = MySqlTypeConstant.VARCHAR,length = 255)
        @ApiModelProperty(value = "盐")
        private String salt;

        @ApiModelProperty(value = "所有用户所属的角色列表")
        private List<Role> roles;
}
