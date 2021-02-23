package com.example.mybatis.service.impl;

import com.example.mybatis.mapper.RoleMapper;
import com.example.mybatis.pojo.Role;
import com.example.mybatis.service.RoleService;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**
 * @Classname Us
 * @Description TODO
 * @Date 2021/2/7 10:57 上午
 * @Author z7-x
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> getRoleUserList(int currPage, int pageSize) {
        Map<String, Object> data = new LinkedHashMap<>();
        data.put("currIndex", (currPage - 1) * pageSize);
        data.put("pageSize", pageSize);
        return roleMapper.getRoleUserList(data);
    }

    @Override
    public List<Role> getRolePermissionList() {
        return roleMapper.getRolePermissionList();
    }
}
