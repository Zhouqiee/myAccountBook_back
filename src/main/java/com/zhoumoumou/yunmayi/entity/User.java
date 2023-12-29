package com.zhoumoumou.yunmayi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (User)表实体类
 *
 * @author zhouqie
 * @since 2023-12-15 09:50:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ymy_user")
public class User {
    @TableId(type = IdType.AUTO)
    private Integer id;
    
    private String name;
    
    private String username;
    
    private String password;
    
    private Integer gender;

    private String phone;
}

