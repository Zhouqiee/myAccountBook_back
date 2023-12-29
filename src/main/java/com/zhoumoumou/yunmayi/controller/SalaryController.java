package com.zhoumoumou.yunmayi.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhoumoumou.yunmayi.entity.Salary;
import com.zhoumoumou.yunmayi.service.SalaryService;
import com.zhoumoumou.yunmayi.utils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Salary)表控制层
 *
 * @author zhouqie
 * @since 2023-12-15 10:08:13
 */
@RestController
@RequestMapping("salary")
@Api(tags = "薪水")
public class SalaryController {
    /**
     * 服务对象
     */
    @Resource
    private SalaryService salaryService;

    /**
     * 分页查询所有数据
     * @param currentpage 当前页数
     * @param pagesize 页数大小
     * @param salary 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Integer currentpage,Integer pagesize, Salary salary) {
               Page<Salary> page = new Page<>(currentpage, pagesize);
        return R.ok((this.salaryService.page(page, new QueryWrapper<>(salary))).getRecords());
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return R.ok(this.salaryService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param salary 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Salary salary) {
    Boolean flag=this.salaryService.save(salary);
    if(flag==true){
        return R.ok().message("插入成功");
        }else {
        return R.fail().message("插入失败");
    }
    }

    /**
     * 修改数据
     *
     * @param salary 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Salary salary) {
    Boolean flag=this.salaryService.updateById(salary);
    if(flag==true){
        return R.ok().message("修改成功");
        }else {
        return R.fail().message("修改失败");
    }
    }

    /**
     * 删除数据
     *
     * @param idList 主键结合
     * @return 删除结果
     */
    @DeleteMapping
    public R delete(@RequestParam("idList") List<Long> idList) {
        Boolean flag=this.salaryService.removeByIds(idList);
            if(flag=true){
        return R.ok().message("修改成功");
        }else {
        return R.fail().message("修改失败");
    }
    }
}

