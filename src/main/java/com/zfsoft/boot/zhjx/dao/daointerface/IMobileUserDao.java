package com.zfsoft.boot.zhjx.dao.daointerface;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.zfsoft.api.dao.BaseDao;
import com.zfsoft.boot.zhjx.dao.entities.ClassInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileUserModel;

@Mapper
public interface IMobileUserDao  extends BaseDao<MobileUserModel>{

	//用户名和密码查询用户
	MobileUserModel checkUser(MobileUserModel mobileUserModel);
	
	//更新apptoken
	int updateApptoken(MobileUserModel mobileUserModel);
	
	//根据用户名获取角色代码
	String getJsdmByYhm(Map<String,Object> map);
	
	//获取班级信息列表
	List<ClassInfoModel> getClassInfoList(Map<String,Object> map);
	
	//获取用户信息列表
	List<InstituteInfoModel> getInstituteInfoList();

	void updateByOpenid(@Param("userName")String userName,@Param("openid") String openid);
}
