package com.example.mybatis.test.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
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
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //在token中获取用户名
        String userName = (String) token.getPrincipal();
        if ("zhangsan".equals(userName)) {
            //参数1：返回数据库中正确的用户名 参数2：返回数据中正确的密码  参数3：提供当前realm的名字 this.getRealm();
            SimpleAuthenticationInfo simpleAuthenticationInfo =
                    new SimpleAuthenticationInfo("zhangsan",
                            "57433b8f9a36309c894f0f550ccc4468",
                            ByteSource.Util.bytes("ps*/x-"),
                            this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
