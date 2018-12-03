package com.zfsoft.boot.zhjx.web.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zfsoft.api.web.session.User;
import com.zfsoft.boot.zhjx.dao.entities.LeftMenuModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IMenuService;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileUserService;

@Controller
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private IMenuService menuService;
	
	@Autowired
	private IMobileUserService mobileUserService;
	
	/**
	 * 获取基础分析页面左侧菜单
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping("/getMenuData")
	@ResponseBody
	public List<LeftMenuModel> getCdData(HttpServletRequest request, Model model) {
		LeftMenuModel menuModel = new LeftMenuModel();
		
		User user = (User)request.getSession().getAttribute("user");
		Map<String,Object> jsdmParams = new HashMap<String,Object>(); 
		jsdmParams.put("yhm",user.getYhm());
		String jsdm = mobileUserService.getJsdmByYhm(jsdmParams);
		
		if("xs".equals(jsdm)) {
			menuModel.setGnmkdm("N09");
		}else if("js".equals(jsdm)){
			menuModel.setGnmkdm("N07");
		}else {
			menuModel.setGnmkdm("N08");
		}
		//先根据一级菜单获取二级菜单
		List<LeftMenuModel> secondLevelMenus = menuService.getModelList(menuModel);
		for (LeftMenuModel secondLevelMenu : secondLevelMenus) {
			if(secondLevelMenu!=null) {
				secondLevelMenu.setSfgnym("0");
				List<LeftMenuModel> thirdLevelMenus = menuService.getModelList(secondLevelMenu);
				for (LeftMenuModel thirdLevelMenu : thirdLevelMenus) {
					if(thirdLevelMenu!=null) {
						thirdLevelMenu.setSfgnym("1");
					}
				}
				secondLevelMenu.setChildren(thirdLevelMenus);				
			}
		}
		return secondLevelMenus;
	}
}
