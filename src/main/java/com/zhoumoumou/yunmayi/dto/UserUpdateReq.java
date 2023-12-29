package com.zhoumoumou.yunmayi.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.zhoumoumou.yunmayi.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

/**
 * @Description:
 * @Author: zhouqie
 * @date 2023/12/26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ymy_user")
public class UserUpdateReq {
    private Integer id;
    @NotBlank(message = "名称不能为空")
    private String name;
    @NotBlank(message = "用户名不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,20}$",message = "密码必须由中文和英文组成,并且长度必须大于8")
    private String password;
    @NotNull(message = "性别不能为空")
    @Min(value = 0,message = "性别只能是0与1之间的数字")
    @Max(value=1,message = "性别只能是0与1之间的数字")
    private Integer gender;
    @Pattern(regexp = "^1[3456789]\\d{9}$",message = "电话号码不符合格式")
    private String phone;

    public User toUser(){
        User user = new User();
        user.setId(id);
        user.setName(name);
        user.setUsername(username);
        user.setPassword(password);
        user.setGender(gender);
        user.setPhone(phone);
        return user;
    }
}

