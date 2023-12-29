package com.zhoumoumou.yunmayi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumoumou.yunmayi.dao.SalaryDao;
import com.zhoumoumou.yunmayi.entity.Salary;
import com.zhoumoumou.yunmayi.service.SalaryService;
import org.springframework.stereotype.Service;

/**
 * (Salary)表服务实现类
 *
 * @author zhouqie
 * @since 2023-12-15 09:50:26
 */
@Service("salaryService")
public class SalaryServiceImpl extends ServiceImpl<SalaryDao, Salary> implements SalaryService {

}

