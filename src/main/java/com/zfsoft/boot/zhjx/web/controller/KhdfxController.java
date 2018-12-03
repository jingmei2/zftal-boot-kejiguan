package com.zfsoft.boot.zhjx.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/khdfx")
public class KhdfxController {
	
	/**
	 * 跳转到客户端分析-区域
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/qy")
	public String qy(HttpServletRequest request, Model model) {
		return "html/khdfx/qy"; 
	}
	
	/**
	 * 跳转到客户端分析-运营商
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/yys")
	public String yys(HttpServletRequest request, Model model) {
		return "html/khdfx/yys"; 
	}
	
	/**
	 * 跳转到客户端分析-终端信息
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/zdxx")
	public String zdxx(HttpServletRequest request, Model model) {
		return "html/khdfx/zdxx"; 
	}
	
}
