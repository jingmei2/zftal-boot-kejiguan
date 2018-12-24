package com.zfsoft.boot.zhjx.service.impl;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.zhjx.dao.daointerface.IVenueDao;
import com.zfsoft.boot.zhjx.dao.entities.VenueModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IVenueService;
import org.springframework.stereotype.Service;

@Service
public class VenueServiceImpl extends BaseServiceImpl<VenueModel, IVenueDao> implements IVenueService {

}
