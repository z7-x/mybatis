package com.example.mybatis.shrio.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.stereotype.Component;

//自定义shiro缓存管理器
@Component
public class RedisCacheManager implements CacheManager {

    //参数1:认证或者是授权缓存的统一名称
    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println(cacheName);
        return new RedisCache<K,V>(cacheName);
    }
}
