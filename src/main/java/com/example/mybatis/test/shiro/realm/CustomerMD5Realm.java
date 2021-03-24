package com.example.mybatis.test.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

/**
 * @Classname CustomerMD5Realm
 * @Description TODO
 * @Date 2021/3/16 2:44 下午
 * @Author z7-x
 */
public class CustomerMD5Realm extends AuthorizingRealm {
    /**
     * 授权
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        String primaryPrincipal = (String) principal.getPrimaryPrincipal();
        System.out.println("身份信息：" + primaryPrincipal);
        //根据身份信息、用户名 获取 当前当前的角色信息以及权限信息
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        //将数据库中查询角色信息赋值给权限对象
        simpleAuthorizationInfo.addRole("admin");
        simpleAuthorizationInfo.addRole("user");
        return simpleAuthorizationInfo;
    }

    /**
     * 登录认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //在token中获取用户名
        String userName = (String) token.getPrincipal();
        if ("zhangsan".equals(userName)) {
            //参数1：返回数据库中正确的用户名 参数2：返回数据中正确的密码  参数3：提供当前realm的名字 this.getRealm();
            SimpleAuthenticationInfo simpleAuthenticationInfo =
                    new SimpleAuthenticationInfo("zhangsan",
                            "3449d412688a577344a15cb74f8d29c3",
                            ByteSource.Util.bytes("ps*/x-"),
                            this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
