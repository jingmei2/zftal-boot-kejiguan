package com.zfsoft.boot.zhjx.dao.entities;

public class WarningModel {
	private String id;            //唯一主键
	private String time;          //预警时间 
	private String place;         //预警地点
	private String username;      //用户id 
	private String name;          //姓名
	private String polygonId;     //预警区域id
	private String type;          //预警类型:0危险预警,1晚归预警,2假期预警,3消费预警,4成绩预警,5借阅预警
	private String warnLevel;
	private String leaveStatus;	//晚归是否请假0未请假 1.已请假
	
	public String getId() {
		return id;
	}
	public String getTime() {
		return time;
	}
	public String getPlace() {
		return place;
	}
	public String getPolygonId() {
		return polygonId;
	}
	public String getType() {
		return type;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public void setPolygonId(String polygonId) {
		this.polygonId = polygonId;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWarnLevel() {
		return warnLevel;
	}
	public void setWarnLevel(String warnLevel) {
		this.warnLevel = warnLevel;
	}
	public String getLeaveStatus() {
		return leaveStatus;
	}
	public void setLeaveStatus(String leaveStatus) {
		this.leaveStatus = leaveStatus;
	}
	
}
