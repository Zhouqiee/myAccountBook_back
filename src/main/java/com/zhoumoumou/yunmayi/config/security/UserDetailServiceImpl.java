package com.zhoumoumou.yunmayi.config.security;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.zhoumoumou.yunmayi.config.redis.AuthObject;
import com.zhoumoumou.yunmayi.entity.LoginUser;
import com.zhoumoumou.yunmayi.entity.Role;
import com.zhoumoumou.yunmayi.entity.User;
import com.zhoumoumou.yunmayi.exception.GlobalException;
import com.zhoumoumou.yunmayi.service.RoleService;
import com.zhoumoumou.yunmayi.service.UserService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @Description:
 * @Author: zhouqie
 * @date 2023/12/28
 */
@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Resource
    PasswordEncoder passwordEncoder;

    @Resource
    RoleService roleService;

    @Resource
    UserService userService;

    @Resource
    RedisTemplate redisTemplate;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username",username);
//       在数据库中找到该账号
        User one=null;
        try {
            one = userService.getOne(queryWrapper);
        }catch (Exception e){
            throw new GlobalException("该账数据库中不止存在1次");
        }
        if (Objects.isNull(one)){
            throw new GlobalException("该账号不存在");
        }
//        通过用户id查询出该用户的用户角色
        List<Role> roleById = roleService.getRoleById(one.getId());
        List<String> collect = roleById.stream().map(Role::getName).collect(Collectors.toList());
        AuthObject authObject = new AuthObject(one,collect);
        redisTemplate.opsForValue().set("user:"+one.getId(),authObject);
        return new LoginUser(username,one.getPassword(),collect);
    }
}
