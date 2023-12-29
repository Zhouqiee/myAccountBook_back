package com.zhoumoumou.yunmayi.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zhoumoumou.yunmayi.entity.Role;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * (Role)表数据库访问层
 *
 * @author zhouqie
 * @since 2023-12-28 10:38:40
 */
public interface RoleDao extends BaseMapper<Role> {
    @Select("select r.id,r.name from ymy_u_r as ur left join ymy_role r on ur.rid=r.id;")
    public List<Role> getRoleSByid(int id);
}

