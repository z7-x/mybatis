package com.example.mybatis.mapper;

import com.example.mybatis.pojo.Role;
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
public interface PermissionMapper {


    /**
     * 根据账号查询用户信息
     */
    Role findPermissionByPermissionName(String permissionName);
}
