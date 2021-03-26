package com.example.mybatis.controller;


import com.example.mybatis.pojo.User;
import com.example.mybatis.service.UserService;
import com.example.mybatis.utils.VerifyCodeUtils;
import com.example.mybatis.utils.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;
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
    public Result getUserRoleList() {
        List<User> userRoleList = userService.getUserRoleList();
        Result<List<User>> listResultData = new Result<>();
        listResultData.setData(userRoleList);
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

    @ApiOperation(value = "验证码", notes = "验证码方法")
    @RequestMapping(value = "/getImage", method = RequestMethod.GET)
    public String verificationCode(HttpSession session, HttpServletResponse response) throws IOException {
        //生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        //验证码放入session
        session.setAttribute("code", code);
        //验证码存入图片
        ServletOutputStream os = response.getOutputStream();
        response.setContentType("image/png");
        VerifyCodeUtils.outputImage(220, 60, os, code);
        return code;
    }

    @ApiOperation(value = "退出登录", notes = "退出登录")
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return "退出成功";
    }

    @ApiOperation(value = "登录验证", notes = "处理身份认证")
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@ApiParam(value = "用户名", required = true)
                        @RequestParam String username,
                        @ApiParam(value = "密码", required = true)
                        @RequestParam String password,
                        @ApiParam(value = "验证码", required = true)
                        @RequestParam String code,
                        HttpSession session) {
        //比较验证码
        String codes = (String) session.getAttribute("code");
        //获取主体对象
        Subject subject = SecurityUtils.getSubject();
        try {
            if (codes.equalsIgnoreCase(code)) {
                subject.login(new UsernamePasswordToken(username, password));
                System.out.println("登录成功");
            } else {
                throw new RuntimeException("验证码错误");
            }
        } catch (UnknownAccountException e) {
            e.printStackTrace();
            System.out.println("用户名错误!");
        } catch (IncorrectCredentialsException e) {
            e.printStackTrace();
            System.out.println("密码错误!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }

        /** 权限部分
         List<String> strings = Arrays.asList("user", "product", "admin");
         //认证用户进行授权
         if (subject.isAuthenticated()) {
         //基于角色
         //1.基于角色权限控制：登录的用户是否具有"admin"的这个角色
         System.out.println(subject.hasRole("admin"));
         //2.基于多角色权限控制:依次判断，返回true或者false，是否拥有该角色
         boolean[] roles = subject.hasRoles(strings);
         Arrays.asList(roles).stream().forEach(isRole -> {
         strings.stream().forEach(s -> {
         System.out.println("用户是否有" + s + "角色：" + isRole);
         });
         });
         //3.检查是否拥有角色 subject.checkRoles();
         //4.多个角色一起判断 hasAllRoles,需要同时具备所有权限
         boolean b = subject.hasAllRoles(strings);
         System.out.println(b);

         //基于权限
         //1.基于权限字符串的访问控制  资源标志符：操作：资源类型 subject.isPermittedAll("","");
         System.out.println("权限"+ subject.isPermitted("user:update:01"));
         System.out.println("权限"+ subject.isPermitted("product:*:01"));
         }
         **/
        return "登录失败";
    }
}
