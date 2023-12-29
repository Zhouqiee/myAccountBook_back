package com.zhoumoumou.yunmayi.dto;


import com.zhoumoumou.yunmayi.entity.Books;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * (Books)表插入dto类
 *
 * @author zhouqie
 * @since 2023-12-15 10:01:58
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BooksInsertReq {

    private Integer uid;

    private Date date;

    private Integer salary;

    private Integer times;

    public Books getBooks() {
        Books books = new Books();
        books.setUid(uid);
        books.setDate(date);
        books.setSalary(salary);
        books.setTimes(times);
        return books;
    }
}
