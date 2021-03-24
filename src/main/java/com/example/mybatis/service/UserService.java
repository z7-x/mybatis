package com.example.mybatis.service;

import com.example.mybatis.pojo.Permission;
import com.example.mybatis.pojo.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @Classname x
 * @Description TODO
 * @Date 2021/2/7 10:56 上午
 * @Author z7-x
 */
public interface UserService {

    int addUser(User user);

    int removeUser(Long id);

    int updateUser(User user);

    User findUserById(Long id);

    List<User> queryUsersByArray(int currPage, int pageSize);

    List<User> getUserRoleList();

    /**
     * 查询用户名查询用户信息
     */
    User findByUserName(String userName);

    /**
     * 根据用户名查询角色
     */
    //根据用户名查询 用户及用户对应的所有角色
    User findRolesByUserName(String username);

    /**
     * 获取角色对应的权限信息
     */
    List<Permission> findPermsByRoleId(Long id);

}
