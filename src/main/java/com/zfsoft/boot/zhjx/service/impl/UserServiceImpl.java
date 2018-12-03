package com.zfsoft.boot.zhjx.service.impl;

import javax.servlet.http.HttpSession;

import com.zfsoft.boot.authz.dao.entities.UserModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zfsoft.boot.zhjx.dao.daointerface.IUserDao;
import com.zfsoft.boot.zhjx.dao.entities.MobileUserModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.service.svcinterface.IUserService;
import com.zfsoft.boot.zhjx.util.Encrypt;

@Service
public class UserServiceImpl implements IUserService{

	@Autowired
	private IUserDao userdao;


	@Override
	public UserModel getUserByUuid(String uuid) {
		return userdao.getUserByUuid(uuid);
	}


	@Override
	public MobileUserModel checkWxLogin(String userName, String pass) {

		pass = Encrypt.encrypt(pass);

		return userdao.checkWxLogin(userName, pass);
	}


	@Override
	public ResultEntity register(MobileUserModel user, HttpSession session) {
		ResultEntity resultEntity = new ResultEntity();

		MobileUserModel findUser = userdao.selectUserByYhm(user.getYhm());
		if (findUser != null) {
			resultEntity.setCode(0);
			resultEntity.setStatus("用户名已存在");
		}else {
			user.setKl(Encrypt.encrypt(user.getKl()));
			user.setSfqy("1");
			user.setYhlx("student");
			user.setYhmmdj("1");
			userdao.insertUser(user);
			resultEntity.setCode(1);
			resultEntity.setStatus("注册成功");
			session.setAttribute("user", user);
			session.setAttribute("yhm", user.getYhm());
		}
		return resultEntity;
	}


	@Override
	public MobileUserModel selectUserByYhm(String yhm) {
		return userdao.selectUserByYhm(yhm);
	}


	@Override
	public MobileUserModel selectUserByOpenid(String openId) {
		return userdao.selectUserByOpenid(openId);
	}


	@Override
	public void updateOpenid(String openid, String yhm) {
		//更新当前登录的用户openid
		userdao.updateOpenid(openid, yhm);
		//将其他同openid的用户openid设置为空
		userdao.clearOpenid(openid, yhm);

	}

}
