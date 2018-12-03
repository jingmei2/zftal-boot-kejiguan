package com.zfsoft.boot.zhjx.web.controller;

import java.security.interfaces.RSAPublicKey;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.DisabledAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.code.kaptcha.Constants;
import com.zfsoft.api.security.RSALoginService;
import com.zfsoft.api.spring.factory.ServiceFactory;
import com.zfsoft.shiro.IncorrectCaptchaException;
import com.zfsoft.shiro.InvalidAccountException;
import com.zfsoft.shiro.NoneRoleException;
import com.zfsoft.shiro.SubjectUtils;
import com.zfsoft.shiro.realm.AccountRealm;
import com.zfsoft.shiro.realm.DefaultAccountRealm;
import com.zfsoft.web.context.WebContext;
import com.zfsoft.webmvc.controller.BaseController;

import net.sf.json.JSONObject;

@Controller
@RequestMapping("/authz")
public class AuthzController extends BaseController{
	  private static final String DEFAULT_ERROR_KEY_ATTRIBUTE_NAME = "shiroLoginFailure";
	    private RSALoginService rsaLoginService = new RSALoginService();

	    /**
	     *
	     * @description	： 用户登录界面
	     * @author 		： 万大龙（743）
	     * @date 		：2017年11月6日 下午5:42:57
	     * @param request
	     * @return
	     */
	    @RequestMapping(value="index")
	    public String index(HttpServletRequest request) {
	    	boolean authenticated = SubjectUtils.isAuthenticated();
	    	/**
	    	 * 如果用户已登录，直接转发到首页
	    	 */
	    	if(authenticated){
	    		return "redirect:/index";
	    	}
	        return "/html/authz/login";
	    }

	   /**
	    *
	    * <p>方法说明：获取公钥<p>
	    * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	    * <p>时间：2016年9月1日上午11:30:27<p>
	    * @param request
	    * @return
	    */
	    @RequestMapping(value="getPublicKey", method= RequestMethod.GET)
	    @ResponseBody
	    public JSONObject getPublicKey(HttpServletRequest request){

	    	RSAPublicKey publicKey = rsaLoginService.generateKey(request);

	    	byte[] modulus = publicKey.getModulus().toByteArray();
	    	byte[] exponent = publicKey.getPublicExponent().toByteArray();

	    	JSONObject json = new JSONObject();
	    	json.put("modulus", Base64.encodeBase64String(modulus));
	    	json.put("exponent", Base64.encodeBase64String(exponent));
	    	return json;
	    }

	    /**
	     *
	     * <p>方法说明：用户登录<p>
	     * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	     * <p>时间：2017年3月16日下午3:59:37<p>
	     * @param request
	     * @return
	     */
	    @RequestMapping(value="slogin")
	    public String slogin(HttpServletRequest request, Model model) {
	    	boolean authenticated = SubjectUtils.isAuthenticated();

	    	/**
	    	 * 如果用户已登录，直接转发到首页
	    	 */
	    	if(authenticated){
	    		return "redirect:/index";
	    	}

	    	HttpSession session = request.getSession();
	    	session.setAttribute(Constants.KAPTCHA_SESSION_CONFIG_KEY, null);

	        String ERROR_VALUE = (String)request.getAttribute(DEFAULT_ERROR_KEY_ATTRIBUTE_NAME);
	        /**
	         * 验证码错误返回失败
	         */
	        if(StringUtils.equals(IncorrectCaptchaException.class.getName(), ERROR_VALUE)){
	        	model.addAttribute("message",getMessage("login.info.error"));
	        }
	        /**
	         * 账号或密码为空
	         */
	        else if(StringUtils.equals(InvalidAccountException.class.getName(), ERROR_VALUE)){
	        	model.addAttribute("message", getMessage("login.info.empty"));
			}
	        /**
	         * 账户或密码错误
	         */
	        else if(StringUtils.equals(UnknownAccountException.class.getName(), ERROR_VALUE)){
	        	model.addAttribute("message", getMessage("login.info.failed"));
	        }
	        /**
	         * 账户没有启用
	         */
	        else if(StringUtils.equals(DisabledAccountException.class.getName(), ERROR_VALUE)){
	        	model.addAttribute("message", getMessage("login.user.forbid"));
	        }
	        /**
	         * 该用户无所属角色，禁止登录
	         */
	        else if(StringUtils.equals(NoneRoleException.class.getName(), ERROR_VALUE)){
	        	model.addAttribute("message", getMessage("login.info.failed"));
	        }


	        String kick =request.getParameter("kickout");
	        if(StringUtils.equals("1", kick)){
	        	model.addAttribute("message", getMessage("login.user.kick"));
	        }

	        return "/html/authz/login";
	    }


	    /**
	     *
	     * <p>方法说明：切换角色<p>
	     * <p>作者：<a href="mailto:waterlord@vip.qq.com">Penghui.Qu[445]<a><p>
	     * <p>时间：2016年9月5日上午8:55:38<p>
	     * @param jsdm
	     * @return
	     */
	    @RequestMapping(value="switchRole")
		public String switchRole(String jsdm) {
			try {
				HttpSession session = WebContext.getSession();

				if(StringUtils.isNotBlank(jsdm) && (!StringUtils.equals(jsdm, getUser().getJsdm()))){
					//切换当前的角色信息
					getUser().setJsdm(jsdm);

					//刷新shiro缓存
					AccountRealm shiroRealm = ServiceFactory.getService(DefaultAccountRealm.class);
					shiroRealm.clearAuthorizationCache();
					//刷新shiro缓存
					//删除用户数据范围标识
					//session.removeAttribute(GlobalString.SESSION_YHJSSJFWZ_LIST);
				}
			} catch (Exception e) {
				logException(e);
			}
			return "redirect:/";
		}

}
