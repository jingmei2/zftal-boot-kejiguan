package com.zfsoft.boot.zhjx.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/apfx")
public class ApfxController {
	
	/**
	 * 跳转到gRApfx分析
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/gRApfx")
	public String gRApfx(HttpServletRequest request, Model model) {
		return "html/apfx/gRApfx"; 
	}
	
	/**
	 * 跳转到bJApfx分析
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/bJApfx")
	public String bJApfx(HttpServletRequest request, Model model) {
		return "html/apfx/bJApfx"; 
	}
	
	/**
	 * 跳转到xYApfx分析
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/xYApfx")
	public String xYApfx(HttpServletRequest request, Model model) {
		return "html/apfx/xYApfx"; 
	}
	
	/**
	 * 跳转到ap分析
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/apfx")
	public String apfx(HttpServletRequest request, Model model) {
		return "html/apfx/apfx"; 
	}
	
	/**
	 * 跳转到ap监控
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/apjk")
	public String apjk(HttpServletRequest request, Model model) {
		return "html/apfx/apjk"; 
	}
	
	/**
	 * 跳转到ap预警
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/apyj")
	public String apyj(HttpServletRequest request, Model model) {
		return "html/apfx/apyj"; 
	}
	
	/**
	 * 跳转到预警区域设置
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/yjqysz")
	public String yjqysz(HttpServletRequest request, Model model) {
		return "html/apfx/yjqysz"; 
	}
	
	@RequestMapping("/yhgj")
	public String yhgj(HttpServletRequest request, Model model) {
		return "html/apfx/yhgj"; 
	}
}
