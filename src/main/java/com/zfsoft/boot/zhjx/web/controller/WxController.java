package com.zfsoft.boot.zhjx.web.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.jdom2.JDOMException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import com.zfsoft.api.web.session.User;
import com.zfsoft.boot.zhjx.dao.entities.MobileDeviceModel;
import com.zfsoft.boot.zhjx.dao.entities.MobileUserModel;
import com.zfsoft.boot.zhjx.dao.entities.ResultEntity;
import com.zfsoft.boot.zhjx.dao.entities.WarningModel;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileDeviceService;
import com.zfsoft.boot.zhjx.service.svcinterface.IMobileUserService;
import com.zfsoft.boot.zhjx.service.svcinterface.IUserService;
import com.zfsoft.boot.zhjx.service.svcinterface.IWarningService;
import com.zfsoft.boot.zhjx.util.Encrypt;
import com.zfsoft.boot.zhjx.util.SHA1;
import com.zfsoft.boot.zhjx.util.WXUtil;
import com.zfsoft.boot.zhjx.util.XmlParseTool;

@Controller
@RequestMapping("/wx")
public class WxController {

	
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private IMobileDeviceService mobileDeviceService;
	
	@Autowired
	private IWarningService warningService;
	
	@Autowired
	private IMobileUserService mobileUserService;
	
	
	@RequestMapping("/userLogin")
	@ResponseBody
	public ResultEntity userLogin(@RequestParam String userName,
			@RequestParam String pass,HttpSession session,String openid) {
		ResultEntity resultEntity = new ResultEntity();
		
		MobileUserModel user = userService.checkWxLogin(userName,pass);
		if (user == null) {
			resultEntity.setCode(0);
			resultEntity.setStatus("用户名或密码错误");
		}else {
			userService.updateOpenid(openid, userName);
			session.setAttribute("user", user);
			session.setAttribute("yhm", user.getYhm());
			
			MobileDeviceModel mobile = new MobileDeviceModel();
			mobile.setUserName(openid);
			MobileDeviceModel mobileDeviceModel = mobileDeviceService.getDeviceInfo(mobile);
			if (mobileDeviceModel != null) {
				mobileDeviceService.updateByOpenid(userName, openid);
			}else {
				mobile.setUserName(userName);
				mobile.setOpenid(openid);
				mobile.setLocInfo("120.31433,30.261329");
				mobileDeviceService.insert(mobile);
			}
			
			resultEntity.setCode(1);
			resultEntity.setStatus("登录成功");
			resultEntity.setData(user);
		}
		return resultEntity;
	}
	
	
	@RequestMapping("/register")
	@ResponseBody
	public ResultEntity register(MobileUserModel user,HttpSession session) {
		ResultEntity resultEntity = new ResultEntity();
		
		resultEntity = userService.register(user, session);
		//插入默认位置
		MobileDeviceModel deviceModel = new MobileDeviceModel();
		deviceModel.setUserName(user.getYhm());
		deviceModel.setLocInfo("120.31433,30.261329");
		System.out.println("插入默认地理位置信息");
		mobileDeviceService.insert(deviceModel);
		return resultEntity;
	}
	

