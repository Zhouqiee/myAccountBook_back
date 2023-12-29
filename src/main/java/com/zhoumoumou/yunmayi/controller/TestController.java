package com.zhoumoumou.yunmayi.controller;

import com.zhoumoumou.yunmayi.entity.Test;
import io.swagger.annotations.Api;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @Description:
 * @Author: zhouqie
 * @date 2023/12/19
 */
@RestController
@RequestMapping("/test")
@Api(tags = "测试")
public class TestController {
    @Resource
    public RedisTemplate redisTemplate;


    @GetMapping("/test1")
    public Test test1(){
        redisTemplate.opsForValue().set("xixi","haha");
        Test test = new Test();
        test.setTitle("信息传输成功");
        test.setDate("2023/12/19 11:42:00");
        return test;
    }
}
