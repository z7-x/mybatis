package com.example.mybatis.test.shiro.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * @Classname CustomerRealm
 * @Description TODO 自定义Realm
 * @Date 2021/3/15 3:05 下午
 * @Author z7-x
 */
public class CustomerRealm extends AuthorizingRealm {

    /**
     * 授权
     *
     * @param principal
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }

    /**
     * 认证
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //在token中获取用户名
        String userName = (String) token.getPrincipal();
        System.out.println(userName);
        if ("zhangsan".equals(userName)) {
            //参数1：返回数据库中正确的用户名 参数2：返回数据中正确的密码  参数3：提供当前realm的名字 this.getRealm();
            SimpleAuthenticationInfo simpleAuthenticationInfo =
                    new SimpleAuthenticationInfo("zhangsan", "1111", this.getName());
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
