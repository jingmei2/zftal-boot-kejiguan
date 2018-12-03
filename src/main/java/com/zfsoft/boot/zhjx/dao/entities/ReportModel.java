package com.zfsoft.boot.zhjx.dao.entities;

/**
 * 分析报告与舆情监控实体
 * @author liucb
 *
 */
public class ReportModel {

	private String id;      //唯一主键
	private String title;   //标题
	private String content; //内容
	private String time;    //时间
	private String type;    //类型,0分析报告,1舆情监控
	private String instituteId; //学院id
	private String yhm;     //关联yhm查询    
	
	public String getId() {
		return id;
	}
	public String getTitle() {
		return title;
	}
	public String getContent() {
		return content;
	}
	public String getTime() {
		return time;
	}
	public String getType() {
		return type;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getInstituteId() {
		return instituteId;
	}
	public void setInstituteId(String instituteId) {
		this.instituteId = instituteId;
	}
	public String getYhm() {
		return yhm;
	}
	public void setYhm(String yhm) {
		this.yhm = yhm;
	}
}
