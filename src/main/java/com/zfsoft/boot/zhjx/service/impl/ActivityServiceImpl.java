package com.zfsoft.boot.zhjx.service.impl;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.zhjx.dao.daointerface.IActivityDao;
import com.zfsoft.boot.zhjx.dao.daointerface.IBookDao;
import com.zfsoft.boot.zhjx.dao.entities.ActivityModel;
import com.zfsoft.boot.zhjx.dao.entities.BookModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IActivityService;
import com.zfsoft.boot.zhjx.service.svcinterface.IBookService;
import org.springframework.stereotype.Service;

@Service
public class ActivityServiceImpl extends BaseServiceImpl<ActivityModel, IActivityDao> implements IActivityService {

}
