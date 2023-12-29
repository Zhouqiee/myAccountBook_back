package com.zhoumoumou.yunmayi.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhoumoumou.yunmayi.dao.BooksDao;
import com.zhoumoumou.yunmayi.entity.Books;
import com.zhoumoumou.yunmayi.service.BooksService;
import org.springframework.stereotype.Service;

/**
 * (Books)表服务实现类
 *
 * @author zhouqie
 * @since 2023-12-15 09:50:26
 */
@Service("booksService")
public class BooksServiceImpl extends ServiceImpl<BooksDao, Books> implements BooksService {

}

