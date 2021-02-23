//package com.example.mybatis.config;
//
//import org.mybatis.spring.mapper.MapperScannerConfigurer;
//import org.springframework.boot.autoconfigure.AutoConfigureAfter;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @Classname MyBatisMapperScannerConfig
// * @Description TODO
// * @Date 2021/2/5 11:44 上午
// * @Author z7-x
// */
//@Configuration
//@AutoConfigureAfter(DataSourceConfig.class)
//public class MyBatisMapperScannerConfig {
//    @Bean
//    public MapperScannerConfigurer mapperScannerConfigurer() throws Exception{
//        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
//        //com.example.mybatis.mapper.* 需要换成自己项目中的mapper目录
//        mapperScannerConfigurer.setBasePackage("com.example.mybatis.mapper.*;com.gitee.sunchenbin.mybatis.actable.dao.*");
//        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
//        return mapperScannerConfigurer;
//    }
//}
