package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Role;
import com.example.mybatis.pojo.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Classname UserMapper
 * @Description TODO
 * @Date 2021/2/7 10:49 上午
 * @Author z7-x
 */
@Mapper
public interface RoleMapper {

    /**
     * 角色对应的用户列表
     */
    List<Role> getRoleUserList(Map<String,Object> data);

    /**
     * 角色对应的权限列表
     */
    List<Role> getRolePermissionList();
}
