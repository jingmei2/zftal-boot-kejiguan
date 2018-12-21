package com.zfsoft.boot.zhjx.util;

import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

/**
 * 项目参数工具类
 * @author yangbilin
 *
 */
public class ResourceUtil {

	private static final ResourceBundle bundle = ResourceBundle.getBundle(FileUntils.APPLICATIONPROPS);

	/**
	 * 获取session定义名称
	 *
	 * @return
	 */
	public static final String getSessionattachmenttitle(String sessionName) {
		return bundle.getString(sessionName);
	}

	//update-end--Author:chenxu  Date:20130322 for：左侧菜单信息放入到session中

	/**
	 * 获得请求路径
	 *
	 * @param request
	 * @return
	 */
	public static String getRequestPath(HttpServletRequest request) {
		String requestPath = request.getRequestURI() + "?" + request.getQueryString();
		if (requestPath.indexOf("&") > -1) {// 去掉其他参数
			requestPath = requestPath.substring(0, requestPath.indexOf("&"));
		}
		requestPath = requestPath.substring(request.getContextPath().length() + 1);// 去掉项目路径
		return requestPath;
	}

	public static String getBasePath() {
		String basePath="";
		HttpServletRequest request = ContextHolderUtils.getRequest();
		String dnPath=getPropertiesValue("domainName");
		if(StringUtils.isNotBlank(dnPath)){
			basePath=dnPath+request.getContextPath()+"/";
		}else{
			basePath=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath()+"/";
		}
		return basePath;
	}


	/**
	 * 获取配置文件参数
	 *
	 * @param name
	 * @return
	 */
	public static final String getConfigByName(String name) {
		return bundle.getString(name);
	}



	public static String getSysPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "").replaceFirst("WEB-INF/classes/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator).replaceAll("%20", " ");
		return resultPath;
	}

	/**
	 * 获取项目根目录
	 *
	 * @return
	 */
	public static String getPorjectPath() {
		String nowpath; // 当前tomcat的bin目录的路径 如
						// D:\java\software\apache-tomcat-6.0.14\bin
		String tempdir;
		nowpath = System.getProperty("user.dir");
		tempdir = nowpath.replace("bin", "webapps"); // 把bin 文件夹变到 webapps文件里面
		tempdir += "\\"; // 拼成D:\java\software\apache-tomcat-6.0.14\webapps\sz_pro
		return tempdir;
	}

	public static String getClassPath() {
		String path = Thread.currentThread().getContextClassLoader().getResource("").toString();
		String temp = path.replaceFirst("file:/", "");
		String separator = System.getProperty("file.separator");
		String resultPath = temp.replaceAll("/", separator + separator);
		return resultPath;
	}

	public static String getSystempPath() {
		return System.getProperty("java.io.tmpdir");
	}

	public static String getSeparator() {
		return System.getProperty("file.separator");
	}

	public static String getParameter(String field) {
		HttpServletRequest request = ContextHolderUtils.getRequest();
		return request.getParameter(field);
	}


	/**
	 * 根据配置变量获取配置文件中对应的值
	 * @author chenminming
	 * @param name 配置名
	 * @return 配置值
	 */
	public static String getPropertiesValue(String name)
	{
		try{
			String value=bundle.getString(name);
			return value;
		}catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据配置变量获取配置文件中对应的值
	 * @author chenminming
	 * @param name 配置名
	 * @return 配置值
	 */
	public static String getPropertiesValue(String name,String defaultvalue)
	{
		try{
			String value=bundle.getString(name);
			return StringUtils.isEmpty(value) ? defaultvalue : value;
		}catch (Exception e) {
			return defaultvalue;
		}
	}

	/**
	 * 获取数据库类型
	 *
	 * @return
	 */
	/*public static final String getJdbcUrl() {
		return bundle.getString("jdbc.url.jeecg").toLowerCase();
	}*/

	public static void main(String[] args) {
		System.out.println(System.getProperty("user.dir"));
		System.out.println(getPorjectPath());
		System.out.println(getSysPath());
		System.out.println(getClassPath());

	}
}
