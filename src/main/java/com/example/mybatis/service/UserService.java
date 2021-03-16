package com.example.mybatis.service;

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
     * 根据账号查询用户信息
     */
    User findUserByUserName(String userName);
}
