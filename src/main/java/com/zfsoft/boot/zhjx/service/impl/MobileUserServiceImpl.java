package com.zfsoft.boot.zhjx.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.api.service.BaseServiceImpl;
import com.zfsoft.boot.zhjx.dao.daointerface.IDatatableDao;
import com.zfsoft.boot.zhjx.dao.daointerface.IMobileUserDao;
import com.zfsoft.boot.zhjx.dao.entities.ClassInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileUserModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileUserService;

@Service
public class MobileUserServiceImpl  extends BaseServiceImpl<MobileUserModel, IMobileUserDao> implements IMobileUserService{
	
	@Autowired
	protected IMobileUserDao mobileUserDao;
	
	//用户名和密码查询用户
	public MobileUserModel checkUser(MobileUserModel mobileUserModel){
		return mobileUserDao.checkUser(mobileUserModel);
	}
	
	//更新apptoken
	public int updateApptoken(MobileUserModel mobileUserModel){
		return mobileUserDao.updateApptoken(mobileUserModel);
	}
	
	//根据用户名获取角色代码
	public String getJsdmByYhm(Map<String,Object> map){
		return mobileUserDao.getJsdmByYhm(map);
	}
	
	//获取班级信息列表
	public List<ClassInfoModel> getClassInfoList(Map<String,Object> map){
		return mobileUserDao.getClassInfoList(map);
	}
		
	//获取用户信息列表
	public List<InstituteInfoModel> getInstituteInfoList(){
		return mobileUserDao.getInstituteInfoList();
	}

}
