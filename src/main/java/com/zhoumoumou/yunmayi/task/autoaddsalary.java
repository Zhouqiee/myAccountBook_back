package com.zhoumoumou.yunmayi.task;

import com.zhoumoumou.yunmayi.entity.Salary;
import com.zhoumoumou.yunmayi.service.SalaryService;
import lombok.Data;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description:
 * @Author: zhouqie
 * @date 2023/12/25
 */
@Component
@Data
public class autoaddsalary {
    @Resource
    SalaryService salaryService;
    @Scheduled(cron = "0 0 0 */1 * ?")
    public void zhoumoumouaddsalary() {
        System.out.println("自动加工资");
        Salary salary = new Salary();
        salary.setUid(1);
        salary.setSalary(2000);
        salary.setDate(new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date()));
        salaryService.save(salary);
    }
}
