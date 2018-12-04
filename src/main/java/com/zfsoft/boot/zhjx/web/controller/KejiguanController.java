package com.zfsoft.boot.zhjx.web.controller;

import com.zfsoft.boot.zhjx.dao.entities.MobileDeviceModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileUserModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileDeviceService;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileUserService;
import com.zfsoft.boot.zhjx.service.svcinterface.IUserService;
import com.zfsoft.boot.zhjx.service.svcinterface.IWarningService;
import com.zfsoft.boot.zhjx.util.SHA1;
import com.zfsoft.boot.zhjx.util.WXUtil;
import com.zfsoft.boot.zhjx.util.XmlParseTool;
import org.apache.commons.lang3.StringUtils;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("/kejiguan")
public class KejiguanController {

//	@Autowired
//	private IUserService userService;

	@RequestMapping("/test")
	@ResponseBody
	public ResultEntity userLogin(@RequestParam String userName,
			@RequestParam String pass,HttpSession session,String openid) {
		ResultEntity resultEntity = new ResultEntity();

		return resultEntity;
	}

}
