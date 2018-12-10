package com.zfsoft.boot.zhjx.web.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/yhfx")
public class YhfxController {


	/**
	 * 跳转到用户分析-实时用户轨迹
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/ssyhgj")
	public String ssyhgj(HttpServletRequest request, Model model) {
		return "html/yhfx/ssyhgj";
	}

	/**
	 * 跳转到用户分析-新老用户
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/activity")
	public String activity(HttpServletRequest request, Model model) {
		return "html/yhfx/activity";
	}


	/**
	 * 跳转到用户分析-用户画像
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/book")
	public String book(HttpServletRequest request, Model model) {
		return "html/yhfx/book";
	}

}
