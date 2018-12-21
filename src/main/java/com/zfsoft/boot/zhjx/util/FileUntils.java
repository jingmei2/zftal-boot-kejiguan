package com.zfsoft.boot.zhjx.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;


/**
 *文件路径获取方法工具类
 *
 */
public class FileUntils {
	/**
	 *系统属性文件名称
	 */
	public static final String APPLICATIONPROPS="application";
	/**
	 *系统中选择的获取图片的配置地址
	 */
	public static final String SUPLOADPATH="suploadPath";
	/**
	 *系统自带
	 */
	//服务类别logo存储文件夹名称变量
	public static final  String ICONPATHCATA = "iconimages";
	//服务logo存储文件夹名称变量
	public static final  String ICONPATHSERVICE="subiconimages";
	/**
	 *自定义上传
	 */
	//服务类别logo存储自定义文件夹名称变量
	public static final  String PICPATHCATA = "picture";
	//服务logo存储自定义文件夹名称变量
	public static final  String PICPATHSERVICE="subpicture";

	/**
	 *域名常量
	 */
	/*public static String DOMAINNAME="";

	static{
		DOMAINNAME=ResourceUtil.getPropertiesValue("domainName");
	}*/

	/**
     * 系统中选择的获取图片的配置地址
     * @return String
     * @author zhangxu
     */
    public static String getImageHost() {
		String url = ResourceUtil.getPropertiesValue(SUPLOADPATH);
		if (url == null) {
			return "/";
		}
        url = url.replace("\\", "/");
        if (!url.endsWith("/")) {
        	url += "/";
        }
		return url;
	}

	public static String encodeURL(String url){
		 try {
		 url = URLEncoder.encode(url, "utf-8").replaceAll("//+", "%20");
		 url = url.replaceAll("%3A", ":").replaceAll("%2F", "/").replaceAll("%25", "%");
		 } catch (UnsupportedEncodingException e) {
		 e.printStackTrace();
		 return null;
		 }
		 return url;
	}

