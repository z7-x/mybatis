package com.example.mybatis.test.shiro.realm;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.subject.Subject;

/**
 * @Classname TestCustomerRealm
 * @Description TODO 测试自定义Realm
 * @Date 2021/3/15 3:09 下午
 * @Author z7-x
 */
public class TestCustomerRealm {

    public static void main(String[] args) {
        //1、创建安全管理器对象
        DefaultSecurityManager securityManager = new DefaultSecurityManager();

        //2、注入Realm
        CustomerMD5Realm realm = new CustomerMD5Realm();
        //---加入md5算法验证
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        credentialsMatcher.setHashAlgorithmName("md5");
        //散列算法
        credentialsMatcher.setHashIterations(1024);
        realm.setCredentialsMatcher(credentialsMatcher);
        securityManager.setRealm(realm);

        //3、SecurityUtils 给全局安全工具类设置安全管理器
        SecurityUtils.setSecurityManager(securityManager);
        //4、关键对象 subject 主体
        Subject subject = SecurityUtils.getSubject();

        //5、创建令牌:相当于在浏览器页面用户输入的
        UsernamePasswordToken token = new UsernamePasswordToken("zhangsan", "123");
        try {
            subject.login(token);
            System.out.println("登录成功" + subject.isAuthenticated());
        } catch (IncorrectCredentialsException e) {
            System.out.println("密码错误");
        } catch (UnknownAccountException e) {
            System.out.println("用户名错误");
        } catch (AuthenticationException e) {
            e.printStackTrace();
        }

        //授权
        if (subject.isAuthenticated()) {
            //基于角色控制
            System.out.println("授权" + subject.hasRole("admin"));
        }
    }
}
