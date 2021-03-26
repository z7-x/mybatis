package com.example.mybatis.shrio.realm;

import com.example.mybatis.pojo.Permission;
import com.example.mybatis.pojo.Role;
import com.example.mybatis.pojo.User;
import com.example.mybatis.service.UserService;
import com.example.mybatis.utils.ApplicationContextUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import java.util.List;

/**
 * @Classname UserRealm
 * @Description TODO 处理用户权限
 * @Date 2021/3/18 6:09 下午
 * @Author z7-x
 */
public class UserRealm extends AuthorizingRealm {

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        //获取身份信息
        String principal = (String) principals.getPrimaryPrincipal();
        System.out.println("调用授权验证: " + principal);
        //根据主身份信息获取角色 和 权限信息
        UserService userService = (UserService) ApplicationContextUtils
                .getBean("userService");
        User user = userService.findRolesByUserName(principal);
        //授权角色信息
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
            for (Role role : user.getRoles()) {
                simpleAuthorizationInfo.addRole(role.getRoleName());
                //权限信息
                List<Permission> perms = userService.findPermsByRoleId(role.getId());
                if (!CollectionUtils.isEmpty(perms)) {
                    for (Permission perm : perms) {
                        simpleAuthorizationInfo.addStringPermission(perm.getPermissionName());
                    }
                }
            }
            return simpleAuthorizationInfo;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //根据身份信息
        String principal = (String) token.getPrincipal();
        System.out.println("调用登录认证：" + principal);
        //在工厂中获取service对象
        UserService userService = (UserService) ApplicationContextUtils.getBean("userService");
        User user = userService.findByUserName(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUserName(), user.getPassWord(),
                    ByteSource.Util.bytes(user.getSalt()),
                    this.getName());
        }
        return null;
    }
}
