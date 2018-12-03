package com.zfsoft.boot.zhjx.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/yyqs")
public class YyqsController {
	
	/**
	 * 跳转到应用趋势-历史趋势
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/lsqs")
	public String lsqs(HttpServletRequest request, Model model) {
		return "html/yyqs/lsqs"; 
	}
	
	/**
	 * 跳转到应用趋势-实时数据
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/sssj")
	public String sssj(HttpServletRequest request, Model model) {
		return "html/yyqs/sssj"; 
	}
}
