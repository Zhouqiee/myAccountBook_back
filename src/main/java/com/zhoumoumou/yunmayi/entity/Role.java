package com.zhoumoumou.yunmayi.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Role)表实体类
 *
 * @author zhouqie
 * @since 2023-12-28 10:38:40
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    
    private Integer id;
    //角色名称
    private String name;
}

