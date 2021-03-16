package com.example.mybatis.test.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @Classname TestMD5Hash
 * @Description TODO Shiro中MD5 + Salt 实现
 * @Date 2021/3/16 2:28 下午
 * @Author z7-x
 */
public class TestMD5Hash {

    public static void main(String[] args) {
        //使用md5
        Md5Hash md5Hash = new Md5Hash("123");
        System.out.println(md5Hash.toHex());

        //使用md5+ salt
        Md5Hash md5Hash1 = new Md5Hash("123", "ps*/x-");
        System.out.println(md5Hash1.toHex());

        //使用md5 + salt + hash散列
        Md5Hash md5Hash2 = new Md5Hash("123", "ps*/x-",1024);
        System.out.println(md5Hash2.toHex());
    }
}
