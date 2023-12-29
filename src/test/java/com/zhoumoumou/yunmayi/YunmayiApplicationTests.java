package com.zhoumoumou.yunmayi;

import com.zhoumoumou.yunmayi.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.annotation.Resource;

@SpringBootTest
class YunmayiApplicationTests {
    @Resource
    PasswordEncoder passwordEncoder;
    @Test
    void contextLoads() throws Exception {
        Claims claims = JwtUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIxNmYyMjNhNzI1MjM0NjZiYjJkNDAxNjZkYmNjOWMxYSIsInN1YiI6Inpob3VxaWUiLCJpc3MiOiJ0d3dsIiwiaWF0IjoxNzAzNzUwMTI2LCJleHAiOjE3MDQzNTQ5MjZ9.HqV23H3BPXIuAJrmGctDupSXxigavf9NmJit7tEZabQ");
        String subject = claims.getSubject();
        System.out.println(subject);
    }

}
