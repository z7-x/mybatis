package com.example.mybatis.test.log;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @Classname ste
 * @Description TODO
 * @Date 2021/3/23 11:40 上午
 * @Author z7-x
 */
public class TestLogging {

    private Logger logger = LoggerFactory.getLogger(TestLogging.class);

    @Test
    public void contextLoads() {
        //日志的级别；
        //由低到高   trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        logger.trace("这是trace日志...");
        logger.debug("这是debug日志...");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("这是info日志...");
        logger.warn("这是warn日志...");
        logger.error("这是error日志...");
    }
}