	/**
	 * 获取所有系统中选择图标列表路径
	 * @param name  	把名称为name的图标排到最前面
	 * @param keyWord   根据keyWord关键词搜索所有图标列表
	 * @param iconpath  该logo存放的文件夹名称
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getImagesPathList(String name,String keyWord,String iconpath){
		String entityFileName = null;
		ArrayList<String> sortPathList = new ArrayList<String>();
		ArrayList<String> pathList = new ArrayList<String>();

		String filepath = ContextHolderUtils.getSession().getServletContext().getRealPath("/") + iconpath;
		filepath = filepath.replace("\\", "/");
		File newFile = new File(filepath);
		if (!newFile.exists()) {
			newFile.mkdir();
		}
		File[] files = newFile.listFiles(); // 该文件目录下文件全部放入数组
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getName();
				if(!StringUtils.isEmpty(name)){
					if(name.equals(fileName)){
						if(!StringUtils.isEmpty(keyWord)){
							if(fileName.indexOf(keyWord) != -1){
								entityFileName = iconpath + "/"+ fileName;
							}
						}else{
							entityFileName = iconpath + "/"+ fileName;
						}
						continue;
					}else{
						if(!StringUtils.isEmpty(keyWord)){
							if(fileName.indexOf(keyWord) != -1){
								pathList.add(iconpath + "/"+ fileName);
							}
						}else{
							pathList.add(iconpath + "/"+ fileName);
						}
					}
				}else{
					if(!StringUtils.isEmpty(keyWord)){
						if(fileName.indexOf(keyWord) != -1){
							pathList.add(iconpath + "/"+ fileName);
						}
					}else{
						pathList.add(iconpath + "/"+ fileName);
					}
				}
			}
		}
		if(!StringUtils.isEmpty(entityFileName)){
			sortPathList.add(entityFileName);
			sortPathList.addAll(pathList);
			return sortPathList;
		}else{
			return pathList;
		}
	}
	/**
	 * 获取所有系统中选择图标列表名称
	 * @param name  	把名称为name的图标排到最前面
	 * @param keyWord   根据keyWord关键词搜索所有图标列表
	 * @param iconpath  该logo存放的文件夹名称
	 * @return ArrayList<String>
	 */
	public static ArrayList<String> getImagesNameList(String name,String keyWord,String iconpath){
		String entityFileName = null;
		ArrayList<String> sortPathList = new ArrayList<String>();
		ArrayList<String> pathList = new ArrayList<String>();

		String filepath = ContextHolderUtils.getSession().getServletContext().getRealPath("/") + iconpath;
		filepath = filepath.replace("\\", "/");
		File newFile = new File(filepath);
		if (!newFile.exists()) {
			newFile.mkdir();
		}
		File[] files = newFile.listFiles(); // 该文件目录下文件全部放入数组
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				String fileName = files[i].getName();
				if(!StringUtils.isEmpty(name)){
					if(name.equals(fileName)){
						if(!StringUtils.isEmpty(keyWord)){
							if(fileName.indexOf(keyWord) != -1){
								entityFileName = fileName;
							}
						}else{
							entityFileName = fileName;
						}
						continue;
					}else{
						if(!StringUtils.isEmpty(keyWord)){
							if(fileName.indexOf(keyWord) != -1){
								pathList.add(fileName);
							}
						}else{
							pathList.add(fileName);
						}

					}
				}else{
					if(!StringUtils.isEmpty(keyWord)){
						if(fileName.indexOf(keyWord) != -1){
							pathList.add(fileName);
						}
					}else{
						pathList.add(fileName);
					}
				}
			}

		}
		if(!StringUtils.isEmpty(entityFileName)){
			sortPathList.add(entityFileName);
			sortPathList.addAll(pathList);
			return sortPathList;
		}else{
			return pathList;
		}
	}

	/**
	 * 系统自带logo，通过关键字搜索icon，并返回页面
	 * @param iconpath
	 * @return String
	 * @author yangbilin
	 */
	public String getImagesHtml(String iconpath){
		HttpServletRequest request=ContextHolderUtils.getRequest();
		String keyWord = request.getParameter("keyWord");
		String imageName = request.getParameter("imageName");
		List<String> iconList = FileUntils.getImagesPathList(null,keyWord,iconpath);
		List<String> fileNameList = FileUntils.getImagesNameList(null,keyWord,iconpath);

		StringBuilder imageHtml = new StringBuilder();
		if(iconList != null && iconList.size() > 0){
			for (int i = 0; i < iconList.size(); i++) {
				imageHtml.append("<li class='col-md-3 col-sm-3 col-xs-3 iconclass' style='white-space:nowrap;text-overflow:ellipsis;overflow:hidden;'>");
				imageHtml.append("<img style='height: 50px;width: 50px;' src='"+FileUntils.getImageHost()+iconList.get(i)+"'><h5>");
				imageHtml.append("<input type='radio' value='"+iconList.get(i)+"' name='icoPath'");
				if(!StringUtils.isEmpty(imageName) &&imageName.equals(iconList.get(i))){
					imageHtml.append("checked='checked'");
				}
				imageHtml.append("/>"+fileNameList.get(i)+"</h5></li>");
			}
		}
		return imageHtml.toString();
	}


	/**
	 * 删除项目用户自定义上传的文件夹下的图片
	 * @param realpath  项目路径
	 * @param filedir
	 * @author yangbilin
	 */
	public void deletePic(String realpath,String filedir){
		String picpath = realpath+ filedir;
		File file = new File(picpath);
		if(file!=null&&file.isFile()){
			if(file.exists()){
				file.delete();
			}
		}
	}

	/**
	 * 保存用户自定义上传的图片
	 * @param file
	 * @param realpath
	 * @author yangbilin
	 */
	public Map<String,Object> savePic(MultipartFile file,String realpath,String picfolder){
		Map<String,Object> map = new HashMap<String, Object>();
		try {
			//String filename = new String(file.getOriginalFilename().getBytes("ISO-8859-1"),"utf-8");
			String filename = file.getOriginalFilename();
			if(StringUtils.isBlank(filename)){
				map.put("issucess",false);
				map.put("msg","获取图片信息失败，请重新提交");
				return map;
			}
	        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
	        String suffix = filename.substring(filename.lastIndexOf("."),filename.length());
	        String newfilename = sdf.format(new Date())+"_"+RandomStringUtils.randomNumeric(12)+suffix;

	        String path = realpath+ picfolder;

	        System.out.println(path);
			//path = "/u01/zftal-boot-kejiguan/"+PICPATHCATA;
			path = "/u01/apache-tomcat-6.0.43_Meeting/webapps/zftal-kejiguan/"+PICPATHCATA;
			File f = new File(path);
			if(!f.exists()) {
				f.mkdirs();
			}
			//并将图片保存到配置路径下
			FileOutputStream fos=new FileOutputStream(path + "/"+ newfilename);
			fos.write(file.getBytes());
			fos.flush();
	        fos.close();
			map.put("newfilename",newfilename);
			String realUrl = "http://portal.zfsoft.com:9098/zftal-kejiguan/"+PICPATHCATA;
			map.put("path",realUrl + "/"+ newfilename);
	        map.put("issuccess",true);
			map.put("msg","保存成功");
		}catch (UnsupportedEncodingException e) {
			map.put("issucess",false);
			map.put("msg","字符集转换失败");
			e.printStackTrace();
		} catch (IOException e) {
			map.put("issucess",false);
			map.put("msg","图片上传失败");
			e.printStackTrace();
		}
		return map;
	}

	/**
	 * 保存或修改完成后，返回list页面
	 * @param request
	 * @param response
	 * @param redirecturl  跳转页面的mapping路径
	 * @author yangbilin
	 */
	public void writeToList(HttpServletRequest request,HttpServletResponse response,
			String redirecturl){
		try {
			PrintWriter writer = response.getWriter();
			writer.print("<script type='text/javascript'>parent.location.href='"+request.getContextPath()+"/"+redirecturl+"'</script>");
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
