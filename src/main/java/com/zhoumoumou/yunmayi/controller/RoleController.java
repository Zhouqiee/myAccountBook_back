package com.zhoumoumou.yunmayi.controller;



import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zhoumoumou.yunmayi.entity.Role;
import com.zhoumoumou.yunmayi.service.RoleService;
import com.zhoumoumou.yunmayi.utils.R;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.List;

/**
 * (Role)表控制层
 *
 * @author zhouqie
 * @since 2023-12-28 10:38:40
 */
@RestController
@RequestMapping("role")
@Api(tags = "角色")
public class RoleController {
    /**
     * 服务对象
     */
    @Resource
    private RoleService roleService;

    /**
     * 分页查询所有数据
     * @param currentpage 当前页数
     * @param pagesize 页数大小
     * @param role 查询实体
     * @return 所有数据
     */
    @GetMapping
    public R selectAll(Integer currentpage,Integer pagesize, Role role) {
               Page<Role> page = new Page<>(currentpage, pagesize);
        return R.ok(this.roleService.page(page, new QueryWrapper<>(role)));
    }

    /**
     * 通过主键查询单条数据
     *
     * @param id 主键
     * @return 单条数据
     */
    @GetMapping("{id}")
    public R selectOne(@PathVariable Serializable id) {
        return R.ok(this.roleService.getById(id));
    }

    /**
     * 新增数据
     *
     * @param role 实体对象
     * @return 新增结果
     */
    @PostMapping
    public R insert(@RequestBody Role role) {
    Boolean flag=this.roleService.save(role);
    if(flag==true){
        return R.ok().message("插入成功");
        }else {
        return R.fail().message("插入失败");
    }
    }

    /**
     * 修改数据
     *
     * @param role 实体对象
     * @return 修改结果
     */
    @PutMapping
    public R update(@RequestBody Role role) {
    Boolean flag=this.roleService.updateById(role);
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
        Boolean flag=this.roleService.removeByIds(idList);
            if(flag=true){
        return R.ok().message("修改成功");
        }else {
        return R.fail().message("修改失败");
    }
    }
}

