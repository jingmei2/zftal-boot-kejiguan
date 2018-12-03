package com.zfsoft.boot.zhjx.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.web.session.User;
import com.zfsoft.boot.zhjx.dao.entities.ReportModel;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IReportService;

@Controller
@RequestMapping("/report")
public class ReportController {
	
	@Autowired
	private IReportService reportService;
	
	/**
	 * 首页分析报告和舆情分析列表
	 * @param request
	 * @param reportModel
	 * @return
	 */
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getList(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		User user = (User)request.getSession().getAttribute("user");
		ReportModel rm = new ReportModel();
		rm.setYhm(user.getYhm());
		
		rm.setType("0");
		List<ReportModel> list1 = reportService.getReportList(rm);
		rm.setType("1");
		if(list1==null) {
			list1 = new ArrayList<ReportModel>();
		}
		map.put("fxbg", list1);
		List<ReportModel> list2 = reportService.getReportList(rm);
		if(list2==null) {
			list2 = new ArrayList<ReportModel>();
		}
		map.put("yqjk", list2);
		return map;
	}
	
	/**
	 * 报告详情
	 * @param request
	 * @param reportModel
	 * @return
	 */
	@RequestMapping(value="/getDetail",method=RequestMethod.GET)
	public String getDetail(HttpServletRequest request,ReportModel reportModel) {
		ReportModel reportModelQueryed = reportService.getDetail(reportModel);
		if(reportModelQueryed==null) {
			reportModelQueryed = new ReportModel();
		}
		//request.setAttribute("reportModel",reportModelQueryed);
		request.setAttribute("reportModelTitle",reportModelQueryed.getTitle());
		request.setAttribute("reportModelContent",reportModelQueryed.getContent());
		request.setAttribute("reportModelTime",reportModelQueryed.getTime());
		return "html/yygl/reportDetail";
	}
	
	//晚归学生名单
	@RequestMapping(value="/getWanguiList",method=RequestMethod.GET)
	@ResponseBody
	public Map<String,Object> getWanguiList(HttpServletRequest request) {
		Map<String,Object> map = new HashMap<String,Object>();
		String type = "0";
		List<WarningModel> weixin = reportService.getWanguiListByType(type);
		
		type = "1";
		List<WarningModel> wangui = reportService.getWanguiListByType(type);
		map.put("weixin", weixin);
		map.put("wangui", wangui);
		return map;
	}
	
}
