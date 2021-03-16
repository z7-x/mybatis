package com.example.mybatis.service;

import com.example.mybatis.pojo.Permission;

/**
 * @Classname PermissionService
 * @Description TODO
 * @Date 2021/3/10 1:56 下午
 * @Author z7-x
 */
public interface PermissionService{

    /**
     * 获取权限信息
     */
    Permission findPermissionByPermissionName(String permissionName);
}
