package com.example.mybatis.controller;

import com.example.mybatis.service.impl.AspectServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Classname AspectController
 * @Description TODO
 * @Date 2021/3/8 3:31 下午
 * @Author z7-x
 */
@RestController
@RequestMapping("/aspect")
@Api(description = "切面类")
public class AspectController {
    @Autowired
    private AspectServiceImpl aspectService;

    @ApiOperation(value = "测试切面", notes = "测试切面")
    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public int contextLoads(@RequestParam int param1, @RequestParam int param2) {
        return aspectService.sum(param1, param2);
    }
}
