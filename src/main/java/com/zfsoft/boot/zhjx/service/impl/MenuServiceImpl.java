package com.zfsoft.boot.zhjx.service.impl;

import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.zhjx.dao.daointerface.IMenuDao;
import com.zfsoft.boot.zhjx.dao.entities.LeftMenuModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IMenuService;

@Service
public class MenuServiceImpl extends BaseServiceImpl<LeftMenuModel, IMenuDao> implements IMenuService{

}
