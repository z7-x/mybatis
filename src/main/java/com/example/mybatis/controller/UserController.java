package com.example.mybatis.controller;


import com.example.mybatis.pojo.User;
import com.example.mybatis.service.UserService;
import com.example.mybatis.utils.common.ResultData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Classname user
 * @Description TODO
 * @Date 2021/2/7 11:03 上午
 * @Author z7-x
 */
@RestController
@RequestMapping("/user")
@Api(description = "用户模块")
public class UserController {

    @Autowired
    private UserService userService;

    @ApiOperation(value = "用户角色列表", notes = "查询所有用户对应的角色列表")
    @RequestMapping(value = "/getUserRoles", method = RequestMethod.GET)
    public ResultData getUserRoleList() {
        ResultData<List<User>> listResultData = new ResultData<>(userService.getUserRoleList());
        return listResultData;
    }

    @ApiOperation(value = "数组分页：用户信息列表", notes = "查询所有用户信息列表")
    @RequestMapping(value = "/findUserAll", method = RequestMethod.GET)
    public List<User> findAll(@RequestParam Integer currPage, @RequestParam Integer pageSize) {
        List<User> user = userService.queryUsersByArray(currPage, pageSize);
        return user;
    }

    @ApiOperation(value = "用户详细列表", notes = "根据用户id查询用户详细信息")
    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    public User findById(@ApiParam(value = "用户id", required = true)
                         @RequestParam Long id) {
        return userService.findUserById(id);
    }

    @ApiOperation(value = "新增用户信息", notes = "传入所需新增的用户对象")
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    public String add(@ApiParam(value = "用户详细实体user", required = true)
                      @RequestBody User user) {
        int i = userService.addUser(user);
        if (i > 0) {
            return "成功";
        }
        return "失败";
    }

    @ApiOperation(value = "更新用户信息", notes = "传入用户id修改用户对象")
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    public String update(@ApiParam(value = "用户详细实体user", required = true)
                         @RequestBody User user) {
        int i = userService.updateUser(user);
        if (i > 0) {
            return "成功";
        }
        return "失败";
    }

    @ApiOperation(value = "删除用户对象", notes = "根据用户id删除用户详细信息")
    @RequestMapping(value = "/deleteUser", method = RequestMethod.GET)
    public String delete(@ApiParam(value = "用户id", required = true)
                         @RequestParam Long id) {
        int i = userService.removeUser(id);
        if (i > 0) {
            return "成功";
        }
        return "失败";
    }


}
