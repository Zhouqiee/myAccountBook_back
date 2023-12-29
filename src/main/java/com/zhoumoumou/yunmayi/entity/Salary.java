package com.zhoumoumou.yunmayi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * (Salary)表实体类
 *
 * @author zhouqie
 * @since 2023-12-15 09:50:26
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ymy_salary")
public class Salary {
    @TableId(type = IdType.AUTO)
    private Integer id;
    //薪水
    private Integer salary;
    //用户id
    private Integer uid;
    //时间
    private String date;

}

