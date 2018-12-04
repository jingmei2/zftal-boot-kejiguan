package com.zfsoft.boot.zhjx.service.impl;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.zhjx.dao.daointerface.IBookDao;
import com.zfsoft.boot.zhjx.dao.entities.BookModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IBookService;
import com.zfsoft.boot.zhjx.service.svcinterface.IMenuService;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImpl extends BaseServiceImpl<BookModel, IBookDao> implements IBookService {

}
