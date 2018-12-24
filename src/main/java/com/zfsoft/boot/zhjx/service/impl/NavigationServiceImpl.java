package com.zfsoft.boot.zhjx.service.impl;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.zhjx.dao.daointerface.INavigationDao;
import com.zfsoft.boot.zhjx.dao.entities.NavigationModel;
import com.zfsoft.boot.zhjx.service.svcinterface.INavigationService;
import org.springframework.stereotype.Service;

@Service
public class NavigationServiceImpl extends BaseServiceImpl<NavigationModel, INavigationDao> implements INavigationService {

}
