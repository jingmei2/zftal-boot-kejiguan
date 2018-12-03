package com.zfsoft.boot.zhjx.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	/**
	 * 跳转到首页
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/index")
	public String index(HttpServletRequest request, Model model) {
		//model.addAttribute("target", "yygl/index");
		return "layout/index"; 
	}
	
	/**
	 * 跳转到基础分析页面
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/jcfx")
	public String jcfx(HttpServletRequest request, Model model) {
		//model.addAttribute("target", "jcfx/index");
		return "html/jcfx/index"; 
	}
	
	@RequestMapping("/yygl")
	public String yygl(HttpServletRequest request, Model model) {
		model.addAttribute("target", "yygl/index");
		return "layout/index"; 
	}
	
}
