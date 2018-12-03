package com.zfsoft.boot.zhjx.dao.entities;

/**
 * 高德地图多边形数据实体
 * @author liucb
 *
 */
public class PolygonModel {
    private String polygonId;  //唯一主键
    private String lnglatStr;  //经纬度串
    private String description;//描述
    private String title;      //预警标题
    private String warnLevel;      //预警等级
    private String startTime;  //预警开始时间
    private String endTime;    //预警结束时间
    private String color;      //预警颜色
    private String iscon;		//是否是宿舍  0：预警区域  1.宿舍区域
    private String lddm;	//楼栋代码
    
	public String getPolygonId() {
		return polygonId;
	}
	public String getLnglatStr() {
		return lnglatStr;
	}
	public String getDescription() {
		return description;
	}
	public void setPolygonId(String polygonId) {
		this.polygonId = polygonId;
	}
	public void setLnglatStr(String lnglatStr) {
		this.lnglatStr = lnglatStr;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getTitle() {
		return title;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public String getColor() {
		return color;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public String getWarnLevel() {
		return warnLevel;
	}
	public void setWarnLevel(String warnLevel) {
		this.warnLevel = warnLevel;
	}
	public String getIscon() {
		return iscon;
	}
	public void setIscon(String iscon) {
		this.iscon = iscon;
	}
	public String getLddm() {
		return lddm;
	}
	public void setLddm(String lddm) {
		this.lddm = lddm;
	}
	
	
}
