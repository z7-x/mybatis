package com.example.mybatis.config.shrio;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;

import java.util.HashMap;
import java.util.Map;

/**
 * 用来整合shiro框架相关的配置类
 */
@Configuration
public class ShiroConfig {
    //1.创建shiroFilter  //负责拦截所有请求
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(DefaultWebSecurityManager defaultWebSecurityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();

        //给filter设置安全管理器
        shiroFilterFactoryBean.setSecurityManager(defaultWebSecurityManager);

        //配置系统公共资源 和 受限资源
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("/user/login", "anon");//anon 设置为公共资源  放行资源放在下面
//        map.put("/user/register", "anon");//anonwaq        设置为公共资源  放行资源放在下面
//        map.put("/register.jsp", "anon");//anon 设置为公共资源  放行资源放在下面
//        map.put("/user/getImage", "anon");
//        map.put("/**", "authc");//authc 请求这个资源需要认证和授权

        //默认认证界面路径
//        shiroFilterFactoryBean.setLoginUrl("/login.jsp");
//        shiroFilterFactoryBean.setFilterChainDefinitionMap(map);
        return shiroFilterFactoryBean;
    }

    //2.创建安全管理器
    @Bean
    public DefaultWebSecurityManager getDefaultWebSecurityManager(Realm realm) {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        //给安全管理器设置
        defaultWebSecurityManager.setRealm(realm);
        return defaultWebSecurityManager;
    }

    //3.创建自定义realm
    @Bean
    public Realm getRealm() {
        UserRealm userRealm = new UserRealm();
        //修改凭证校验匹配器
        HashedCredentialsMatcher credentialsMatcher = new HashedCredentialsMatcher();
        //设置加密算法为md5
        credentialsMatcher.setHashAlgorithmName("MD5");
        //设置散列次数
        credentialsMatcher.setHashIterations(1024);

        //开启缓存管理：EhCache
        userRealm.setCacheManager(new EhCacheManager());
        userRealm.setCachingEnabled(true);//开启全局缓存
        userRealm.setAuthenticationCachingEnabled(true);//认证认证缓存
        userRealm.setAuthenticationCacheName("authenticationCache");//给认证缓存起一个名字
        userRealm.setAuthorizationCachingEnabled(true);//开启授权缓存
        userRealm.setAuthorizationCacheName("authorizationCache");

        userRealm.setCredentialsMatcher(credentialsMatcher);
        return userRealm;
    }
}