	@RequestMapping("/userInfo")
	@ResponseBody
	public ResultEntity userInfo(@SessionAttribute("yhm")String yhm) {
		ResultEntity resultEntity = new ResultEntity();
		
		MobileUserModel user = userService.selectUserByYhm(yhm);
		if (user == null) {
			resultEntity.setCode(0);
			resultEntity.setStatus("用户信息失效，请重新登录");
		}else {
			resultEntity.setCode(1);
			resultEntity.setData(user);
		}
		return resultEntity;
	}
	
	
	
	
	@ResponseBody
	@RequestMapping(value = "/sending", produces = "text/plain;charset=utf-8")
	public String sending(HttpServletRequest request) {
		
		try {
			//WeixinApp weixinApp = weixinAppService.queryWeixinAppByCompanyCode(companyCode);
			String token = "yjpt";
			if (request.getMethod().equals("GET")) {
				// 微信加密签名
				String signature = request.getParameter("signature");
				// 时间戳
				String timestamp = request.getParameter("timestamp");
				// 随机数
				String nonce = request.getParameter("nonce");
				// 随机字符串
				String echostr = request.getParameter("echostr");
				
				String sign = SHA1.getSHA11(token, timestamp, nonce);
				if (sign.equals(signature)) {
					return echostr;
				}else {
					return "";
				}
			} else {
				Map<String, String> postmessage = new HashMap<String, String>();
				StringBuffer jb = new StringBuffer();
				String line = null;
				BufferedReader reader = null;
				try {
					reader = request.getReader();
					while ((line = reader.readLine()) != null) {
						jb.append(line);
					}
					String xml = jb.toString();
					postmessage = XmlParseTool.doXMLParse(xml);
					//logger.info("postmessage:"+JacksonUtils.objectToJson(postmessage));
					System.out.println("MsgType = " + postmessage.get("MsgType") + "Event=" + postmessage.get("Event"));
					if (postmessage.get("MsgType").equals("event")) {
						 if(postmessage.get("Event").equals("LOCATION")){
							
							String openId=postmessage.get("FromUserName");
							MobileUserModel user = userService.selectUserByOpenid(openId);
							if (user == null) {
								MobileDeviceModel deviceModel = new MobileDeviceModel();
								deviceModel.setUserName(openId);
								deviceModel.setOpenid(openId);
								deviceModel.setLocInfo(postmessage.get("Longitude")+"," + postmessage.get("Latitude"));
								System.out.println("插入地理位置信息");
								deviceModel.setLastLoginTime(new Date());
								mobileDeviceService.insert(deviceModel);
								System.out.println("插入成功");
							}
							MobileDeviceModel deviceModel = new MobileDeviceModel();
							deviceModel.setUserName(user.getYhm());

							MobileDeviceModel deviceModel2 = mobileDeviceService.getDeviceInfo(deviceModel);
							if (deviceModel2 == null) {
								deviceModel.setLocInfo(postmessage.get("Longitude")+"," + postmessage.get("Latitude"));
								System.out.println("插入地理位置信息");
								deviceModel.setLastLoginTime(new Date());
								mobileDeviceService.insert(deviceModel);
								System.out.println("插入成功");
							}else {
								//更新地理位置
								deviceModel2.setLocInfo(postmessage.get("Longitude")+"," + postmessage.get("Latitude"));
								deviceModel2.setLastLoginTime(new Date());
								mobileDeviceService.update(deviceModel2);
							}
							
							return "success";
						}
					}else if (postmessage.get("MsgType").equals("LOCATION") || postmessage.get("MsgType").equals("location")) {
						String openId=postmessage.get("FromUserName");
						MobileUserModel user = userService.selectUserByOpenid(openId);
						if (user == null) {
							return "";
						}
						MobileDeviceModel deviceModel = new MobileDeviceModel();
						deviceModel.setUserName(user.getYhm());

						MobileDeviceModel deviceModel2 = mobileDeviceService.getDeviceInfo(deviceModel);
						if (deviceModel2 == null) {
							deviceModel.setLocInfo(postmessage.get("Longitude")+"," + postmessage.get("Latitude"));
							System.out.println("插入地理位置信息");
							deviceModel.setLastLoginTime(new Date());
							mobileDeviceService.insert(deviceModel);
							System.out.println("插入成功");
						}else {
							//更新地理位置
							deviceModel2.setLocInfo(postmessage.get("Longitude")+"," + postmessage.get("Latitude"));
							deviceModel2.setLastLoginTime(new Date());
							mobileDeviceService.update(deviceModel2);
						}
						
						return "success";
					}
				} catch (JDOMException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return "success";
			}
		} catch (Exception e) {
			//logger.error("pushEvent error",e);
		}
		return "";
	}
	
	
	@RequestMapping("/getOpenId")
	@ResponseBody
	public ResultEntity getOpenId(@RequestParam String code) {
		ResultEntity resultEntity = new ResultEntity();
		
		String result = WXUtil.getOpenid(code);
		String openId = WXUtil.parseJson(result, "openid");
		resultEntity.setCode(1);
		resultEntity.setData(openId);
		
		return resultEntity;
	}
	
	//按规则生成账号用户名
	@RequestMapping("/getYhmByMath")
	@ResponseBody
	public ResultEntity getYhmByMath(String openid) {
		ResultEntity resultEntity = new ResultEntity();
		
		
		
		Long a = (long) (Math.random() * 50);
		a = a + 200931030420202L;
		String yhm = String.valueOf(a);
		resultEntity.setCode(1);
		resultEntity.setData(yhm);
		
		//如果openid已绑定账号，则放回已绑定账号登录
		if (StringUtils.isNotBlank(openid)) {
			MobileUserModel user = userService.selectUserByOpenid(openid);
			if (user != null) {
				resultEntity.setData(user.getYhm());
			}
		}
		return resultEntity;
	}
	
	/**
	 * 学生微信端获取预警记录
	 * @param request
	 * @param username
	 * @return
	 */
	@RequestMapping(value="/getList",method=RequestMethod.GET)
	@ResponseBody
	public ResultEntity getList(HttpServletRequest request,@SessionAttribute("yhm")String yhm) {
		Map<String,Object> params = new HashMap<String,Object>();
		
		Map<String,Object> jsdmParams = new HashMap<String,Object>(); 
		jsdmParams.put("yhm",yhm);
		String jsdm = mobileUserService.getJsdmByYhm(jsdmParams);
		
        params.put("yhm",yhm);
        params.put("jsdm",jsdm);
		List<WarningModel> list = warningService.getWarningList(params);
		
		if(list==null) {
			list = new ArrayList<WarningModel>();
		}
		ResultEntity resultEntity = new ResultEntity(ResultEntity.SUCCESS_CODE, "获取成功", list);
		return resultEntity;
	}
	
}
