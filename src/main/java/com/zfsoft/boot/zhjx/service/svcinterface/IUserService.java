package com.zfsoft.boot.zhjx.service.svcinterface;

import javax.servlet.http.HttpSession;

import com.zfsoft.boot.authz.dao.entities.UserModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileUserModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;

public interface IUserService {

	UserModel getUserByUuid(String uuid);

	//验证微信学生登录
	MobileUserModel checkWxLogin(String userName,String pass);

	//学生注册
	ResultEntity register(MobileUserModel user,HttpSession session);

	//根据用户名查询学生
	MobileUserModel selectUserByYhm(String yhm);

	MobileUserModel selectUserByOpenid(String openId);

	void updateOpenid(String openid,String yhm);

}
