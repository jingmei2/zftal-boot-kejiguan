package com.zfsoft.boot.zhjx.web.controller;

import java.util.ArrayList;
import java.util.Date;
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

import com.zfsoft.boot.zhjx.dao.entities.ClassInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.InstituteInfoModel;
import com.zfsoft.boot.zhjx.dao.entities.LdxxbModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileApCountModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileApModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileApService;
import com.zfsoft.boot.zhjx.util.DateUtil;

/**
 * ap分析controller
 * @author liucb
 */
@Controller
@RequestMapping("/mAp")
public class MobileApController {
	@Autowired
	protected IMobileApService mobileApService;
	
	/**
	 * 提交ap数据数据到后台
	 * @param request
	 * @param model
	 * @param mobileDeviceModel
	 * @return
	 */
	@RequestMapping(value="/submitApInfo",method=RequestMethod.POST)
	@ResponseBody
	public ResultEntity submitDeviceInfo(HttpServletRequest request, Model model,MobileApModel mobileApModel) {
		boolean flag = false;
		mobileApModel.setCreateTime(DateUtil.fSecond(new Date()));
		flag = mobileApService.insert(mobileApModel);
		ResultEntity result = null;
		if(flag) {
			result = new ResultEntity(ResultEntity.SUCCESS_CODE,"提交成功",flag);
		}else {
			result = new ResultEntity(ResultEntity.ERROR_CODE,"提交失败",flag);
		}
		return result;
	}
	
	/**
	 * 获取ap统计数据
	 * @param request
	 * @param model
	 * @param mobileDeviceModel
	 * @return
	 */
	@RequestMapping(value="/selectApCountList",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity selectApCountList(HttpServletRequest request,String daySelected,String xyId,String bjId,String yhm,MobileApModel mobileApModel) {
		ResultEntity result = null;
		Map<String,Object> params = new HashMap<String,Object>();
		//System.out.println(daySelected+"--"+yjTypeSelected);
		String startDate = DateUtil.getPastDate(0);
		String endDate = DateUtil.getPastDate(0);
		
		if(daySelected!=null&&!"".equals(daySelected)){
			if("todaySp".equals(daySelected)){
				startDate = DateUtil.getPastDate(0);
			}else if("sevenDaySp".equals(daySelected)) {
				startDate = DateUtil.getPastDate(7);
			}else if("fourteenSp".equals(daySelected)) {
				startDate = DateUtil.getPastDate(14);
			}else if("thirtySp".equals(daySelected)) {
				startDate = DateUtil.getPastDate(30);
			}else if("totalSp".equals(daySelected)) {
				startDate = "";
			}else{
				startDate = daySelected;
				endDate = daySelected;
			}
		}
		params.put("startTime", startDate);
		params.put("endTime", endDate);
		
		if(xyId!=null&&!"".equals(xyId)){
			params.put("xyId", xyId);
		}
		if(bjId!=null&&!"".equals(bjId)){
			params.put("bjId", bjId);
		}
		if(yhm!=null&&!"".equals(yhm)){
			params.put("yhm", yhm);
		}
		
		List<MobileApCountModel> list = mobileApService.selectApCountList(params);
		if(list==null) {
			list = new ArrayList<MobileApCountModel>();
		}
		
		result = new ResultEntity();
		result.setStatus(ResultEntity.SUCCESS_STATUS);
		result.setCode(1);
		result.setData(list);
		
		return result;
	}
	
	@RequestMapping("/getInstituteList")
	@ResponseBody
	public List<InstituteInfoModel> getInstituteList(){
		return mobileApService.getInstituteList();
	}
	
	@RequestMapping("/getClassList")
	@ResponseBody
	public List<ClassInfoModel> getClassList(String xyId){
		return mobileApService.getClassList(xyId);
	}
	
	@RequestMapping("/getLddmList")
	@ResponseBody
	public List<LdxxbModel> getLddmList(){
		return mobileApService.getLddmList();
	}
}
