package com.zhoumoumou.yunmayi.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhoumoumou.yunmayi.entity.Role;

import java.util.List;

/**
 * (Role)表服务接口
 *
 * @author zhouqie
 * @since 2023-12-28 10:38:40
 */
public interface RoleService extends IService<Role> {
    public List<Role> getRoleById(int userId);

}

