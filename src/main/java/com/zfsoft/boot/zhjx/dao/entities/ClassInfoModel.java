package com.zfsoft.boot.zhjx.dao.entities;

/**
 * 班级信息实体
 * @author liucb
 *
 */
public class ClassInfoModel {

	
	private String classId;    //班级id
	private String className;  //班级名称
	private String instituteId;//学院id
	
	public String getClassId() {
		return classId;
	}
	public String getClassName() {
		return className;
	}
	public String getInstituteId() {
		return instituteId;
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
}
