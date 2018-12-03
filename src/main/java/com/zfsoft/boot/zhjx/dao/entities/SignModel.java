package com.zfsoft.boot.zhjx.dao.entities;

/**
 * 签到信息实体
 * @author liucb
 *
 */
public class SignModel {

	
	private String yhm;    //用户名
	private String xm;     //姓名
	private String signData;  //签到时间
	private String location;//签到地点，经纬度，逗号隔开
	private String content;//签到备注
	private String address;//签到地点
	
	private String classId;//班级id
	private String className;//班级名称
	private String instituteId;//学院id
	private String instituteName;//学院名称
	private String kssj;//开始时间
	private String jssj;//结束时间
	
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
	public String getSignData() {
		return signData;
	}
	public void setSignData(String signData) {
		this.signData = signData;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getClassId() {
		return classId;
	}
	public String getClassName() {
		return className;
	}
	public String getInstituteId() {
		return instituteId;
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setClassId(String classId) {
		this.classId = classId;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getKssj() {
		return kssj;
	}
	public String getJssj() {
		return jssj;
	}
	public void setKssj(String kssj) {
		this.kssj = kssj;
	}
	public void setJssj(String jssj) {
		this.jssj = jssj;
	}
	public String getXm() {
		return xm;
	}
	public void setXm(String xm) {
		this.xm = xm;
	}
}
