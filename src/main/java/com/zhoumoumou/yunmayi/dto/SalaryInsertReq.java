package com.zhoumoumou.yunmayi.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.zhoumoumou.yunmayi.entity.Salary;

/**
 * (Salary)表插入dto类
 *
 * @author zhouqie
 * @since 2023-12-15 10:01:59
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SalaryInsertReq {
    //薪水
    private Integer salary;
    //用户id
    private Integer uid;

    public Salary getSalary() {
        Salary salary = new Salary();
        salary.setSalary(this.salary);
        salary.setUid(uid);
        return salary;
    }
}
