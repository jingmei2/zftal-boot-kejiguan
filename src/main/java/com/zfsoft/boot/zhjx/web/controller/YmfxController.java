package com.zfsoft.boot.zhjx.web.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.web.session.User;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileUserService;
import com.zfsoft.boot.zhjx.service.svcinterface.IWarningService;
import com.zfsoft.boot.zhjx.util.DateUtil;
import com.zfsoft.boot.zhjx.util.ReportFormUtil;

/**
 * @author H110MF
 *
 */
/**
 * @author H110MF
 *
 */
@Controller
@RequestMapping("/ymfx")
public class YmfxController {
	
	@Autowired
	private IWarningService warningService;
	
	@Autowired
	private IMobileUserService mobileUserService;
	

	/**
	 * 跳转到页面分析-页面排行
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ymph")
	public String ymph(HttpServletRequest request, Model model) {
		return "html/ymfx/ymph"; 
	}
	
	/**
	 * 跳转到页面分析-性能监控
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/xnjk")
	public String xnjk(HttpServletRequest request, Model model) {
		return "html/ymfx/xnjk"; 
	}
	
	/**
	 * 跳转到页面分析-访问深度
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/fwsd")
	public String fwsd(HttpServletRequest request, Model model) {
		return "html/ymfx/fwsd"; 
	}
	
	
	/**
	 * 晚归分析
	 * @return
	 */
	@ExceptionHandler
	@RequestMapping("/wgfx")
	public String wgfx() {
		List<ReportFormUtil> list = new ArrayList<>();
		
		
		
		return "html/ymfx/wgfx"; 
	}
	
	/**
	 * 签到分析
	 * @return
	 */
	@ExceptionHandler
	@RequestMapping("/qdfx")
	public String qdfx() {
		return "html/ymfx/qdfx"; 
	}
	
	/**
	 * 用户行为分析获取图表数据
	 * @return
	 */
	@RequestMapping("/getData")
	@ResponseBody
	public Map<String,Object> getData(String daySelected,String yjTypeSelected,String instituteid,String classid,String username,HttpServletRequest request){
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> params = new HashMap<String,Object>();
		//System.out.println(daySelected+"--"+yjTypeSelected);
		String yjType = "0";
		String startDate = DateUtil.getPastDate(0);
		String endDate = DateUtil.getPastDate(-1);
		
		if(yjTypeSelected!=null&&!"".equals(yjTypeSelected)) {
			if("sswgyj".equals(yjTypeSelected)){
				yjType = "1";
			}else if("sksjfx".equals(yjTypeSelected)){
				yjType = "1";
			}else if("aqyjfx".equals(yjTypeSelected)){
				yjType = "0";
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
		if (StringUtils.isNotBlank(instituteid)) {
			params.put("instituteid", instituteid);
		}
		if (StringUtils.isNotBlank(classid)) {
			params.put("classid", classid);
		}
		if (StringUtils.isNotBlank(username)) {
			params.put("username", username);
		}
		

		User user = (User)request.getSession().getAttribute("user");
		Map<String,Object> jsdmParams = new HashMap<String,Object>(); 
		jsdmParams.put("yhm",user.getYhm());
		String jsdm = mobileUserService.getJsdmByYhm(jsdmParams);
        params.put("yhm",user.getYhm());
        params.put("jsdm",jsdm);
		
		List<WarningModel> warningModelList = warningService.getYhxwfxWarningList(params);
		
		result.put("warningModelList", warningModelList);
		return result;
	}
	
	
}
