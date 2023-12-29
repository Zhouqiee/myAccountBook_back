package com.zhoumoumou.yunmayi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumoumou.yunmayi.dao.RoleDao;
import com.zhoumoumou.yunmayi.entity.Role;
import com.zhoumoumou.yunmayi.service.RoleService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * (Role)表服务实现类
 *
 * @author zhouqie
 * @since 2023-12-28 10:38:40
 */
@Service("roleService")
public class RoleServiceImpl extends ServiceImpl<RoleDao, Role> implements RoleService {
    @Resource
    RoleDao roleDao;
    @Override
    public List<Role> getRoleById(int userId) {
        List<Role> roleSByid = roleDao.getRoleSByid(userId);
        return roleSByid;
    }
}

