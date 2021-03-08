package com.example.mybatis.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Classname AspectServiceImpl
 * @Description TODO
 * @Date 2021/3/8 3:31 下午
 * @Author z7-x
 */
@Service
@Transactional
public class AspectServiceImpl {

    public int sum(int a, int b) {

        int sum = a + b;

        return sum;
    }
}
