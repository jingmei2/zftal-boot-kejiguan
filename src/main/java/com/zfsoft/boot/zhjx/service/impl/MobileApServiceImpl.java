package com.zfsoft.boot.zhjx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.zhjx.dao.daointerface.IMobileApDao;
import com.zfsoft.boot.zhjx.dao.entities.ClassInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.LdxxbModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileApCountModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileApModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileApService;

@Service
public class MobileApServiceImpl extends BaseServiceImpl<MobileApModel, IMobileApDao> implements IMobileApService{
	@Autowired
	protected IMobileApDao mobileApDao;
	
	public List<MobileApCountModel> selectApCountList(Map<String,Object> params){
		return mobileApDao.selectApCountList(params);
	}
	
	public List<InstituteInfoModel> getInstituteList(){
		return mobileApDao.getInstituteList();
	}
	
	public List<ClassInfoModel> getClassList(String xyId){
		return mobileApDao.getClassList(xyId);
	}

	@Override
	public List<LdxxbModel> getLddmList() {
		return mobileApDao.getLddmList();
	}
}
