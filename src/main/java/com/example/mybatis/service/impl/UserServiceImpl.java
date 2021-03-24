package com.example.mybatis.service.impl;

import com.example.mybatis.pojo.Permission;
import com.example.mybatis.pojo.User;
import com.example.mybatis.mapper.UserMapper;
import com.example.mybatis.service.UserService;
import com.example.mybatis.utils.SaltUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Classname Us
 * @Description TODO
 * @Date 2021/2/7 10:57 上午
 * @Author z7-x
 */
@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public int addUser(User user) {
        //1.生成随机盐
        String salt = SaltUtils.getSalt(8);
        //2.将随机盐保存到数据
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
        Md5Hash md5Hash = new Md5Hash(user.getPassWord(), salt, 1024);
        user.setPassWord(md5Hash.toHex());
        return userMapper.addUser(user);
    }

    @Override
    public int removeUser(Long id) {
        return userMapper.removeUser(id);
    }

    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    @Override
    public User findUserById(Long id) {
        return userMapper.findUserById(id);
    }

    @Override
    public List<User> queryUsersByArray(int currPage, int pageSize) {
        //查询全部数据
        List<User> users = userMapper.findAll();
        //从第几条数据开始
        int firstIndex = (currPage - 1) * pageSize;
        //到第几条数据结束
        int lastIndex = currPage * pageSize;
        return users.subList(firstIndex, lastIndex); //直接在list中截取
    }

    @Override
    public List<User> getUserRoleList() {
        return userMapper.getUserRoleList();
    }

    @Override
    public User findByUserName(String userName) {
        return userMapper.findUserByUserName(userName);
    }

    @Override
    public User findRolesByUserName(String username) {
        return userMapper.findRolesByUserName(username);
    }

    @Override
    public List<Permission> findPermsByRoleId(Long id) {
        return userMapper.findPermsByRoleId(id);
    }
}
