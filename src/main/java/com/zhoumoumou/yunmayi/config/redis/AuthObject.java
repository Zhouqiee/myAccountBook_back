package com.zhoumoumou.yunmayi.config.redis;

import com.zhoumoumou.yunmayi.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @Description:
 * @Author: zhouqie
 * @date 2023/12/29
 */
@Data
@NoArgsConstructor
public class AuthObject extends User {
List<String> roles;

    public AuthObject(User user,List<String> roles){
        addUser(user);
        this.roles=roles;
    }

    public AuthObject(User user){
        addUser(user);
        this.roles=null;
    }


    public void addUser(User user){
    this.setId(user.getId());
    this.setName(user.getName());
    this.setUsername(user.getUsername());
    this.setPassword(user.getPassword());
    this.setGender(user.getGender());
    this.setPhone(user.getPhone());
}
}
