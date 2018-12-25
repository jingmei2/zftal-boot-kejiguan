package com.zfsoft.boot.zhjx.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/yhfx")
public class YhfxController {
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

	/**
	 * 跳转到用户分析-用户画像
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/venue")
	public String venue(HttpServletRequest request, Model model) {
		return "html/yhfx/venue";
	}


	/**
	 * 跳转到用户分析-用户画像
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/navigation")
	public String navigation(HttpServletRequest request, Model model) {
		return "html/yhfx/navigation";
	}
}
