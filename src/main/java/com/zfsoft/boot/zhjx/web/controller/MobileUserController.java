package com.zfsoft.boot.zhjx.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.basicutils.StringUtils;
import com.zfsoft.boot.zhjx.dao.entities.ClassInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileUserModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileUserService;
import com.zfsoft.boot.zhjx.util.Encrypt;
import com.zfsoft.boot.zhjx.util.MD5Util;

@Controller
@RequestMapping("/mUser")
public class MobileUserController {
	@Autowired
	private IMobileUserService mobileUserService;

	/**
	 * 移动端登录接口
	 * @param request
	 * @param model
	 * @param mobileUserModel
	 * @return
	 */
	@RequestMapping(value="/login",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity login(HttpServletRequest request, Model model,MobileUserModel mobileUserModel) {
		mobileUserModel.setKl(Encrypt.encrypt(mobileUserModel.getKl()));
		MobileUserModel checkedUser = mobileUserService.checkUser(mobileUserModel);
		ResultEntity resultEntity = null;
		if(!StringUtils.isEmpty(mobileUserModel.getYhm())&&!StringUtils.isEmpty(mobileUserModel.getKl())){
			if(checkedUser!=null) {
				//更新apptoken
				String newApptoken = null;
				try {
					newApptoken = MD5Util.md5Encode(checkedUser.getYhm()+System.currentTimeMillis());
				} catch (Exception e) {
					e.printStackTrace();
				}
		        mobileUserModel.setApptoken(newApptoken);
		        mobileUserService.updateApptoken(mobileUserModel);
		        
		        //刷新用户信息，因为apptoken已经改变
		        checkedUser = mobileUserService.checkUser(mobileUserModel);
				resultEntity = new ResultEntity(1,ResultEntity.SUCCESS_STATUS,checkedUser);
			}else {
				resultEntity = new ResultEntity(0,"用户名或密码错误",checkedUser);
			}
		}else {
			resultEntity = new ResultEntity(0,"参数错误","参数错误");
		}
		return resultEntity;
	}
	
	
	//获取班级信息列表
	@RequestMapping(value="/getClassInfoList",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity getClassInfoList(HttpServletRequest request,String instituteId) {
		Map<String,Object> params = new HashMap<String,Object>();
		params.put("instituteId", instituteId);
		List<ClassInfoModel> list = mobileUserService.getClassInfoList(params);
		
		if(list==null) {
			list = new ArrayList<ClassInfoModel>();
		}
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", list);
		return resultEntity;
	}
	
	//获取学院信息列表
	@RequestMapping(value="/getInstituteInfoList",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity getInstituteInfoList(HttpServletRequest request) {
		List<InstituteInfoModel> list = mobileUserService.getInstituteInfoList();
		if(list==null) {
			list = new ArrayList<InstituteInfoModel>();
		}
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", list);
		return resultEntity;
	}
}
