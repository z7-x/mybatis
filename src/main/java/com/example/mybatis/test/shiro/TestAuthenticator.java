package com.example.mybatis.test.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;

/**
 * @Classname TestAuthenticator
 * @Description TODO ini读取人员权限信息
 * @Date 2021/3/12 2:29 下午
 * @Author z7-x
 */
public class TestAuthenticator {
    public static void main(String[] args) {
        //1、创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();
        //2、给安全管理器设置 realm
        securityManager.setRealm(new IniRealm("classpath:shiro.ini"));
        //3、SecurityUtils 给全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //4、关键对象 subject 主体
        Subject subject = SecurityUtils.getSubject();

        //5、创建令牌:相当于在浏览器页面用户输入的
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "1111");
        try {
            System.out.println("认证状态：" + subject.isAuthenticated());
            subject.login(token);
            System.out.println("认证状态：" + subject.isAuthenticated());
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误");
        }
    }
}
