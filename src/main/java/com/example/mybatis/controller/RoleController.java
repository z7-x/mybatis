package com.example.mybatis.controller;


import com.example.mybatis.pojo.Role;
import com.example.mybatis.service.RoleService;
import com.example.mybatis.utils.JedisUtil;
import com.example.mybatis.utils.RedisUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Classname user
 * @Description TODO
 * @Date 2021/2/7 11:03 上午
 * @Author z7-x
 */
@RestController
@RequestMapping("/role")
@Api(description = "角色模块")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JedisUtil jedisUtil;

    @ApiOperation(value = "sql分页:用户角色列表", notes = "查询所分配的角色的用户列表")
    @RequestMapping(value = "/getRoleUsers", method = RequestMethod.GET)
    public List<Role> getRolePermissions(@RequestParam Integer currPage, @RequestParam Integer pageSize) {
        return roleService.getRoleUserList(currPage,pageSize);
    }

    @ApiOperation(value = "角色权限列表", notes = "查询所分配的角色的权限列表")
    @RequestMapping(value = "/getRolePermissions", method = RequestMethod.GET)
    public List<Role> getRolePermissionList() {
        return roleService.getRolePermissionList();
    }


}
