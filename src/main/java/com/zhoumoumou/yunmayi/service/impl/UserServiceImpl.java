package com.zhoumoumou.yunmayi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumoumou.yunmayi.dao.UserDao;
import com.zhoumoumou.yunmayi.entity.User;
import com.zhoumoumou.yunmayi.service.UserService;
import org.springframework.stereotype.Service;

/**
 * (User)表服务实现类
 *
 * @author zhouqie
 * @since 2023-12-15 09:50:26
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements UserService {

}

