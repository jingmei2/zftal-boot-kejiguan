package com.zfsoft.boot.zhjx.web.controller;

import java.util.ArrayList;
import java.util.Collections;
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

import com.zfsoft.api.web.session.User;
import com.zfsoft.boot.zhjx.dao.entities.MobileDeviceModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileUserService;
import com.zfsoft.boot.zhjx.service.svcinterface.IWarningService;
import com.zfsoft.boot.zhjx.util.BigDecimalUtil;
import com.zfsoft.boot.zhjx.util.DateUtil;


/**
 * 预警controller
 * @author liucb
 */
@Controller
@RequestMapping("/warning")
public class WarningController {
	
	@Autowired
	private IWarningService warningService;
	
	@Autowired
	private IMobileUserService mobileUserService;
	
	
	/**
	 * 学生微信端获取预警记录
	 * @param request
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity getList(HttpServletRequest request,String username) {
		Map<String,Object> params = new HashMap<String,Object>();
		
		Map<String,Object> jsdmParams = new HashMap<String,Object>(); 
		jsdmParams.put("yhm",username);
		String jsdm = mobileUserService.getJsdmByYhm(jsdmParams);
		
        params.put("yhm",username);
        params.put("jsdm",jsdm);
		List<WarningModel> list = warningService.getWarningList(params);
		
		if(list==null) {
			list = new ArrayList<WarningModel>();
		}
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", list);
		return resultEntity;
	}
	
	@RequestMapping("/getData")
	@ResponseBody
	public Map<String,Object> getData(String daySelected,String yjTypeSelected,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		//System.out.println(daySelected+"--"+yjTypeSelected);
		String yjType = "0";
		String startDate = DateUtil.getPastDate(0);
		String endDate = DateUtil.getPastDate(0);
		
		if(yjTypeSelected!=null&&!"".equals(yjTypeSelected)) {
			if("wxyj".equals(yjTypeSelected)){
				yjType = "0";
			}else if("sswgyj".equals(yjTypeSelected)){
				yjType = "1";
			}else if("jqyj".equals(yjTypeSelected)){
				yjType = "2";
			}
		}
		
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
		params.put("yjType", yjType);
		params.put("startDate", startDate);
		params.put("endDate", endDate);
		

		User user = (User)request.getSession().getAttribute("user");
		Map<String,Object> jsdmParams = new HashMap<String,Object>(); 
		jsdmParams.put("yhm",user.getYhm());
		String jsdm = mobileUserService.getJsdmByYhm(jsdmParams);
        params.put("yhm",user.getYhm());
        params.put("jsdm",jsdm);
		
		List<WarningModel> warningModelList = warningService.getWarningList(params);
		
		result.put("warningModelList", warningModelList);
		return result;
	}
	
	/**
	 * 首页预警统计
	 * @return
	 */
	@RequestMapping("/getIndexPageWarningCount")
	@ResponseBody
	public Map<String,Object> getIndexPageWarningCount(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> paramsDay = new HashMap<String,Object>();
		Map<String,Object> paramsMonth = new HashMap<String,Object>();
		Map<String,Object> paramsYear = new HashMap<String,Object>();
		String crtDate = DateUtil.getPastDate(0);
		String lastDate = DateUtil.getPastDate(1);
		String crtMonth = DateUtil.getPastMonth(0);
		String lastMonth = DateUtil.getPastMonth(1);
		String crtYear = DateUtil.getPastYear(0);
		String lastYear = DateUtil.getPastYear(1);
		
		User user = (User)request.getSession().getAttribute("user");
		Map<String,Object> jsdmParams = new HashMap<String,Object>(); 
		jsdmParams.put("yhm",user.getYhm());
		String jsdm = mobileUserService.getJsdmByYhm(jsdmParams);
		paramsDay.put("jsdm",jsdm);
		paramsDay.put("yhm",user.getYhm());
		
		paramsMonth.put("jsdm",jsdm);
		paramsMonth.put("yhm",user.getYhm());
		
		paramsYear.put("jsdm",jsdm);
		paramsYear.put("yhm",user.getYhm());
		
		//统计晚归预警,日增长率,月增长率,年增长率
		paramsDay.put("yjType", "1");
		paramsMonth.put("yjType", "1");
		paramsYear.put("yjType", "1");
		
		int totalCountWg = warningService.getSumByYjType(paramsDay);
		result.put("totalCountWg", totalCountWg);
		
		paramsDay.put("dateStr",crtDate);
		int crtDateCountWg = warningService.getWarningDayCount(paramsDay);
		
		paramsDay.put("dateStr",lastDate);
		int lastDateCountWg = warningService.getWarningDayCount(paramsDay);
		
		if(crtDateCountWg==0||lastDateCountWg==0){
			result.put("wgDateZzl", 0.00);
		}else {
			double zzl = ((double)crtDateCountWg-lastDateCountWg)/lastDateCountWg;
			result.put("wgDateZzl", zzl);
		}
		
		paramsMonth.put("monthStr", crtMonth);
		int crtMonthCountWg = warningService.getWarningMonthCount(paramsMonth);
		
		paramsMonth.put("monthStr", lastMonth);
		int lastMonthCountWg = warningService.getWarningMonthCount(paramsMonth);
		
		if(crtMonthCountWg==0||lastMonthCountWg==0){
			result.put("wgMonthZzl", 0.00);
		}else {
			double zzl = ((double)crtMonthCountWg-lastMonthCountWg)/lastMonthCountWg;
			result.put("wgMonthZzl", zzl);
		}
		
		paramsYear.put("yearStr", crtYear);
		int crtYearCountWg = warningService.getWarningYearCount(paramsYear);
		
		paramsYear.put("yearStr", lastYear);
		int lastYearCountWg = warningService.getWarningYearCount(paramsYear);
		
		if(crtYearCountWg==0||lastYearCountWg==0){
			result.put("wgYearZzl", 0.00);
		}else {
			double zzl = ((double)crtYearCountWg-lastYearCountWg)/lastYearCountWg;
			result.put("wgYearZzl", zzl);
		}
		
		//统计消费预警,日增长率,月增长率,年增长率
		paramsDay.put("yjType", "3");
		paramsMonth.put("yjType", "3");
		paramsYear.put("yjType", "3");
		
		int totalCountXf = warningService.getSumByYjType(paramsDay);
		result.put("totalCountXf", totalCountXf);
		
		paramsDay.put("dateStr",crtDate);
		int crtDateCountXf = warningService.getWarningDayCount(paramsDay);
		
		paramsDay.put("dateStr",lastDate);
		int lastDateCountXf = warningService.getWarningDayCount(paramsDay);
		
		
		if(crtDateCountXf==0||lastDateCountXf==0){
			result.put("xfDateZzl",0.00);
		}else {
			double zzl = ((double)crtDateCountXf-lastDateCountXf)/lastDateCountXf;
			result.put("xfDateZzl", zzl);
		}
		
		paramsMonth.put("monthStr", crtMonth);
		int crtMonthCountXf = warningService.getWarningMonthCount(paramsMonth);
		
		paramsMonth.put("monthStr", lastMonth);
		int lastMonthCountXf = warningService.getWarningMonthCount(paramsMonth);
		
		if(crtMonthCountXf==0||lastMonthCountXf==0){
			result.put("xfMonthZzl",0.00);
		}else {
			double zzl = ((double)crtMonthCountXf-lastMonthCountXf)/lastMonthCountXf;
			result.put("xfMonthZzl", zzl);
		}
		
		paramsYear.put("yearStr", crtYear);
		int crtYearCountXf = warningService.getWarningYearCount(paramsYear);
		
		paramsYear.put("yearStr", lastYear);
		int lastYearCountXf = warningService.getWarningYearCount(paramsYear);
		
		if(crtYearCountXf==0||lastYearCountXf==0){
			result.put("xfYearZzl",0.00);
		}else {
			double zzl = ((double)crtYearCountXf-lastYearCountXf)/lastYearCountXf;
			result.put("xfYearZzl", zzl);
		}
		
		//统计成绩预警,日增长率,月增长率,年增长率
		paramsDay.put("yjType", "4");
		paramsMonth.put("yjType", "4");
		paramsYear.put("yjType", "4");
		
		int totalCountCj = warningService.getSumByYjType(paramsDay);
		result.put("totalCountCj", totalCountCj);
		
		paramsDay.put("dateStr",crtDate);
		int crtDateCountCj = warningService.getWarningDayCount(paramsDay);
		
		paramsDay.put("dateStr",lastDate);
		int lastDateCountCj = warningService.getWarningDayCount(paramsDay);
		
		if(crtDateCountCj==0||lastDateCountCj==0){
			result.put("cjDateZzl",0.00);
		}else {
			double zzl = ((double)crtDateCountCj-lastDateCountCj)/lastDateCountCj;
			result.put("cjDateZzl", zzl);
		}
		
		paramsMonth.put("monthStr", crtMonth);
		int crtMonthCountCj = warningService.getWarningMonthCount(paramsMonth);
		
		paramsMonth.put("monthStr", lastMonth);
		int lastMonthCountCj = warningService.getWarningMonthCount(paramsMonth);
		
		if(crtMonthCountCj==0||lastMonthCountCj==0){
			result.put("cjMonthZzl",0.00);
		}else {
			double zzl = ((double)crtMonthCountCj-lastMonthCountCj)/lastMonthCountCj;
			result.put("cjMonthZzl", zzl);
		}
		
		paramsYear.put("yearStr", crtYear);
		int crtYearCountCj = warningService.getWarningYearCount(paramsYear);
		
		paramsYear.put("yearStr", lastYear);
		int lastYearCountCj = warningService.getWarningYearCount(paramsYear);
		
		if(crtYearCountCj==0||lastYearCountCj==0){
			result.put("cjYearZzl", 0.00);
		}else {
			double zzl = ((double)crtYearCountCj-lastYearCountCj)/lastYearCountCj;
			result.put("cjYearZzl", zzl);
		}
		
		//统计借阅预警,日增长率,月增长率,年增长率
		paramsDay.put("yjType", "5");
		paramsMonth.put("yjType", "5");
		paramsYear.put("yjType", "5");
		
		int totalCountJy = warningService.getSumByYjType(paramsDay);
		result.put("totalCountJy", totalCountJy);
		
		paramsDay.put("dateStr",crtDate);
		int crtDateCountJy = warningService.getWarningDayCount(paramsDay);
		
		paramsDay.put("dateStr",lastDate);
		int lastDateCountJy = warningService.getWarningDayCount(paramsDay);
		if(crtDateCountJy==0||lastDateCountJy==0){
			result.put("jyDateZzl", 0.00);
		}else {
			double zzl = ((double)crtDateCountJy-lastDateCountJy)/lastDateCountJy;
			result.put("jyDateZzl", zzl);
		}
		
		paramsMonth.put("monthStr", crtMonth);
		int crtMonthCountJy = warningService.getWarningMonthCount(paramsMonth);
		
		paramsMonth.put("monthStr", lastMonth);
		int lastMonthCountJy = warningService.getWarningMonthCount(paramsMonth);
		
		if(crtMonthCountJy==0||lastMonthCountJy==0){
			result.put("jyMonthZzl", 0.00);
		}else {
			double zzl = ((double)crtMonthCountJy-lastMonthCountJy)/lastMonthCountJy;
			result.put("jyMonthZzl", zzl);
		}
		
		paramsYear.put("yearStr", crtYear);
		int crtYearCountJy = warningService.getWarningYearCount(paramsYear);
		
		paramsYear.put("yearStr", lastYear);
		int lastYearCountJy = warningService.getWarningYearCount(paramsYear);
		
		if(crtYearCountJy==0||lastYearCountJy==0){
			result.put("jyYearZzl",0.00);
		}else {
			double zzl = ((double)crtYearCountJy-lastYearCountJy)/lastYearCountJy;
			result.put("jyYearZzl", zzl);
		}
		return result;
	}
	
