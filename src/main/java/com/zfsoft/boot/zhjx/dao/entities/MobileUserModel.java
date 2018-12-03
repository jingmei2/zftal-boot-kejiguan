package com.zfsoft.boot.zhjx.dao.entities;

import org.apache.ibatis.type.Alias;
import org.aspectj.weaver.AjAttribute.PrivilegedAttribute;
import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

/**
 * 移动设备手机用户实体
 * @author liucb
 *
 */
@Alias(value="mobileUserModel")
public class MobileUserModel {
    private String yhm;//用户名
    private String xm; //姓名
    private String dzyx; //电子邮箱
    private String sfqy;//是否启用
    private String yhlx;//用户类型
    private String kl;//口令
    private String sjhm;//手机号码
    private String yhmmdj;//用户密码等级
    private String apptoken;//32位token串
    private String classId;
    private String schoolName;//学院名称
    private String className;//班级名称
    private String openid;
    
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
	public String getDzyx() {
		return dzyx;
	}
	public void setDzyx(String dzyx) {
		this.dzyx = dzyx;
	}
	public String getSfqy() {
		return sfqy;
	}
	public void setSfqy(String sfqy) {
		this.sfqy = sfqy;
	}
	public String getYhlx() {
		return yhlx;
	}
	public void setYhlx(String yhlx) {
		this.yhlx = yhlx;
	}
	public String getKl() {
		return kl;
	}
	public void setKl(String kl) {
		this.kl = kl;
	}
	public String getSjhm() {
		return sjhm;
	}
	public void setSjhm(String sjhm) {
		this.sjhm = sjhm;
	}
	public String getYhmmdj() {
		return yhmmdj;
	}
	public void setYhmmdj(String yhmmdj) {
		this.yhmmdj = yhmmdj;
	}
	public String getApptoken() {
		return apptoken;
	}
	public void setApptoken(String apptoken) {
		this.apptoken = apptoken;
	}
	public String getClassId() {
		return classId;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	
	
}
