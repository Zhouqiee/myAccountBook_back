package com.zhoumoumou.yunmayi.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (Books)表实体类
 *
 * @author zhouqie
 * @since 2023-12-15 09:50:25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("ymy_books")
public class Books {
    @TableId(type = IdType.AUTO)
    private Integer id;

    private Integer uid;

    private Date date;

    private Integer salary;

    private Integer times;
}