	/**
	 * 首页统计图
	 * @return
	 */
	@RequestMapping("/getIndexQst")
	@ResponseBody
	public Map<String,Object> getIndexQst(HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		
		User user = (User)request.getSession().getAttribute("user");
		Map<String,Object> jsdmParams = new HashMap<String,Object>(); 
		jsdmParams.put("yhm",user.getYhm());
		String jsdm = mobileUserService.getJsdmByYhm(jsdmParams);
        params.put("yhm",user.getYhm());
        params.put("jsdm",jsdm);
		
		List<String>  dateStrList = new ArrayList<String>();
		List<Integer> xfyjList = new ArrayList<Integer>();
		List<Integer> wgyjList = new ArrayList<Integer>();
		List<Integer> cjyjList = new ArrayList<Integer>();
		List<Integer> jyyjList = new ArrayList<Integer>();
		for (int i = 0; i < 8; i++) {
			String dateStr = DateUtil.getPastDate(i);
			
			dateStrList.add(dateStr.substring(5,dateStr.length()));
			
			params.put("dateStr", dateStr);
			params.put("yjType", "3");//消费预警
			xfyjList.add(warningService.getWarningDayCount(params));
			
			params.put("yjType", "1");//晚归预警
			wgyjList.add(warningService.getWarningDayCount(params));
			
			params.put("yjType", "4");//成绩预警
			cjyjList.add(warningService.getWarningDayCount(params));
			
			params.put("yjType", "5");//借阅预警
			jyyjList.add(warningService.getWarningDayCount(params));
		}
		
		Collections.reverse(dateStrList);
		dateStrList.remove(0);
		result.put("dateStrList",dateStrList);
		
		Collections.reverse(xfyjList);
		Collections.reverse(wgyjList);
		Collections.reverse(cjyjList);
		Collections.reverse(jyyjList);
		
		List<Double> xfyjZzlList = new ArrayList<Double>();
		List<Double> wgyjZzlList = new ArrayList<Double>();
		List<Double> cjyjZzlList = new ArrayList<Double>();
		List<Double> jyyjZzlList = new ArrayList<Double>();
		
		for (int i = 1; i < xfyjList.size(); i++) {
			if(xfyjList.get(i)==0||xfyjList.get(i-1)==0){
				xfyjZzlList.add(0.00);
			}else {
				double zzl = ((double)xfyjList.get(i)-xfyjList.get(i-1))/xfyjList.get(i-1);
				xfyjZzlList.add(BigDecimalUtil.getFormatDoubleVal(zzl));
			}
		}
		for (int i = 1; i < wgyjList.size(); i++) {
			if(wgyjList.get(i)==0||wgyjList.get(i-1)==0){
				wgyjZzlList.add(0.00);
			}else {
				double zzl = ((double)wgyjList.get(i)-wgyjList.get(i-1))/wgyjList.get(i-1);
				wgyjZzlList.add(BigDecimalUtil.getFormatDoubleVal(zzl));
			}
		}
		for (int i = 1; i < cjyjList.size(); i++) {
			if(cjyjList.get(i)==0||cjyjList.get(i-1)==0){
				cjyjZzlList.add(0.00);
			}else {
				double zzl = ((double)cjyjList.get(i)-cjyjList.get(i-1))/cjyjList.get(i-1);
				cjyjZzlList.add(BigDecimalUtil.getFormatDoubleVal(zzl));
			}
		}
		for (int i = 1; i < jyyjList.size(); i++) {
			if(jyyjList.get(i)==0||jyyjList.get(i-1)==0){
				jyyjZzlList.add(0.00);
			}else {
				double zzl = ((double)jyyjList.get(i)-jyyjList.get(i-1))/jyyjList.get(i-1);
				jyyjZzlList.add(BigDecimalUtil.getFormatDoubleVal(zzl));
			}
		}
		
		result.put("xfyjZzlList",xfyjZzlList);
		result.put("wgyjZzlList",wgyjZzlList);
		result.put("cjyjZzlList",cjyjZzlList);
		result.put("jyyjZzlList",jyyjZzlList);
		return result;
	}
	
	/**
	 * 添加预警数据
	 * @return
	 */
	@RequestMapping("/insert")
	@ResponseBody
	public Map<String,Object> insertWarning(WarningModel warningModel){
		Map<String,Object> result = new HashMap<String,Object>();
		
		if("LV2".equals(warningModel.getWarnLevel())) {
			if(DateUtil.getCuttentHours()>22) {
				warningModel.setType("1");
			}
		}else {
			warningModel.setType("0");
		}
		
		warningModel.setTime(DateUtil.fSecond(new Date()));
		
		int rows = 0;
		rows = warningService.insertWarning(warningModel);
		if(rows>0) {
			result.put("status", "success");
			result.put("message", "添加成功!");
		}else {
			result.put("status", "error");
			result.put("message", "添加失败!");
		}
		return result;
	}
}
