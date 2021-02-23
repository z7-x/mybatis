package com.example.mybatis.service;

import com.example.mybatis.pojo.Role;
import com.example.mybatis.pojo.User;

import java.util.List;
import java.util.Map;

/**
 * @Classname x
 * @Description TODO
 * @Date 2021/2/7 10:56 上午
 * @Author z7-x
 */
public interface RoleService {

    /**
     * 角色用户列表
     * @return
     */
    List<Role> getRoleUserList(int currPage, int pageSize);

    List<Role> getRolePermissionList();
}
